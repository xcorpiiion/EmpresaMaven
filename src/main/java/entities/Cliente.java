package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cliente extends Pessoa {
	private Double dinheiroCarteira;
	private List<Produtos> carrinhoProduto = new ArrayList<Produtos>();
	private List<Produtos> produtosComprados = new ArrayList<Produtos>();

	public Cliente(String nome, String email, Double dinheiro, SimpleDateFormat dataNascimento) {
		super(nome, email, dataNascimento);
		this.dinheiroCarteira = dinheiro;
	}

	public Double getDinheiroCarteira() {
		return dinheiroCarteira;
	}
	
	

	public List<Produtos> getCarrinhoProduto() {
		return carrinhoProduto;
	}

	public List<Produtos> getProdutosComprados() {
		return produtosComprados;
	}

	public void addItensCarrinho(Cliente cliente, Loja loja, Scanner scanner, String nomeProduto) throws Exception {
		if (loja.getProduto() == null) {
			throw new Exception("O produto esta null");
		}

		loja.mostrarProdutos();
		System.out.println("-------------------------------------------------------------------------------");

		// Verifica se existe o produto
		if (loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto))) {
			// Se passou no teste, pergunta a quantidade de itens que quer adicionar no
			// carrinho.
			System.out.print("Informe a quantidade de produtos que você deseja adicionar no carrinho: ");
			int qtdProdutoAddCarrinho = 0;
			qtdProdutoAddCarrinho = scanner.nextInt();
			// Add o item no carrinho
			for (int i = 0; i < qtdProdutoAddCarrinho; i++) {
				cliente.carrinhoProduto.addAll(loja.getProduto().stream()
						.filter(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)).collect(Collectors.toList()));
			}
		} else {
			throw new Exception("O produto não existe");
		}

		System.out.println("Produtos add no carrinho");
		System.out.println("-------------------------------------------------------------------------------");

	}

	public void addDinheiroCarteira(Double dinheiro) {
		this.dinheiroCarteira += dinheiro;
		System.out.println("Você adicionou " + dinheiro + " a sua carteira");
		System.out.println("O valor da sua carteira é R$: " + this.getDinheiroCarteira());
	}

	public void comprarProduto(Loja loja, Cliente cliente) throws Exception {
		if (this.carrinhoProduto == null) {
			throw new Exception("O produto esta null");
		}

		cliente.carrinhoProduto.forEach(System.out::println);
		System.out.println("----------------------------------------------------------");

		// interação com o usuario
		Scanner scanner = new Scanner(System.in);
		int auxScanner = 0;

		// Opção do cliente comprar todos os itens do carrinho
		try {
			while (auxScanner == 0) {
				System.out.println("Deseja comprar todos os itens ou prefere comprar um por ver?");
				System.out.print("1 - comprar todos\n2 - comprar um por vez: ");
				auxScanner = scanner.nextInt();
				if (auxScanner < 1 || auxScanner > 2) {
					System.out.println("Informe um valor valido");
					auxScanner = 0;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Você digitou algo invalido");
		}
		// 1 é para compra todos os itens 2 é para escolher os itens que quer comprar
		double totalPreco = 0.0;

		// aux do switch
		boolean comprou = false;
		while (comprou == false) {
			switch (auxScanner) {
			case 1:
				totalPreco = 0.0;
				for (Produtos prod : cliente.carrinhoProduto) {
					totalPreco += prod.getPreco();
				}
				// Pergunta se tem certeza se deseja comprar tudo
				try {
					while (auxScanner == 0) {
						System.out.println("Tem certeza que gostaria de comprar todos os itens do carrinho?");
						System.out.print("1 - sim\n2 - não");
						auxScanner = scanner.nextInt();
						if (auxScanner != 1 || auxScanner != 2) {
							System.out.println("Informe um valor valido");
							auxScanner = 0;
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Você digitou algo invalido");
				}
				if (auxScanner == 1) {
					if (cliente.dinheiroCarteira >= totalPreco) {

						for(Produtos prod : cliente.getCarrinhoProduto()) {
							cliente.produtosComprados.add(prod);
						}
						cliente.carrinhoProduto.clear();
						System.out.println("Itens comprado com sucesso");
						comprou = true;
					}
				}
				break;
			case 2:
				// Pergunta o nome do produto que eu quero compra
				String nome = "Tablet";
				int qtdItemCarrinho = 0;
				totalPreco = 0.0;
				if (this.carrinhoProduto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome))) {
					for (Produtos prod : this.carrinhoProduto) {
						if (prod.getNome().equalsIgnoreCase(nome)) {
							qtdItemCarrinho++;
							totalPreco += prod.getPreco();
							// add o produto na lista, caso eu não tenha dinheiro para comprar, ele remove
							// da lista
							this.produtosComprados.add(prod);
						}
					}
					// mostra na tela a quantidade de itens no carrinho e pergunta quantos dele eu
					// quero compra
					System.out.println("----------------------------------------------------");
					this.produtosComprados.forEach(System.out::println);
					System.out.println();
					// Pergunta se tem certeza se deseja comprar tudo
					try {
						System.out.println("Quantos itens você deseja compra?");
						// auxScanner agr vai armazenar a quantidade de itens que eu quero compra
						auxScanner = scanner.nextInt();
					} catch (NumberFormatException e) {
						System.out.println("Você digitou algo invalido");
					}
					int qtdCompra = auxScanner;
					if (qtdCompra <= qtdItemCarrinho) {
						// pergunta se tem certeza que deseja compra
						try {
							while (auxScanner == 0) {
								System.out.println("Tem certeza que gostaria de comprar todos os itens?");
								System.out.print("1 - sim\n2 - não");
								auxScanner = scanner.nextInt();
								if (auxScanner != 1 || auxScanner != 2) {
									System.out.println("Informe um valor valido");
									auxScanner = 0;
								}
							}
						} catch (NumberFormatException e) {
							System.out.println("Você digitou algo invalido");
						}
						if (this.dinheiroCarteira >= totalPreco) {
							this.dinheiroCarteira -= totalPreco;
							this.produtosComprados.addAll(
									loja.getProduto().stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome))
											.collect(Collectors.toList()));
							System.out.println("Produto comprado com sucesso.");
							comprou = true;
						} else {
							for (Produtos prod : this.carrinhoProduto) {
								if (prod.getNome().equalsIgnoreCase(nome)) {
									qtdItemCarrinho++;
									totalPreco += prod.getPreco();
									// remove da lista
									this.produtosComprados.remove(prod);
								}
							}
							throw new Exception("O dinheiro é insulficiente");
						}
					}
				} else {
					System.out.println("O item não existe no carrinho");
				}
				break;
			case 3:
				comprou = true;
			default:
				System.out.println("O número informado não é valido");
			}
		}

//		this.produtosComprados.forEach(System.out::println);
//		System.out.println("Produto comprado com sucesso.");
//		System.out.println("Dinheiro na carteira R$: " + this.dinheiroCarteira);

	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + " Email: " + this.getEmail() + ", Valor na carteira: "
				+ this.getDinheiroCarteira() + ", Produtos no carrinho: "
				+ this.carrinhoProduto.stream().map(p1 -> p1.getNome()).collect(Collectors.toList())
				+ ", Produtos comprados: "
				+ this.produtosComprados.stream().map(p1 -> p1.getNome()).collect(Collectors.toList());
	}
}
