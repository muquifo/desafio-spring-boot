package com.concrete.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concrete.desafio.model.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
	List<Usuario> findAll();
	Usuario findById(Long id);
}

