package com.concrete.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.desafio.exception.UserInvalidException;
import com.concrete.desafio.key.ErrorKey;
import com.concrete.desafio.model.User;
import com.concrete.desafio.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> fazendoLogin(@RequestBody User user) throws UserInvalidException {
		User userEmail = loginService.findByEmailPassword(user.getEmail(), user.getPassword());
		return new ResponseEntity<User>(userEmail, HttpStatus.OK);
	}

	@ExceptionHandler(UserInvalidException.class)
	public ResponseEntity<ErrorKey> handlerTokenException() {
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("Usuário e/ou senha inválidos");
		return new ResponseEntity<ErrorKey>(errorKey, HttpStatus.UNAUTHORIZED);
	}

}
