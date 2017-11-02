package com.concrete.desafio.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.desafio.exception.TokenInvalidException;
import com.concrete.desafio.exception.UserAlreadyExistsException;
import com.concrete.desafio.key.ErrorKey;
import com.concrete.desafio.model.User;
import com.concrete.desafio.service.UserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	UserService userService;

	//-------------------Retorna Todas os Usuarios--------------------------------------------------------

//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<Usuario>> listAll() {
//		List<Usuario> listaUsuarios = usuarioService.findAll();
//		if(listaUsuarios.isEmpty()){
//			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
//	}


    //-------------------Retorna um unico Usuario pelo id--------------------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUsuario(@PathVariable("id") long id, @RequestHeader ("token") String token) throws TokenInvalidException  {
		System.out.println("Buscando Usuario com id " + id);
		System.out.println("Token: "+token);
		User user = userService.findById(id, token);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


    //-------------------Cria um Usuario--------------------------------------------------------

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> criaUsuario(@RequestBody User usuario) throws UserAlreadyExistsException {
		System.out.println("Criando Usuario " + usuario.getName());

		userService.save(usuario);

		return new ResponseEntity<User>(usuario, HttpStatus.CREATED);
	}


	@ExceptionHandler(TokenInvalidException.class)
	public ResponseEntity<ErrorKey> handlerTokenException() {
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("Não autorizado");
		return new ResponseEntity<ErrorKey>(errorKey, HttpStatus.UNAUTHORIZED);
	}


	  @ExceptionHandler(UserAlreadyExistsException.class)
	    public String handleException() throws JSONException {
		  Gson gson = new Gson();
		  ErrorKey errorKey = new ErrorKey();
		  errorKey.setMensagem("E-mail já existente");
		  String mensagemErroJson = gson.toJson(errorKey);
		  return mensagemErroJson;
	    }


    //------------------- Altera um Usuario --------------------------------------------------------

//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
//		System.out.println("Alterando Usuario " + id);
//
//		Usuario usuarioBanco = usuarioService.findById(id);
//
//		if (usuarioBanco==null) {
//			System.out.println("Usuario com id " + id + " nao encontrado");
//			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
//		}
//
//		usuarioBanco.setUsername(usuario.getUsername());
//		usuarioBanco.setEmail(usuario.getEmail());
//		usuarioBanco.setTelefone(usuario.getTelefone());
//
//		//usuarioService.update(usuarioBanco);
//		return new ResponseEntity<Usuario>(usuarioBanco, HttpStatus.OK);
//	}

}
