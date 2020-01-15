import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Cliente;
import entities.Funcionario;
import entities.Loja;
import entities.Produtos;
import enums.Cargo;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nome = "", email = "";
		int typeLogin = 0, isLogin = 0; // determina qual é o seu tipo de Login
		boolean isFinish = false;
		SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");

		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);

		List<Produtos> produtos = new ArrayList<Produtos>();
		produtos.add(new Produtos("God of war", 150.00, 30));
		produtos.add(new Produtos("The last of us", 120.00, 10));

		// cadastra um funcionario do RH para poder contratar novos funcionarios
		try {
			dataNascimento.parse("20/01/1997");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Funcionario funcionario = new Funcionario("Jessie", "jessie@gmail.com", 2501.00, Cargo.RH, dataNascimento);

		// Cria um cliente
		try {
			dataNascimento.parse("01/01/1930");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cliente cliente = new Cliente("Kratos", "kratos@gmail.com", 2000.00, dataNascimento);

		// cria uma loja
		Loja loja = new Loja("Kratos games", "kratos.games@gmail.com", produtos);
		try {
			loja.contratarFuncionario(funcionario.getNome(), funcionario.getEmail(), funcionario.getCargo(),
					funcionario.getSalario(), funcionario.getDataNascimento(), funcionario);
			loja.cadastrarCliente(cliente.getNome(), cliente.getEmail(), cliente.getDinheiroCarteira(),
					cliente.getDataNascimento());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Bem vindo a loja " + loja.getNome());
		System.out.println("-------------------------------------------------------------------------------");
		// faz o looping da loja
		while (!isFinish) {
			if (isLogin == 1) {
				isLogin = verificarValidade("Deseja continuar loggado", "1 - para sim / qualquer número para não: ",
						scanner, isLogin);
			}
			if (isLogin != 1) {
				typeLogin = 0;
				typeLogin = verificarValidade("Você é funcionario ou cliente?",
						"1 - para cliente / 2 - para funcionario: ", scanner, typeLogin);
			}
			// Verifica o número digitado
			switch (typeLogin) {
			case 1:
				int cadastro = 0;
				if (isLogin != 1) {
					cadastro = verificarValidade("Você possui cadastro?", "1 - para sim / 2 - para não: ", scanner,
							cadastro);
				} else {
					cadastro = 1;
				}
				if (cadastro == 1) {
					if (fazerLogin(nome, email, scanner, loja, typeLogin, isLogin)) {
						isLogin = 1;
						loja.mostrarProdutos();
						System.out.println(
								"-------------------------------------------------------------------------------");
						cadastro = 0;
						switch (opcoesCliente(scanner, cadastro)) {
						case 1:
							System.out.print("Informe o valor que deseja colocar na carteira: ");
							try {
								cliente.addDinheiroCarteira(scanner.nextDouble());
							} catch (NumberFormatException e) {
								System.out.println("Informe um valor valido: " + e.getMessage());
							}
							break;
						case 2:
							try {
								// Mostra os produtos da loja
								loja.mostrarProdutos();
								System.out.println(
										"-------------------------------------------------------------------------------");

								System.out.print("Informe o nome do produto que você deseja adicionar no carrinho: ");
								scanner.nextLine();
								nome = scanner.nextLine();
								cliente.addItensCarrinho(cliente, loja, scanner, nome);
							} catch (Exception e) {
								e.printStackTrace();
							}
							cadastro = 0;
							break;
						case 3:
							try {
								cliente.comprarProduto(loja, cliente);
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						case 4:
							System.out.println(cliente);
							break;
						}
						
					} else {
						System.out.println("Você não possui cadastro!");
					}
				}
			}
			System.out.println("-------------------------------------------------------------------------------");
			isLogin = 0;
			isLogin = verificarValidade("Deseja continuar loggado", "1 - para sim / 2 - para não: ", scanner, isLogin);
		}
		scanner.close();
	}

	// Vai verificar se o numero que eu digitei é valido e vai alterar as mensagens
	// do print
	private static int verificarValidade(String msg, String msg2, Scanner scanner, int numeroVerificador) {
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

	private static boolean fazerLogin(String nome, String email, Scanner scanner, Loja loja, int clienteOuFunc,
			int isLogin) {
		if (isLogin == 1) {
			return true;
		}
		System.out.println("Informe os seus dados para fazer o Login");
		int tentarLoginNovamente = 0;
		while (tentarLoginNovamente == 0) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.print("Informe o seu nome: ");
			nome = scanner.next();
			System.out.print("Informe o seu email: ");
			email = scanner.next();
			if (loja.verificarLogin(nome, email, clienteOuFunc)) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Login feito com sucesso!");

				System.out.println("-------------------------------------------------------------------------------");
				return true;
			} else {
				try {
					System.out
							.println("-------------------------------------------------------------------------------");
					System.out.println("Você não possui cadastro ou você errou algum dado!");
					System.out.println("Gostaria de tentar novamente? ");
					System.out.print("1 - para sim / qualquer número para não: ");
					tentarLoginNovamente = scanner.nextInt();
					System.out
							.println("-------------------------------------------------------------------------------");
					if (tentarLoginNovamente == 1) {
						System.out.println("Informe os seus dados novamente");
						tentarLoginNovamente = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("Você informou um digito invalido " + e.getMessage());
				}
			}
		}
		return false;
	}

	private static int opcoesCliente(Scanner scanner, int numeroVerificador) {
		while (numeroVerificador == 0) {
			try {
				System.out.println("Quais das opções a baixo você gostaria de fazer?");
				System.out.println("1 - colocar dinheiro na sua carteira");
				System.out.println("2 - adicionar produtos no seu carrinho");
				System.out.println("3 - compra produtos que estão no carrinho");
				System.out.println("4 - ver os seus dados");
				System.out.print("Informe a opção desejada: ");
				numeroVerificador = scanner.nextInt();
				if (numeroVerificador < 1 || numeroVerificador > 4) {
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
}
