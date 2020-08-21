package com.elisrs.service;

import com.elisrs.component.MailComponentService;
import com.elisrs.configMail.MensagemEmail;
import com.elisrs.enty.RecuperacaoSenha;
import com.elisrs.enty.Usuario;
import com.elisrs.repository.PermissaoRepository;
import com.elisrs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    MailComponentService mail;

    @Autowired
    RecuperaSenhaService recuperaSenhaService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PermissaoRepository permissaoRepository;


    public String criptografaSenha(String senha){
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
       String result = encoder.encode(senha);
        //System.out.printf(result);
       //assertTrue(encoder.matches(senha, result));

        return result;
    }

    public boolean comparaCripto(String senha, String senhasave){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return  encoder.matches(senha,senhasave);
    }

    public boolean updateSenha(String senha, int id){
        Optional<Usuario> userOp = usuarioRepository.findById(id);

        if(userOp.isPresent()){
            Usuario userAntigo = userOp.get();
            userAntigo.setSenha(criptografaSenha(senha));
            usuarioRepository.save(userAntigo);
            return true;
        }else{
            return false;
        }
    }
    public  boolean verificaEmail(String email){
        Optional<Usuario> userOp = usuarioRepository.findUsuarioByEmail(email);
        if(userOp.isPresent()){
            Usuario user = userOp.get();
            RecuperacaoSenha recuperacaoSenha = recuperaSenhaService.criaRecuperacao(user);
            if(recuperacaoSenha !=null){
                return enviarEmail(user, recuperacaoSenha.getCodigo());
            }else{
                return false;
            }

        }else{
            return false;
        }
    }

    public boolean enviarEmail(Usuario user, String codigo) {
        mail.enviarEmail(new MensagemEmail("Web Garden <er.paisagista@gmail.com>",
                user.getEmail(),
                "Esqueci minha senha",
                "Clique no link abaixo para recuperar sua senha\n" + "localhost:8080/redefinirconta?inkx=" + codigo));

        return true;
    }

    public boolean atualizarConta(String senha, String token){
        RecuperacaoSenha rec = checaToken(token);
        if(rec != null){
            return updateSenha(senha,rec.getUsuario().getId());
        }else{
            return false;
        }
    }
    public RecuperacaoSenha checaToken(String token){
        RecuperacaoSenha recsenha = recuperaSenhaService.buscarRecuperação(token);
        if (recsenha!=null){
            LocalDateTime localagora = LocalDateTime.now();
            System.out.println("expiraçã0 === "+ recsenha.getExpiracao().plusMinutes(30));
            System.out.println("agora == "+ localagora);
            if(recsenha.getExpiracao().plusMinutes(30).isAfter(localagora)){

                return recsenha;
            }else {
                System.out.println("token nao é valido");
                return null;
            }
        }else {
            System.out.println("token nao encontrado");
            return null;
        }
    }

    public void deletaLogsRecuperacaoUsuario(Usuario usuario){
        recuperaSenhaService.recuperaSenhaRepository.deleteByUsuarioId(usuario.getId());
    }

    public Usuario buscaUser(Integer id){
        Optional<Usuario> userOp = usuarioRepository.findById(id);
        if(userOp.isPresent()){
            Usuario user = userOp.get();
            return user;
        }else{

            return null;
        }
    }
}
