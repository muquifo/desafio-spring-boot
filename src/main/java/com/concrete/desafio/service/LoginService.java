package com.concrete.desafio.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafio.exception.UserInvalidException;
import com.concrete.desafio.model.User;
import com.concrete.desafio.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public User findByEmailPassword(String email, String password) throws UserInvalidException{

		User user = userRepository.findByEmail(email);

		if(user == null) {
			throw new UserInvalidException("Usuario e/ou senha invalidos");
		}else if(!user.getPassword().equals(password)){
			throw new UserInvalidException("Usuario e/ou senha invalidos");
		}

		User userAtualizado = updateLastLogin(user);

		return userAtualizado;
	}

	private User updateLastLogin(User user) {
		user.setLast_login(LocalDateTime.now());
		userRepository.flush();
		User userAtualizado = userRepository.findById(user.getId());
		return userAtualizado;
	}

}
