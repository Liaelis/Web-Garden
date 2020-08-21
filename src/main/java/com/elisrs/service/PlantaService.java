package com.elisrs.service;

import com.elisrs.dto.AtributoPlantaDTO;
import com.elisrs.dto.PlantaDTO;
import com.elisrs.enty.Persistencia;
import com.elisrs.enty.Planta;
import com.elisrs.repository.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

            @Autowired
            PlantaRepository plantaRepository;
            @Autowired
            PersistenciaRepository persistenciaRepository;
            @Autowired
            CategoriaRepository categoriaRepository;
            @Autowired
            CordaflorRepository cordaflorRepository;
            @Autowired
            CordafolhaRepository cordafolhaRepository;
            @Autowired
            TexturafolhaRepository texturafolhaRepository;
            @Autowired
            TexturatroncoRepository texturatroncoRepository;
            @Autowired
            ExposicaosolarRepository exposicaosolarRepository;
            @Autowired
            FormadaplantaRepository formadaplantaRepository;
            @Autowired
            ClimaRepository climaRepository;
            @Autowired
            FotoRepository fotoRepository;


            public Planta salvarPlantinha(PlantaDTO plantaDTO){
                Planta planta = new Planta();
                planta.setNomecientifico(plantaDTO.getNomecientifico());
                planta.setNomepopular(plantaDTO.getNomepopular());
                planta.setAltura(plantaDTO.getAltura());
                planta.setDiametro(plantaDTO.getDiametro());
                planta.setStatus(true);
                Optional<Persistencia> tipoPersistencia = persistenciaRepository.findById(plantaDTO.getPersistencia());
                planta.setPersistencia(tipoPersistencia.get());
                Planta plantaSalva = plantaRepository.save(planta);
                plantaSalva.setCategoriavegetals(categoriaRepository.findAllById(plantaDTO.getCategorias()));
                plantaSalva.setCordaflors(cordaflorRepository.findAllById(plantaDTO.getCordasflores()));
                plantaSalva.setCordafolhas(cordafolhaRepository.findAllById(plantaDTO.getCordasfolhas()));
                plantaSalva.setClimas(climaRepository.findAllById(plantaDTO.getClimas()));
                plantaSalva.setExposicaosolars(exposicaosolarRepository.findAllById(plantaDTO.getExposicoes()));
                plantaSalva.setTexturafolhas(texturafolhaRepository.findAllById(plantaDTO.getTexturasdafolha()));
                plantaSalva.setTexturatroncos(texturatroncoRepository.findAllById(plantaDTO.getTexturasdotronco()));
                plantaSalva.setFormadaplantas(formadaplantaRepository.findAllById(plantaDTO.getFormas()));
                plantaRepository.save(plantaSalva);
                return plantaSalva;


            }

            public List<Planta> findAllPlant(){
                boolean bol = true;
                List<Planta> listaPlanta = plantaRepository.findPlantaByStatus(bol);
                return listaPlanta;

            }

            public Planta buscaPlanta(Integer id){
                Optional<Planta>opPlanta = plantaRepository.findById(id);
                Planta planta = opPlanta.get();
                return planta;
            }

            public List<Planta> buscaPorAtributo(AtributoPlantaDTO atributosPlantaDTO){
                List<Planta> plantas = plantaRepository.findPlant(atributosPlantaDTO);
                return plantas;
            }

            public boolean desativa(Planta planta){
                planta.setStatus(false);
                plantaRepository.save(planta);
                return true;
            }

}
