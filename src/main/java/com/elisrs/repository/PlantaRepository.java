package com.elisrs.repository;

import com.elisrs.enty.Planta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantaRepository extends JpaRepository<Planta,Integer>, PlantaRepositoryCustom {

    List<Planta> findPlantaByStatus(Boolean status);

}
