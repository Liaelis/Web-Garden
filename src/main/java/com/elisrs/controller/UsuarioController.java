package com.elisrs.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.elisrs.enty.RecuperacaoSenha;
import com.elisrs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.elisrs.enty.Permissao;
import com.elisrs.enty.Usuario;
import com.elisrs.repository.PermissaoRepository;
import com.elisrs.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    HttpServletRequest req;
    @Autowired //injetor de dependencia
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PermissaoRepository permissaorepository;
    @Autowired
    private UsuarioService usuarioService;


    //Cadastro de usuario
    @RequestMapping(method = RequestMethod.POST, value = "/cadastrar")
    public ModelAndView salvar(Usuario usuario) {
        ModelAndView andV;

        Optional<Permissao> permissao = permissaorepository.findById(2);
        if (permissao.isPresent()) {
            usuario.setPermissao(permissao.get());
        } else {
            System.out.println("nao tem");
        }

        Optional<Usuario> opEmail = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (opEmail.isPresent()) {
            andV = new ModelAndView("pages/cadastro");
            andV.addObject("op", false);
            andV.addObject("mensagem", true);
            return andV;
        } else {
            usuario.setSenha(usuarioService.criptografaSenha(usuario.getSenha()));
            usuarioRepository.save(usuario);
            andV = new ModelAndView("pages/cadastro");
            andV.addObject("op", true);
            andV.addObject("mensagem", true);
            return andV;
        }
    }

//    //Login de usuario
//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public ModelAndView autenticarUsuario(String email, String senha) {
//        ModelAndView mv;
//        HttpSession session = req.getSession();
//        Optional<Usuario> opUsuario = usuarioRepository.findUsuarioByEmail(email);
//        if (opUsuario.isPresent()) {
//            Usuario usuario = opUsuario.get();
//            if (usuarioService.comparaCripto(senha, usuario.getSenha())) {
//                if (usuario.getPermissao().getId().equals(1)) {
//                    session.setAttribute("usuario", usuario);
//                    mv = new ModelAndView("redirect:/areaadm");
//                    return mv;
//                } else {
//                    mv = new ModelAndView("redirect:/areausuer");
//                    session.setAttribute("usuario", usuario);
//                    return mv;
//                }
//            } else {
//                mv = new ModelAndView("pages/login");
//                mv.addObject("erro", true);
//                return mv;
//            }
//        } else {
//            mv = new ModelAndView("pages/login");
//            mv.addObject("erro", true);
//            return mv;
//        }
//    }

    //Logout de usuario volta ao index
//    @RequestMapping("/logout")
//    public ModelAndView desconectar(Usuario usuario) {
//        HttpSession session = req.getSession();
//        session.removeAttribute("usuario");
//        return new ModelAndView("redirect:/");
//    }

    //carrega informações do usuario
    @GetMapping("/dadosuser")
    public ModelAndView verDados() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mv = new ModelAndView("pages/dadosusuario");
        mv.addObject("usuario",usuario);
        return mv;
    }

    //atualiza dados do usuario
    @PostMapping("/dadosuser")
    public ModelAndView atualizar(String nome, String email, String telefone, Integer id) {
        Optional<Usuario> optUsuarioAntigo = usuarioRepository.findById(id);
        Usuario usuarioAntigo = optUsuarioAntigo.get();
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setPermissao(usuarioAntigo.getPermissao());
        usuarioNovo.setSenha(usuarioAntigo.getSenha());
        usuarioNovo.setNome(nome);
        usuarioNovo.setEmail(email);
        usuarioNovo.setTelefone(telefone);
        usuarioNovo.setId(usuarioAntigo.getId());
        usuarioRepository.save(usuarioNovo);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mv = new ModelAndView("pages/dadosusuario");
        mv.addObject("usuario",usuario);
        mv.addObject("success", true);
        return mv;
    }

    @RequestMapping("/testauser")
    public ModelAndView testarTipoUsuario() {
        ModelAndView mv;
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usuario.getPermissao().getId().equals(1)) {
            mv = new ModelAndView("redirect:/areaadm");
            return mv;
        } else {
            mv = new ModelAndView("redirect:/areausuer");
            return mv;
        }
    }

    @GetMapping("/deletar")
    public ModelAndView deletarConta() {
        ModelAndView mv;
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usuario.getPermissao().getId().equals(1)) {
            mv = new ModelAndView("pages/dadosusuario");
            mv.addObject("usuario", usuario);
            mv.addObject("mens", true);
            return mv;

        } else {
            usuarioService.deletaLogsRecuperacaoUsuario(usuario);
            usuarioRepository.delete(usuario);
            SecurityContextHolder.clearContext();
            mv = new ModelAndView("redirect:/login");
            return mv;
        }
    }

    @RequestMapping("/redefinir")
    public ModelAndView atualizarSenha() {
        ModelAndView mv;
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("pages/redefinirsenha");
        mv.addObject("usuario", usuario);

        return mv;
    }

    @RequestMapping("/savesenha")
    public ModelAndView salvarNovaSenha(String senha, String senha1) {
        ModelAndView mv = new ModelAndView("pages/redefinirsenha");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (senha.equals(senha1)) {
            boolean retornosave = usuarioService.updateSenha(senha, usuario.getId());
            mv.addObject("success", retornosave);
            mv.addObject("usuario",usuario);
        } else {
            System.out.printf("Houve um erro senhas não são iguais");
        }
        return mv;
    }

    @RequestMapping("/recuperacao")
    public String recuperacaoEtapa1() {
        return "pages/verificaemail";
    }

    @PostMapping("/verifica")
    public ModelAndView veririficaEmail(String email) {
        ModelAndView mv = new ModelAndView("pages/verificaemail");
        boolean retorno = usuarioService.verificaEmail(email);
        mv.addObject("mensagem", true);
        mv.addObject("err", retorno);
        return mv;
    }

    @RequestMapping("/redefinirconta")
    public ModelAndView recuperaConta(@RequestParam String inkx) {
        RecuperacaoSenha recu = usuarioService.checaToken(inkx);
        if (recu != null) {
            ModelAndView mv = new ModelAndView("pages/recuperarconta");
            mv.addObject("token", inkx);
            return mv;
        } else {
            return new ModelAndView("pages/naoautorizado");
        }

    }

    @PostMapping("/recuperar")
    public ModelAndView atualizaConta(String token, String senha, String senha1) {
        if (senha.equals(senha1)) {
            boolean retorn = usuarioService.atualizarConta(senha, token);
            if (retorn) {
                return new ModelAndView("redirect:/login");
            } else {
                ModelAndView mv = new ModelAndView("pages/recuperarconta");
                mv.addObject("mens", retorn);
                return mv;
            }
        } else {
            ModelAndView mv = new ModelAndView("pages/recuperarconta");
            mv.addObject("mens", true);
            mv.addObject("token", token);
            return mv;
        }
    }
}
