package com.elisrs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.elisrs.enty.Projeto;
import com.elisrs.enty.Usuario;
import com.elisrs.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.elisrs.service.ProjetoService;
import com.elisrs.service.AtributosService;

import java.util.List;

@Controller
public class AreaRestritaAdministrador {
    //repositorys criados
    @Autowired
    private AtributosService at;

    @Autowired
    private ProjetoService ps;
    @Autowired
    HttpServletRequest req;

    //carrega a pagina
    @RequestMapping("/areaadm")
    public ModelAndView areaUsuario() {
        ModelAndView andV = new ModelAndView("pages/arearestritaAdministrador");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andV.addObject("txt", at.buscaTexturaTronco());
        andV.addObject("txf", at.buscaTexturaFolha());
        andV.addObject("categ", at.buscaCategoria());
        andV.addObject("forma", at.buscaForma());
        andV.addObject("corflor", at.buscaCordaFlor());
        andV.addObject("corfolha", at.buscaCordaFolha());
        andV.addObject("persis", at.buscaPersistencia());
        andV.addObject("cli", at.buscaClima());
        andV.addObject("esp", at.buscaExposicao());
        andV.addObject("usuario", usuario);
        List<Projeto> listaprojetos = ps.listaProjeto(usuario);
        if (listaprojetos.isEmpty()) {
            andV.addObject("mens", true);
        } else {
            andV.addObject("project", listaprojetos);
        }


        return andV;
    }


}
