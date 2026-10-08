package com.joaomelo.estoque_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_categoria")
public class Categoria {
//@ são anotações do JPA (Java Persistence API)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	//construtor padrão
	public Categoria() {
	}
	
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	//getters and setters( servem para acessar e modificar os atributos
	//privados de uma classe de forma segura e controlada)
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
