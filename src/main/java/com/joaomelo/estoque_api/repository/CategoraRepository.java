package com.joaomelo.estoque_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaomelo.estoque_api.model.Categoria;

@Repository
public interface CategoraRepository extends JpaRepository<Categoria, Long> {
	// Todos os métodos básicos de CRUD já estão herdados aqui!
	//CRUD é um acrônimo para Create, Read, Update e Delete,
	//representando as quatro operações básicas para gerenciar um banco de dados
}
