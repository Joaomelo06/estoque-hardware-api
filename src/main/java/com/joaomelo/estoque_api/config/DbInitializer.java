package com.joaomelo.estoque_api.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaomelo.estoque_api.model.Produto;
import com.joaomelo.estoque_api.repository.ProdutoRepository;

@Configuration
public class DbInitializer implements CommandLineRunner {
	
	private final ProdutoRepository produtoRepository;

    public DbInitializer(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
        @Override
        public void run(String... args) throws Exception {
            // Se o banco estiver vazio, insere produtos iniciais para teste
            if (produtoRepository.count() == 0) {
                Produto p1 = new Produto(null, "Placa de Vídeo RTX 4060", "Gigabyte", new BigDecimal("1899.90"), 10, null);
                Produto p2 = new Produto(null, "Processador Ryzen 7 5700X", "AMD", new BigDecimal("1200.00"), 5, null);
                Produto p3 = new Produto(null, "Memória RAM 16GB DDR4", "Kingston", new BigDecimal("280.00"), 20, null);

                produtoRepository.save(p1);
                produtoRepository.save(p2);
                produtoRepository.save(p3);

                System.out.println("==========================================");
                System.out.println(">>> BANCO H2 POPULADO COM PRODUTOS DE TESTE!");
                System.out.println("==========================================");
                
             // Verifique se não guardou p1 três vezes por engano:
                produtoRepository.save(p1);
                produtoRepository.save(p2); // Certifique-se de que aqui está p2
                produtoRepository.save(p3); // Certifique-se de que aqui está p3
            }
    }
}
