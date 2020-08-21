package com.elisrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elisrs.enty.Projeto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>{

 List<Projeto> findByUsuarioId(Integer idUsuario);
}
