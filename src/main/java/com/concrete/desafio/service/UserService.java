package com.concrete.desafio.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafio.exception.SessaoInvalidException;
import com.concrete.desafio.exception.TokenInvalidException;
import com.concrete.desafio.exception.UserAlreadyExistsException;
import com.concrete.desafio.model.User;
import com.concrete.desafio.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) throws UserAlreadyExistsException{

		if(userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException("E-mail já existente");
		}

		LocalDateTime timeCreated = LocalDateTime.now();
		user.setCreated(timeCreated);
		user.setModified(timeCreated);
		user.setLast_login(timeCreated);
		user.setToken(UUID.randomUUID().toString());

		userRepository.save(user);
	}

	public User findById(Long id, String token) throws TokenInvalidException, SessaoInvalidException{

		User userToken = userRepository.findByToken(token);
		User userId = null;

		if(userToken == null) {
			throw new TokenInvalidException("Não autorizado");
		}else{
			userId = userRepository.findById(id);
			if(!userId.getToken().equals(token)) {
				throw new TokenInvalidException("Não autorizado");
			}else if(!tempoSessaoPermitido(userId.getLast_login())){
				throw new SessaoInvalidException("Sessão inválida");
			}
		}
		return userId;
	}

	private boolean tempoSessaoPermitido(LocalDateTime last_login) {
		boolean tempoPermitido = true;
		LocalDateTime dataTimeAtual = LocalDateTime.now();
		Duration duracao = Duration.between(last_login, dataTimeAtual);
		long tempoMinuto = (duracao.getSeconds() / 60);
		if(tempoMinuto > 60) {
			tempoPermitido = false;
		}
		return tempoPermitido;
	}

}
