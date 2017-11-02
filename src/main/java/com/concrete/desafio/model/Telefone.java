package com.concrete.desafio.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "telefone")
public class Telefone {

	@Column(name = "nm_number")
	private Long number;
	
	@Column(name = "nm_ddd")
	private String ddd;




	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
}
