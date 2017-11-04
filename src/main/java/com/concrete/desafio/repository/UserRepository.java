package com.concrete.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concrete.desafio.model.User;



@Repository 
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findById(Long id);
	User findByToken(String token);
}