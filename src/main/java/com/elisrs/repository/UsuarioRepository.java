package com.elisrs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elisrs.enty.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha);
	public Optional<Usuario> findUsuarioByEmail(String email);

}
