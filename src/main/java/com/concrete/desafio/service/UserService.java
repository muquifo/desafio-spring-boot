package com.concrete.desafio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByUsername(String name) {
		return userRepository.findByName(name);
	}

	public User findById(Long id, String token) throws TokenInvalidException{

		User userToken = userRepository.findByToken(token);

		if(userToken == null) {
			throw new TokenInvalidException("Não autorizado");
		}

		return userRepository.findById(id);
	}


}
