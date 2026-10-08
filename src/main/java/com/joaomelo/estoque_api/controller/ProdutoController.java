package com.joaomelo.estoque_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaomelo.estoque_api.model.Produto;
import com.joaomelo.estoque_api.service.ProdutoService;

@RestController //Diz ao Spring que esta classe vai responder requisições Web
//e serializar as respostas automaticamente para JSON.
@RequestMapping("/api/produtos")//Define a URL base para todos os endpoints deste controller.
public class ProdutoController {
	private final ProdutoService produtoService;
	
	//injeção de dependencia do noo service
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	// GET: http://localhost:8080/api/produtos
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos(){
		List<Produto> produtos = produtoService.listarTodos();
		return ResponseEntity.ok(produtos);// Retorna HTTP 200 OK com a lista no corpo
	}
	
	// GET: http://localhost:8080/api/produtos/1
	@GetMapping("/{id}")
	
	//@PathVariable: Captura parâmetros passados na própria URL o ID /1 exemplo
	public ResponseEntity<Produto> buscarPorId(@PathVariable("id") Long Id){
		Produto produto = produtoService.buscarPorId(Id);
		return ResponseEntity.ok(produto);
	}

	// POST: http://localhost:8080/api/produtos
	@PostMapping                       
	
	//@RequestBody: Pega o JSON enviado no corpo da requisição e 
	//transforma em um objeto Java
	public ResponseEntity<Produto> criar(@RequestBody Produto produto){
		Produto novoProduto = produtoService.salvar(produto);
		// Retorna HTTP 201 CREATED (Padrão para criação de recursos)
		return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
		
	}

	// PUT: http://localhost:8080/api/produtos/1/baixa?quantidade=2
	@PutMapping("/{id}/baixa")
	public ResponseEntity<Produto> darBaixaEstoque(
			@PathVariable("id") Long Id,
			@RequestParam("quantidade") Integer quantidade){
		Produto produtoAtualizado = produtoService.darBaixaEstoque(Id, quantidade);
		return ResponseEntity.ok(produtoAtualizado);
	}
	
	// PUT: http://localhost:8080/api/produtos/1/reposicao?quantidade=5
	@PutMapping("/{id}/reposicao")
	
	//@RequestParam: Captura parâmetros passados como query string = 2
	public ResponseEntity<Produto> reporEstoque(
			@PathVariable("id") Long Id,
			@RequestParam("quantidade") Integer quantidade){
		Produto produtoAtualizado = produtoService.reporEstoque(Id, quantidade);
		return ResponseEntity.ok(produtoAtualizado);
	}
	
	// DELETE: http://localhost:8080/api/produtos/1
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long Id){
		produtoService.deletar(Id);
		return ResponseEntity.noContent().build(); // Retorna HTTP 204 No Content
	}
 	
	
	
	//Verbo HTTP  Status Code Retornado   Significado de Mercado 
	//GET           200  OK             Busca realizada com sucesso. 
	//POST          201 CREATED         Novo produto/item cadastrado com sucesso.
	//PUT           200   OK            Atualização (como dar baixa no estoque) realizada.
	//DELETE        204   NO CONTENT    Deletado com sucesso (não há corpo a ser retornado).
	
}
