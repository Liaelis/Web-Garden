package com.elisrs.repository;

import com.elisrs.enty.RecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RecuperaSenhaRepository extends JpaRepository<RecuperacaoSenha, Integer> {
    Optional<RecuperacaoSenha> findByCodigo(String codigo);
    @Transactional
     void  deleteByUsuarioId(Integer idusuario);
}
