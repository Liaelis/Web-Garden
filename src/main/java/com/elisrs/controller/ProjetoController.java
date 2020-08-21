package com.elisrs.controller;

import com.elisrs.enty.Planta;
import com.elisrs.enty.Plantaprojeto;
import com.elisrs.enty.Projeto;
import com.elisrs.enty.Usuario;
import com.elisrs.service.PlantaProjetoService;
import com.elisrs.service.PlantaService;
import com.elisrs.service.ProjetoService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjetoController {

    @Autowired
    ProjetoService projetoService;
    @Autowired
    HttpServletRequest req;
    @Autowired
    PlantaService plantaService;
    @Autowired
    PlantaProjetoService plantaProjetoService;


    //Validator validator = ESAPI.validator();

    @PostMapping("/criaprojeto")
    public ModelAndView createProject(Projeto projeto){
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Encoder encoder = ESAPI.encoder();
        String nomeProjeto = encoder.canonicalize(projeto.getNomeprojeto(),true,false);
        projeto.setNomeprojeto(nomeProjeto);
        System.out.println(nomeProjeto);
        Projeto projetoNovo = projetoService.salvarProjeto(projeto,usuario);
        ModelAndView andView = new ModelAndView("pages/escolherplanta.html");
        andView.addObject("usuario",usuario);
        List<Planta> plant = plantaService.findAllPlant();
        andView.addObject("plant",plant);
        andView.addObject("proj",projetoNovo);
        return andView;
    }

    @RequestMapping("/selecionarmais")
    public ModelAndView addMaisPlanta(Integer id){
        Projeto projeto = projetoService.buscaProjeto(id);
        ModelAndView andView = new ModelAndView("pages/escolherplanta.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        List<Planta> plant = plantaService.findAllPlant();
        andView.addObject("plant",plant);
        andView.addObject("projeto",projeto);
        return  andView;
    }

    @RequestMapping("/verprojeto")
    public  ModelAndView verProjeto(Integer id){
        ModelAndView andView= new ModelAndView("pages/verprojeto.html");
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

    @RequestMapping("/edicaoprojeto")
    public ModelAndView edicaoDeProjeto(Integer id){
    	ModelAndView andView =new ModelAndView("pages/editarprojeto.html");
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

    @PostMapping("/updateprojeto")
    public ModelAndView update(Projeto projeto){
        ModelAndView andView =new ModelAndView("pages/editarprojeto.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        List<Plantaprojeto> listaPlantas = plantaProjetoService.buscaPlantasProjeto(projeto.getId());
        Projeto projetoNovo = projetoService.editarProjeto(projeto);
        andView.addObject("plantas",listaPlantas);
        andView.addObject("projeto", projetoNovo);
        return andView;

    }

    @RequestMapping("/deleteprojeto")
    public ModelAndView delete(Integer id){
        ModelAndView andView =  new ModelAndView("redirect:/testauser");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        boolean resposta = projetoService.exluirProjeto(id);

        return andView;
    }

}
