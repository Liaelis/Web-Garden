package com.elisrs.controller;

import com.elisrs.enty.Projeto;
import com.elisrs.enty.Usuario;
import com.elisrs.repository.UsuarioRepository;
import com.elisrs.service.AtributosService;
import com.elisrs.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class AreaRestritaUsuarioController {

	@Autowired
	private AtributosService at;

	@Autowired
	private ProjetoService ps;
	@Autowired
	HttpServletRequest req;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping("/areausuer")
	public ModelAndView areaUsuario() {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView andV = new ModelAndView("pages/arearestritaUsuario");
		andV.addObject("txt",at.buscaTexturaTronco());
		andV.addObject("txf", at.buscaTexturaFolha());
		andV.addObject("categ", at.buscaCategoria());
		andV.addObject("forma", at.buscaForma());
		andV.addObject("corflor",at.buscaCordaFlor());
		andV.addObject("corfolha",at.buscaCordaFolha());
		andV.addObject("persis",at.buscaPersistencia());
		andV.addObject("cli",at.buscaClima());
		andV.addObject("esp",at.buscaExposicao());
		List<Projeto> listaprojetos = ps.listaProjeto(usuario);
		andV.addObject("usuario",usuario);
		if(listaprojetos.isEmpty()){
			andV.addObject("mens",true);
		}else{
			andV.addObject("project",listaprojetos );
		}


		return andV;

	}
}
