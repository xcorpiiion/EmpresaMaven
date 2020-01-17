import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import entities.Cliente;
import entities.Endereco;
import entities.Produtos;

public class ClienteTest {
	private Double dinheiroCarteira;
	private List<Produtos> carrinhoProduto = new ArrayList<Produtos>();
	private List<Produtos> produtosComprados = new ArrayList<Produtos>();
	private List<Produtos> produto = new ArrayList<Produtos>();
	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");
	private List<Cliente> cliente = new ArrayList<Cliente>();

	@Before
	public void addDadosCliente() {
		try {
			nascimento.parse("19/10/1992");
			cliente.add(new Cliente("Matheus", "matheus@gmail.com", 2500.00, nascimento, 
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("20/11/1999");
			cliente.add(new Cliente("Vergil", "vergil@gmail.com", 1500.00, nascimento, 
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("9/1/1992");
			cliente.add(new Cliente("Dante", "dante@gmail.com", 900.00, nascimento, 
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("19/9/1996");
			cliente.add(new Cliente("Harry", "harry@gmail.com", 1300.00, nascimento, 
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
	}
	
	@Before
	public void addDadosProduto() {
		produto.add(new Produtos("Tablet", 2500.00, 50));
		produto.add(new Produtos("Computador", 3500.00, 70));
		produto.add(new Produtos("Smartphone", 2500.00, 150));
		produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
	}
	
	@Before
	public void addDadosCarrinho() {
		carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
		carrinhoProduto.add(new Produtos("Computador", 3500.00, 70));
		carrinhoProduto.add(new Produtos("Smartphone", 2500.00, 150));
		carrinhoProduto.add(new Produtos("Fone de Ouvido", 50.00, 200));
		carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
		carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
	}

	@Before
	public void addDinheiroCarteira() {
		this.dinheiroCarteira = 100000.00;
	}

	@Ignore
	public void alterarDados() {
		cliente.get(0).setNome("lucas");
		cliente.get(0).setEmail("lucas@mail.com");
		cliente.get(0).getEndereco().setRua("Rua dos alfeneiros");
		cliente.get(0).getEndereco().setNumeroResidencia("45-A");
		cliente.get(0).getEndereco().setBairro("Jardim formiga");
		cliente.get(0).getEndereco().setCep("12345678");
		cliente.get(0).getEndereco().setCidade("Salvador");
		cliente.get(0).getEndereco().setEstado("São paulo");
		
	}
	
	@Test()
	public void addCarrinho() {

		if(produto == null) {
			assertEquals("O produto está null", produto);
		}
		
		// Verifica se existe o produto
		String nome = "Tablet";
		int auxQtdCarrinho = 3;
		if (produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome))) {
			for (int i = 0; i < auxQtdCarrinho; i++) {
				carrinhoProduto.addAll(produto.stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome))
						.collect(Collectors.toList()));
			}
		} else {
			assertEquals("O produto não existe", nome);
		}
		
		
	}

	@Test()
	public void comprarProduto() {

		this.carrinhoProduto.forEach(System.out::println);
		System.out.println("----------------------------------------------------------");
		
		// Opção do cliente comprar todos os itens do carrinho
		// 1 é para compra todos os itens 2 é para escolher os itens que quer comprar
		int opcaoCompra = 2;
		double totalPreco = 0.0;
		switch(opcaoCompra) {
		case 1:
			totalPreco = 0.0;
			for(Produtos prod : this.carrinhoProduto) {
				totalPreco += prod.getPreco();
			}
			// Pergunta se tem certeza se deseja comprar tudo
			if(this.dinheiroCarteira >= totalPreco) {
				this.produtosComprados.addAll(this.produto);
				System.out.println("Itens comprado com sucesso");
			}
			break;
		case 2:
			// Pergunta o nome do produto que eu quero compra
			String nome = "Tablet";
			int qtdItemCarrinho = 0;
			totalPreco = 0.0;
			if(this.carrinhoProduto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome))) {
				for(Produtos prod : this.carrinhoProduto) {
					if(prod.getNome().equalsIgnoreCase(nome)) {
						qtdItemCarrinho++;
						totalPreco += prod.getPreco();
						this.produtosComprados.add(prod);
					}
				}
				// mostra na tela a quantidade de itens no carrinho e pergunta quantos dele eu quero compra
				int qtdCompra = 2;
				if(qtdCompra <= qtdItemCarrinho) {
					// pergunta se tem certeza que deseja compra
					if(this.dinheiroCarteira >= totalPreco) {
						this.dinheiroCarteira -= totalPreco;
						this.produtosComprados.addAll(this.produto.stream()
								.filter(prod -> prod.getNome().equalsIgnoreCase(nome))
								.collect(Collectors.toList()));
					} else {
						fail("O dinheiro é insulficiente");
					}
				}
			} else {
				assertTrue("O item não existe no carrinho", this.carrinhoProduto.stream()
						.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome)));
			}
			break;
		default:
			assertEquals("O número informado não é valido", opcaoCompra);
		}
		
		this.produtosComprados.forEach(System.out::println);
		System.out.println("Produto comprado com sucesso.");
		System.out.println("Dinheiro na carteira R$: " + this.dinheiroCarteira);
	}
	
	@Test(timeout = 100)
	public void cadastrarCliente() {
		if (this.cliente == null) {
			assertEquals("O cliente está nullo", this.cliente);
		}

		String nome = "lalau";
		String email = "lalau@gmail.com";

		// Verifica se o cliente já existe
		boolean wasCadastrado = this.cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email));
		assertTrue("O cliente já está cadastrado", !wasCadastrado);

		// Caso o tenha passado na verificação, o funcionario será contratado
		try {
			nascimento.parse("03/04/2000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("Você informou uma data invalida");
		}
		try {
			cliente.add(new Cliente(nome, email, 2500.00, nascimento, 
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("O endereço está incorreto");
		}
		System.out.println("O cliente foi cadastrado");
	}
	
}
