package com.joaomelo.estoque_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaomelo.estoque_api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	//Derived Query (consulta pelo nome do metodo)
	List<Produto> findByMarca(String marca);
	
	// Buscar produtos com estoque abaixo de um determinado limite(Menor que)
	List<Produto> findByQuantidadeEstoqueLessThan(Integer quantidade);
}
