package com.concrete.desafio.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.concrete.desafio.key.ErrorKey;
import com.concrete.desafio.model.User;
import com.concrete.desafio.service.UserService;
import com.google.gson.Gson;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private static final String TOKEN_VALIDO = "1f896037-95f4-4028-8c81-6194f5fa9c8c";
	private static final Long ID_VALIDO = 123456L;
	User USER_VALIDO = null;

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@Before
	public void setUp() {
		USER_VALIDO = new User();
	}

	@Test
	public void deveRetornarUmusuarioComSucessoQuandoBuscarPeloIdEToken() {
		when(userService.findById(ID_VALIDO, TOKEN_VALIDO)).thenReturn(USER_VALIDO);
		ResponseEntity<User> userResponse = userController.getUsuario(ID_VALIDO, TOKEN_VALIDO);
		assertNotNull(userResponse);
	}

	@Test
	public void deveCreiarUmUsuarioComSucesso() {
		ResponseEntity<User> userResponse  = userController.criaUsuario(USER_VALIDO);
		verify(userService, times(1)).save(USER_VALIDO);
		assertNotNull(userResponse);
	}

	@Test
	public void deveTratarErroTokenInvalido() {
		ResponseEntity<ErrorKey> errorResponse  = userController.handlerTokenInvalidException();
		assertNotNull(errorResponse);
	}

	@Test
	public void deveTratarErroUsuarioEmailExistente() throws JSONException {
		String erroMensagem = userController.handleUserAlreadyExistsException();
		Gson gson = new Gson();
		ErrorKey errorKey = gson.fromJson(erroMensagem, ErrorKey.class);
		assertNotNull(errorKey.getMensagem());
		assertNotNull(erroMensagem);
	}

	@Test
	public void deveTratarSessaoInvalida() throws JSONException {
		ResponseEntity<ErrorKey> errorResponse  = userController.handlerSessaoInvalidException();
		assertNotNull(errorResponse);
	}

}
