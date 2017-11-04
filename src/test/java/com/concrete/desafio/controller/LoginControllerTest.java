package com.concrete.desafio.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.concrete.desafio.key.ErrorKey;
import com.concrete.desafio.model.User;
import com.concrete.desafio.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	private static final String EMAIL_VALIDO = "teste@teste.com.br";
	private static final String PASSWORD_VALIDO = "teste1234";

	User USER_VALIDO = null;

	@Mock
	LoginService loginService;

	@InjectMocks
	LoginController loginController;

	@Before
	public void setUp() {
		USER_VALIDO = new User();
	}

	@Test
	public void deveFazerLoginComSucessoRetornandoUsuario() {
		ResponseEntity<User> userResponse  = loginController.fazendoLogin(USER_VALIDO);
		when(loginService.findByEmailPassword(EMAIL_VALIDO, PASSWORD_VALIDO)).thenReturn(USER_VALIDO);
		assertNotNull(userResponse);
	}

	@Test
	public void deveTratarErroUserInvalid() {
		ResponseEntity<ErrorKey> errorResponse  = loginController.handlerUserInvalidException();
		assertNotNull(errorResponse);
	}

}
