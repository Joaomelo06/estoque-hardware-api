package com.joaomelo.estoque_api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joaomelo.estoque_api.model.Produto;
import com.joaomelo.estoque_api.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service//serve para indicar que uma classe pertence à camada de 
//serviço e contém a lógica de negócios da aplicação
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	// Lista todos o produto
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	
	//buscar produto por ID com Tratamento de erro(Exception)
	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Produto não encontrado com o ID:" + id));
	}
	
	@Transactional
	//salva ou atualizar produto com validações
	public Produto salvar(Produto produto) {
		
		if(produto.getPreco() == null || produto.getPreco().
				compareTo(BigDecimal.ZERO) <=0) {
			throw new RuntimeException("O preço do produto deve ser maior que zero. ");
		}
		
		if(produto.getQuantidadeEstoque() == null || produto.getQuantidadeEstoque() <0) {
			throw new RuntimeException(" A quantidade em estoque não pode er negativa");
		}
		return produtoRepository.save(produto);
	}
	
	
	@Transactional
	public Produto darBaixaEstoque(Long produtoId, Integer quantidadeVendida) {
		Produto produto = buscarPorId(produtoId);
		
		
		if(quantidadeVendida <=0 ) {
			throw new RuntimeException("A quantidade para baixa deve ser mairo que zero");
		}
		//validação estoque 
		if(produto.getQuantidadeEstoque() < quantidadeVendida) {
			throw new RuntimeException("Estoque insuficiente para a venda! Saldo atual: " 
			+ produto.getQuantidadeEstoque() + " unidade");
		}
		
		// Atualiza a quantidade e salva no banco
		int novoEstoque = produto.getQuantidadeEstoque() - quantidadeVendida;
		produto.setQuantidadeEstoque(novoEstoque);
		
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public Produto reporEstoque(Long ProdutoId, Integer quantidadeEntrada) {
		Produto produto = buscarPorId(ProdutoId);
		
		if(quantidadeEntrada <= 0) {
			throw new RuntimeException("Quantidade de reposição deve ser mairo que Zero");
		}
		
		int novoEstoque = produto.getQuantidadeEstoque()+ quantidadeEntrada;
		produto.setQuantidadeEstoque(novoEstoque);
		
		return produtoRepository.save(produto);
	}
	//  Deletar produto por ID
    @Transactional
	public void deletar(Long Id) {
    	Produto produto = buscarPorId(Id);
    	produtoRepository.delete(produto);
    }
	
}
