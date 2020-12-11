package com.innovation.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.innovation.cursomc.domain.Categoria;
import com.innovation.cursomc.domain.Cidade;
import com.innovation.cursomc.domain.Cliente;
import com.innovation.cursomc.domain.Endereco;
import com.innovation.cursomc.domain.Estado;
import com.innovation.cursomc.domain.Produto;
import com.innovation.cursomc.domain.enums.TipoCliente;
import com.innovation.cursomc.repositories.CategoriaRepository;
import com.innovation.cursomc.repositories.CidadeRepository;
import com.innovation.cursomc.repositories.ClienteRepository;
import com.innovation.cursomc.repositories.EnderecoRepository;
import com.innovation.cursomc.repositories.EstadoRepository;
import com.innovation.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//CATEGORIAS
		Categoria cat1 = new Categoria(null,"Escritório");
		Categoria cat2 = new Categoria(null,"Informática");
		
		//PRODUTOS
		Produto p1 = new Produto(null,"Computador", 1000.00);
		Produto p2 = new Produto(null,"Impressora", 500.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		//ADD PRODUTO NA CATEGORIA
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//ADD CATEGORIA NO PRODUTO
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		//ESTADOS
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		//CIDADES
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		//ADD CIDADES AOS ESTADOS
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("12345678","23456789"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303","Jardim", "38220834",c1,cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800","Centro", "6464646",c2,cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
	}

}
