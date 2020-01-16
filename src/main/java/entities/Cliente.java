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

	public void addItensCarrinho(Cliente cliente, Empresa loja, Scanner scanner, String nomeProduto) throws Exception {
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

	public void comprarProduto(Empresa loja, Cliente cliente) throws Exception {
		if (this.carrinhoProduto.isEmpty()) {
			throw new Exception("A lista esta vazia");
		}

		cliente.carrinhoProduto.forEach(System.out::println);
		System.out.println("----------------------------------------------------------");

		// interação com o usuario
		Scanner scanner = new Scanner(System.in);
		// essa variavel vai ajudar a escolher as opcoes
		int auxScanner = 0;

		// Opção do cliente comprar todos os itens do carrinho
		auxScanner = verificarValidade("Deseja comprar todos os produtos ou prefere comprar um por vez?",
				"1 - comprar todos / 2 - comprar um por vez: ", scanner, auxScanner);

		// 1 é para compra todos os itens 2 é para escolher os itens que quer comprar
		double totalPreco = 0.0;

		// essa variavel vai verificar se eu comprei, caso eu tenha comprado vai sair do
		// looping
		boolean comprou = false;
		while (!comprou) {
			switch (auxScanner) {
			case 1:
				totalPreco = 0.0;
				for (Produtos prod : cliente.carrinhoProduto) {
					totalPreco += prod.getPreco();
				}
				// retorna o valor da variavel para 0 para poder executar o metodo
				// verificarValidade
				auxScanner = 0;
				// Pergunta se tem certeza se deseja comprar tudo
				auxScanner = verificarValidade("Tem certeza que gostaria de comprar todos os itens do carrinho?",
						"1 - sim / 2 - não: ", scanner, auxScanner);

				if (auxScanner == 1) {
					if (cliente.dinheiroCarteira >= totalPreco) {

						for (Produtos prod : cliente.getCarrinhoProduto()) {
							cliente.produtosComprados.add(prod);
						}
						cliente.carrinhoProduto.clear();
						System.out.println("Produtos comprado com sucesso");
						comprou = true;
					}
				} else {
					// caso não tenha certeza, ele vai sair do looping e vai seguir o programa
					// normalmente
					comprou = true;
				}
				break;
			case 2:
				System.out.println("Produtos que estão no carrinho");
				cliente.getCarrinhoProduto().forEach(System.out::println);
				// Pergunta o nome do produto que eu quero compra
				System.out.println("Qual produto que está no carrinho você quer compra (informe o nome): ");
				scanner.nextLine();
				String nomeProduto = scanner.nextLine();

				// conta a quantidade de itens que tem no meu carrinho
				int qtdItemCarrinho = 0;
				// soma o preço dos produtos para poder dar o total na hora da compra
				totalPreco = 0.0;

				// Variavel que vai ser uma auxiliar para receber os produtos do carrinho e
				// depois
				// vai ajudar a remover os produtos do carrinho depois que a compra for
				// realizada
				List<Produtos> auxAddItemsCarrinho = new ArrayList<>();

				// verifico se o produto existe no carrinho
				if (cliente.carrinhoProduto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto))) {
					for (Produtos prod : cliente.carrinhoProduto) {
						if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
							qtdItemCarrinho++;
							totalPreco += prod.getPreco();
							// add o produto na lista
							auxAddItemsCarrinho.add(prod);
						}
					}
					// mostra na tela a quantidade de itens no carrinho e pergunta quantos dele eu
					// quero compra
					System.out.println("----------------------------------------------------");
					System.out.println("Produtos que você está querendo compra");
					auxAddItemsCarrinho.forEach(System.out::println);
					System.out.println("----------------------------------------------------");
					// Pergunta se tem certeza se deseja comprar tudo
					try {
						System.out.print("Quantos itens você deseja compra? (informe a quantidade): ");
						// auxScanner agr vai armazenar a quantidade de itens que eu quero compra
						int aux = 0;
						aux = scanner.nextInt();
						auxScanner = aux;
					} catch (NumberFormatException e) {
						System.out.println("Você digitou algo invalido");
					}

					int qtdCompra = auxScanner;
					if (qtdCompra <= qtdItemCarrinho) {
//						// pergunta se tem certeza que deseja compra
						auxScanner = 0;
						auxScanner = verificarValidade(
								"Tem certeza que gostaria de comprar todos os itens do carrinho?",
								"1 - sim / 2 - qualquer outro número para não: ", scanner, auxScanner);
						if (auxScanner != 1) {
							comprou = true;
							break;
						}

						if (this.dinheiroCarteira >= totalPreco) {

							this.dinheiroCarteira -= totalPreco;
							for (Produtos prod : auxAddItemsCarrinho) {
								if (qtdCompra > 0) {
									cliente.produtosComprados.add(prod);
									cliente.getCarrinhoProduto().remove(prod);

								}
								qtdCompra--;
							}

							System.out.println("Produtos comprado com sucesso.");
							comprou = true;
							break;
						} else {
							throw new Exception("O dinheiro é insulficiente");
						}
					} else {
						System.out.println("A quantidade de item que você quer comprar é maior do que a quantidade"
								+ " que está no carrinho");
						comprou = true;
						break;
					}

				} else {
					System.out.println("O item não existe no carrinho");
					break;
				}
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

	private int verificarValidade(String msg, String msg2, Scanner scanner, int numeroVerificador) {
		while (numeroVerificador == 0) {
			try {
				System.out.println(msg);
				System.out.print(msg2);
				numeroVerificador = scanner.nextInt();
				if (numeroVerificador < 1 || numeroVerificador > 2) {
					System.out.println("Informe um número valido");
					numeroVerificador = 0;
				}
			} catch (NumberFormatException e) {
				System.out.println("Você informou um digito invalido " + e.getMessage());
			}
		}
		System.out.println("-------------------------------------------------------------------------------");
		return numeroVerificador;
	}

	public String toString(Cliente cliente) {
		return "Nome: " + cliente.getNome() + " Email: " + cliente.getEmail() + ", Valor na carteira: "
				+ cliente.getDinheiroCarteira() + ", Produtos no carrinho: "
				+ cliente.carrinhoProduto.stream().map(p1 -> p1.getNome()).collect(Collectors.toList())
				+ ", Produtos comprados: "
				+ cliente.produtosComprados.stream().map(p1 -> p1.getNome()).collect(Collectors.toList());
	}
}
