package com.concrete.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafio.model.Usuario;
import com.concrete.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;


	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id);
	}


}
