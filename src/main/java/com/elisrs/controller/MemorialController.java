package com.elisrs.controller;

import com.elisrs.enty.Plantaprojeto;
import com.elisrs.enty.Projeto;
import com.elisrs.enty.Usuario;
import com.elisrs.service.PlantaProjetoService;
import com.elisrs.service.PlantaService;
import com.elisrs.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemorialController {
    @Autowired
    ProjetoService projetoService;
    @Autowired
    HttpServletRequest req;
    @Autowired
    PlantaService plantaService;
    @Autowired
    PlantaProjetoService plantaProjetoService;
    @RequestMapping("/vermemorial")
    public ModelAndView verMemorial(Integer id){
        ModelAndView andView= new ModelAndView("pages/memorial.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        List<Plantaprojeto> listaPlantas = plantaProjetoService.buscaPlantasProjeto(id);
        if(listaPlantas.isEmpty()){
            andView.addObject("mens",true);
        }
        andView.addObject("plantas",listaPlantas);
        Projeto projeto = projetoService.buscaProjeto(id);
        andView.addObject("projeto",projeto);
        return andView;
    }
}
