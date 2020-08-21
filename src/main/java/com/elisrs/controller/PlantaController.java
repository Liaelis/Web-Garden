package com.elisrs.controller;

import com.elisrs.dto.AtributoPlantaDTO;
import com.elisrs.dto.PlantaDTO;
import com.elisrs.enty.*;
import com.elisrs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class PlantaController {
    @Autowired
    PlantaService plantaService;
    @Autowired
    FotoService fotoService;

    @Autowired
    HttpServletRequest req;
    @Autowired
    ProjetoService projetoService;
    @Autowired
    PlantaProjetoService plantaProjetoService;
   @Autowired
    AtributosService atributosService;


    @PostMapping("/salvarplanta")
    public ModelAndView salvarPlanta(@ModelAttribute PlantaDTO planta){
        ModelAndView andView = new ModelAndView("pages/salvarfoto.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
       Planta plant = plantaService.salvarPlantinha(planta);
       andView.addObject("planta",plant);
       return andView;

    }
    @ModelAttribute(name="plantaDTO")
    public PlantaDTO initvalue(){
        return new PlantaDTO();
    }

    @PostMapping("/salvarfotoplanta")
    public ModelAndView salvarFotoDePlanta(Integer id, @RequestParam("imagemplanta") MultipartFile multipartFile) throws IOException {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView andView = new ModelAndView("redirect:/testauser");
        andView.addObject("usuario",usuario);
        boolean bol = fotoService.salvarFoto(id,multipartFile);
        return andView;

    }
    // vê a planta apartir do projeto que ela está inserida
    @RequestMapping("/verplanta")
    public ModelAndView verPlanta( Integer idpp){
        ModelAndView andView = new ModelAndView("pages/planta.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        if(usuario.getPermissao().getId().equals(1)) {
            andView.addObject("permi",true);
        }
        Iterable<Projeto> listaProjeto = projetoService.listaProjeto(usuario);
        Integer idplanta = plantaProjetoService.buscaPlantaDeProjeto(idpp);
        List<Formadaplanta> forma = atributosService.buscaForma();
        List<Categoriavegetal> categorias = atributosService.buscaCategoria();
        Planta planta = plantaService.buscaPlanta(idplanta);
        andView.addObject("planta",planta);
        andView.addObject("proj",listaProjeto);
        return  andView;

   }

   // vê a planta apartir estando inserida ou não em um projeto
    @RequestMapping("/infoplanta")
   public ModelAndView verSoPlanta(Integer idplanta){
        ModelAndView andView = new ModelAndView("pages/planta.html");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        andView.addObject("usuario",usuario);
        if(usuario.getPermissao().getId().equals(1)) {
            andView.addObject("permi",true);
        }
       Iterable<Projeto> listaProjeto = projetoService.listaProjeto(usuario);
       Planta planta = plantaService.buscaPlanta(idplanta);
       if(!planta.isStatus()){
           andView.addObject("desativada",true);
       }
       andView.addObject("planta",planta);
       andView.addObject("proj",listaProjeto);
       return andView;
   }

   @PostMapping("/buscar")
    public ModelAndView buscarPersonalizada(@ModelAttribute AtributoPlantaDTO atributoPlantaDTO){
        ModelAndView andView = new ModelAndView("pages/resultadoplanta.html");
       Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       andView.addObject("usuario",usuario);
       Iterable<Projeto> listaProjeto = projetoService.listaProjeto(usuario);
        List<Planta> listaResultado = plantaService.buscaPorAtributo(atributoPlantaDTO);
        if(listaResultado.isEmpty()){
           andView.addObject("obj",true);
        }else {
            andView.addObject("plant",listaResultado);
            andView.addObject("proj",listaProjeto);

        }
       return andView;
   }
    @ModelAttribute(name="atributoPlantaDTO")
    public AtributoPlantaDTO initvaluee(){
        return new AtributoPlantaDTO();
    }

    @RequestMapping("/desativar")
    public RedirectView desativarPlanta(Integer idplanta){
        Planta planta = plantaService.buscaPlanta(idplanta);
        plantaService.desativa(planta);
       RedirectView redirectView = new RedirectView("/infoplanta");
        redirectView.addStaticAttribute("idplanta",idplanta);
        return redirectView;
    }
}
