package com.elisrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elisrs.enty.Categoriavegetal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoriavegetal, Integer> {

//    @Query("SELECT c FROM Categoriavegetal c where c.id in: refs")
//    List<Categoriavegetal> findByIds (@Param("refs") List<Integer> refs);

}
