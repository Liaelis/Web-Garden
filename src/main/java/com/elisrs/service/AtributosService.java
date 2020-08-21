package com.elisrs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.elisrs.enty.Categoriavegetal;
import com.elisrs.enty.Clima;
import com.elisrs.enty.Cordaflor;
import com.elisrs.enty.Cordafolha;
import com.elisrs.enty.Exposicaosolar;
import com.elisrs.enty.Formadaplanta;
import com.elisrs.enty.Persistencia;
import com.elisrs.enty.Texturafolha;
import com.elisrs.enty.Texturatronco;
import com.elisrs.repository.CategoriaRepository;
import com.elisrs.repository.ClimaRepository;
import com.elisrs.repository.CordaflorRepository;
import com.elisrs.repository.CordafolhaRepository;
import com.elisrs.repository.ExposicaosolarRepository;
import com.elisrs.repository.FormadaplantaRepository;
import com.elisrs.repository.PersistenciaRepository;
import com.elisrs.repository.ProjetoRepository;
import com.elisrs.repository.TexturafolhaRepository;
import com.elisrs.repository.TexturatroncoRepository;

@Service
public class AtributosService {
	@Autowired
	TexturafolhaRepository textfolhaRepository;
	@Autowired
	TexturatroncoRepository texttroncoRespository;
	@Autowired
	FormadaplantaRepository formaRepository;
	@Autowired
	PersistenciaRepository persistenciaRepository;
	@Autowired
	ExposicaosolarRepository exposicaoRepository;
	@Autowired
	CordafolhaRepository corfolhaRepository;
	@Autowired
	CordaflorRepository corflorRepository;
	@Autowired
	ClimaRepository climaRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Texturatronco> buscaTexturaTronco() {
		return texttroncoRespository.findAll();
	
	}
	public List<Texturafolha> buscaTexturaFolha(){
		return textfolhaRepository.findAll();
	}
	
	public List<Cordafolha> buscaCordaFolha(){
		return corfolhaRepository.findAll();
	}
	
	public List<Cordaflor> buscaCordaFlor(){
		return corflorRepository.findAll();
	}
	
	public List<Formadaplanta> buscaForma(){
		return formaRepository.findAll();
	}
	
	public List<Exposicaosolar> buscaExposicao(){
		return exposicaoRepository.findAll();
	}
	
	public List<Persistencia> buscaPersistencia(){
		return persistenciaRepository.findAll();
	}
	
	public List<Categoriavegetal> buscaCategoria(){
		return categoriaRepository.findAll();
	}
	
	public List<Clima> buscaClima(){
		return climaRepository.findAll();
	}
	
 
}
