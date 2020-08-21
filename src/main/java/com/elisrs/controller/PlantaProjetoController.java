package com.elisrs.controller;

import com.elisrs.enty.Planta;
import com.elisrs.enty.Plantaprojeto;
import com.elisrs.enty.Projeto;
import com.elisrs.enty.Usuario;
import com.elisrs.service.PlantaProjetoService;
import com.elisrs.service.PlantaService;
import com.elisrs.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class PlantaProjetoController {

    @Autowired
    ProjetoService projetoService;
    @Autowired
    HttpServletRequest req;
    @Autowired
    PlantaService plantaService;
   @Autowired
    PlantaProjetoService plantaProjetoService;


   @RequestMapping("/adicionar")
    public ModelAndView adicionaPlantaProjeto(Integer idproj, Integer idplan){
        Boolean operaçao = plantaProjetoService.salvarPlantaProjeto(idproj,idplan);
        ModelAndView andView = new ModelAndView("pages/escolherplanta.html");
       Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       andView.addObject("usuario",usuario);
        Projeto  projeto = projetoService.buscaProjeto(idproj);
        List<Planta> plant = plantaService.findAllPlant();
        if(operaçao){
            andView.addObject("men",true);
        }else{
            andView.addObject("negado",true);
        }
        andView.addObject("plant",plant);
        andView.addObject("projeto",projeto);
        return andView;
    }

    @RequestMapping("/deletarplanta")
    public ModelAndView deletarPlanta(Integer idpp, Integer idproj){
        boolean bol= plantaProjetoService.exluirPlantaprojeto(idpp);
        ModelAndView andView = new ModelAndView("pages/editarprojeto.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        Iterable<Plantaprojeto> listaPlantas = plantaProjetoService.buscaPlantasProjeto(idproj);
        andView.addObject("plantas",listaPlantas);
        Projeto projeto = projetoService.buscaProjeto(idproj);
        andView.addObject("projeto",projeto);
            return andView;
   }

   @PostMapping("/salvarplantaemprojeto")
    public ResponseEntity adicionarPlantaEmProjeto(@RequestBody Map<String,Object> corpo){
       Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String idproj = (String) corpo.get("idprojeto");
       String idplan = (String) corpo.get("idplanta");
       Integer idplanta = Integer.parseInt(idplan);
       Integer idprojeto = Integer.parseInt(idproj);
       System.out.println(idprojeto);
       Boolean operacao = plantaProjetoService.salvarPlantaProjeto(idprojeto,idplanta);
       if(operacao){
           return new ResponseEntity(HttpStatus.OK);
       }else {
           return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
       }
   }
}
