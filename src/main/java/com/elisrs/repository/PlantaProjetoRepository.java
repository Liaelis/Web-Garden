package com.elisrs.repository;

import com.elisrs.enty.Planta;
import com.elisrs.enty.Plantaprojeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantaProjetoRepository extends JpaRepository<Plantaprojeto, Integer> {

    public List<Plantaprojeto> findByProjetoId(Integer id);
    public Optional <Plantaprojeto> findByProjetoIdAndPlantaId(Integer idprojeto, Integer idplanta);
}
