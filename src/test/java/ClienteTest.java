import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import entities.Produtos;

public class ClienteTest {
	private Double dinheiroCarteira;
	private List<Produtos> carrinhoProduto = new ArrayList<Produtos>();
	private List<Produtos> produtosComprados = new ArrayList<Produtos>();
	private List<Produtos> produto = new ArrayList<Produtos>();

	@Before
	public void addDadosProduto() {
		produto.add(new Produtos("Tablet", 2500.00, 50));
		produto.add(new Produtos("Computador", 3500.00, 70));
		produto.add(new Produtos("Smartphone", 2500.00, 150));
		produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
	}
	
	@Before
	public void addDadosCarrinho() {
		this.carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
		this.carrinhoProduto.add(new Produtos("Computador", 3500.00, 70));
		this.carrinhoProduto.add(new Produtos("Smartphone", 2500.00, 150));
		this.carrinhoProduto.add(new Produtos("Fone de Ouvido", 50.00, 200));
		this.carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
		this.carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
	}

	@Before
	public void addDinheiroCarteira() {
		this.dinheiroCarteira = 100000.00;
	}

	@Test
	public void addCarrinho() {
		if (this.produto == null) {
			fail("O produto esta null");
		}

		// Verifica se existe o produto
		String nome = "Tablet";
		int auxQtdCarrinho = 3;
		if (this.produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome))) {
			for (int i = 0; i < auxQtdCarrinho; i++) {
				this.carrinhoProduto.addAll(this.produto.stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome))
						.collect(Collectors.toList()));
			}
		} else {
			assertEquals("O produto não existe", nome);
		}
		
		System.out.println("Produto add no carrinho");
	}

	@Test
	public void comprarProduto() {
		if (this.carrinhoProduto == null) {
			fail("O produto esta null");
		}

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
}
