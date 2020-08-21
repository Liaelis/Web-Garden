package com.elisrs.service;

import com.elisrs.enty.Planta;
import com.elisrs.enty.Plantaprojeto;
import com.elisrs.enty.Projeto;
import com.elisrs.repository.PlantaProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PlantaProjetoService {

    @Autowired
    ProjetoService projetoService;
    @Autowired
    HttpServletRequest req;
    @Autowired
    PlantaService plantaService;
    @Autowired
    PlantaProjetoRepository plantaProjetoRepository;

    public boolean salvarPlantaProjeto(Integer idproj, Integer idplan){
        Projeto projeto = projetoService.buscaProjeto(idproj);
        Planta planta = plantaService.buscaPlanta(idplan);
        Plantaprojeto plantaprojeto = new Plantaprojeto();
        plantaprojeto.setPlanta(planta);
        plantaprojeto.setProjeto(projeto);
        plantaprojeto.setPrecoUnitario(BigDecimal.ZERO);
        plantaprojeto.setAreaquadrada(BigDecimal.ZERO);
        plantaprojeto.setQuantidade(0);
        Optional<Plantaprojeto> opp = plantaProjetoRepository.findByProjetoIdAndPlantaId(idproj,idplan);
        if(opp.isPresent()){
            Plantaprojeto plantaTest = opp.get();
            return false;
        }else{
            Plantaprojeto pp= plantaProjetoRepository.save(plantaprojeto);
            return true;
        }

    }

    public List<Plantaprojeto> buscaPlantasProjeto(Integer id){
        List<Plantaprojeto> list = plantaProjetoRepository.findByProjetoId(id);
        return list;
    }

    public boolean exluirPlantaprojeto(Integer id){
        plantaProjetoRepository.deleteById(id);

        return true;
    }

    public Integer buscaPlantaDeProjeto(Integer id){
        Optional<Plantaprojeto> pp = plantaProjetoRepository.findById(id);
        Plantaprojeto plantaprojeto = pp.get();
        return plantaprojeto.getPlanta().getId();
    }
}
