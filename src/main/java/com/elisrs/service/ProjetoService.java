package com.elisrs.service;

import com.elisrs.enty.Plantaprojeto;
import com.elisrs.enty.Projeto;
import com.elisrs.enty.Usuario;
import com.elisrs.repository.PlantaProjetoRepository;
import com.elisrs.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoRepository projetoRepository;
    @Autowired
    PlantaProjetoRepository plantaProjetoRepository;

    public Projeto salvarProjeto(Projeto projeto,Usuario usuario){
    		projeto.setUsuario(usuario);
            projetoRepository.save(projeto);
            return projeto;
    }

    public List<Projeto> listaProjeto(Usuario usuario){

       List<Projeto> projetosUser = projetoRepository.findByUsuarioId(usuario.getId());
       return projetosUser;
    }

    public Projeto buscaProjeto(Integer id){
        Optional<Projeto> proj = projetoRepository.findById(id);
        if(proj.isPresent()){
            Projeto projeto = proj.get();
            return projeto;
        }else{
            System.out.println("Deu ruim");
            return null;
        }
    }

    public Projeto editarProjeto(Projeto projetoNovo){
        Optional<Projeto> optionalProjetoAntigo= projetoRepository.findById(projetoNovo.getId());
        Projeto projetoAntigo = optionalProjetoAntigo.get();
        projetoNovo.setUsuario(projetoAntigo.getUsuario());
        return projetoRepository.save(projetoNovo);
    }

    public Boolean exluirProjeto(Integer id){
        Iterable<Plantaprojeto> listplantaprojeto = plantaProjetoRepository.findByProjetoId(id);
        plantaProjetoRepository.deleteAll(listplantaprojeto);
        projetoRepository.deleteById(id);
        Optional<Projeto> testaProjeto = projetoRepository.findById(id);
        if(testaProjeto.isPresent()){
            System.out.println("Não deletou");
            return false;
        }else{
            System.out.println("deletou");
            return true;
        }

    }


}
