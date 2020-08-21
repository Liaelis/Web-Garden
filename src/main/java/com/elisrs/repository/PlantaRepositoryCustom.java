package com.elisrs.repository;

import com.elisrs.dto.AtributoPlantaDTO;
import com.elisrs.dto.PlantaDTO;
import com.elisrs.enty.Planta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlantaRepositoryCustom {
    List<Planta> findPlant(AtributoPlantaDTO filter);
}
