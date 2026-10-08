package com.joaomelo.estoque_api.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity//Avisa ao Spring: "Trate essa classe como uma tabela no banco de dados."
@Table(name = "tb_produto")
public class Produto {

	@Id// define que é a chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Diz ao banco para gerar o ID de forma automática e auto-incrementada (1, 2, 3...).
	private Long id;
	private String nome;
	private String marca;
	private BigDecimal preco;//usado para valores monetarios
	private Integer quantidadeEstoque;
	
	@ManyToOne // muito produto pertencem a uma categoria, ou seja, muito produtos podem estar em uma mesma categoria
	@JoinColumn(name = "categoria_id")//nome da coluna de chave Estrangeira
	private Categoria categoria;
	
	public Produto() {
		
	}
	
	public Produto(Long id, String nome, String marca,
		BigDecimal preco, Integer quantidadeEstoque, Categoria categoria) {
	this.id = id;
	this.nome = nome;
	this.marca = marca;
	this.preco = preco;
	this.quantidadeEstoque = quantidadeEstoque;
	this.categoria = categoria;
	
	}

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

	public String getMarca() {
	return marca;
}

	public void setMarca(String marca) {
	this.marca = marca;
}

	public BigDecimal getPreco() {
	return preco;
}

	public void setPreco(BigDecimal preco) {
	this.preco = preco;
}

	public Integer getQuantidadeEstoque() {
	return quantidadeEstoque;
}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
	this.quantidadeEstoque = quantidadeEstoque;
}

	public Categoria getCategoria() {
	return categoria;
}

	public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}
	


}
