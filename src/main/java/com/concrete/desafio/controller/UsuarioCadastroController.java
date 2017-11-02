package com.concrete.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concrete.desafio.model.Telefone;
import com.concrete.desafio.model.Usuario;
import com.concrete.desafio.service.UsuarioService;

@RestController
@RequestMapping("/cadastro")
public class UsuarioCadastroController {

	@Autowired
	UsuarioService usuarioService;


	//-------------------Retorna Todas os Usuarios--------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> listaUsuarios = usuarioService.findAll();
		if(listaUsuarios.isEmpty()){
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
	}


    //-------------------Retorna um unico Usuario pelo id--------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") long id) {
		System.out.println("Buscando Usuario com id " + id);
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			System.out.println("Usuario com id " + id + " nao encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}


    //-------------------Cria um Usuario--------------------------------------------------------

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createUsuario(@RequestBody Usuario usuario,@RequestBody Telefone telefone, UriComponentsBuilder ucBuilder) {
		System.out.println("Criando Usuario " + usuario.getUsername());

		usuarioService.save(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


    //------------------- Altera um Usuario --------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		System.out.println("Alterando Usuario " + id);

		Usuario usuarioBanco = usuarioService.findById(id);

		if (usuarioBanco==null) {
			System.out.println("Usuario com id " + id + " nao encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		usuarioBanco.setUsername(usuario.getUsername());
		usuarioBanco.setEmail(usuario.getEmail());
		usuarioBanco.setTelefone(usuario.getTelefone());

		//usuarioService.update(usuarioBanco);
		return new ResponseEntity<Usuario>(usuarioBanco, HttpStatus.OK);
	}

}
