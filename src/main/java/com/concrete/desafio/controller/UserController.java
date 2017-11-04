package com.concrete.desafio.controller;

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

import com.concrete.desafio.exception.SessaoInvalidException;
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUsuario(@PathVariable("id") long id, @RequestHeader ("token") String token) throws TokenInvalidException, SessaoInvalidException  {
		User user = userService.findById(id, token);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> criaUsuario(@RequestBody User user) throws UserAlreadyExistsException {
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@ExceptionHandler(TokenInvalidException.class)
	public ResponseEntity<ErrorKey> handlerTokenInvalidException() {
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("Não autorizado");
		return new ResponseEntity<ErrorKey>(errorKey, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public String handleUserAlreadyExistsException(){
		Gson gson = new Gson();
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("E-mail já existente");
		String mensagemErroJson = gson.toJson(errorKey);
		return mensagemErroJson;
	}

	@ExceptionHandler(SessaoInvalidException.class)
	public ResponseEntity<ErrorKey> handlerSessaoInvalidException() {
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("Sessão inválida");
		return new ResponseEntity<ErrorKey>(errorKey, HttpStatus.UNAUTHORIZED);
	}

}
