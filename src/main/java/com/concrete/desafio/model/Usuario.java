package com.concrete.desafio.model;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "user")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_id")
	private Long id;

	@Column(name = "cd_token")
	private String token;

	@Column(name = "nm_user")
	private String username;

	@Column(name = "cd_password")
	private String password;

	@Column(name = "nm_email")
	private String email;

	@Column(name = "dt_created")
	private Data created;

	@Column(name = "dt_modified")
	private Data modified;

	@Column(name = "dt_last_login")
	private Data last_login;

	private Collection<Telefone> telefone;





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Data getCreated() {
		return created;
	}

	public void setCreated(Data created) {
		this.created = created;
	}

	public Data getModified() {
		return modified;
	}

	public void setModified(Data modified) {
		this.modified = modified;
	}

	public Data getLast_login() {
		return last_login;
	}

	public void setLast_login(Data last_login) {
		this.last_login = last_login;
	}

	public Collection<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(Collection<Telefone> telefone) {
		this.telefone = telefone;
	}

}
