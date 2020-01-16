import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Cliente;
import entities.Funcionario;
import entities.Empresa;
import entities.Produtos;
import enums.Cargo;
import enums.MotivoDemissao;

public class MainClass {

	private static String nome = "", email = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int typeLogin = 0, isLogin = 0; // determina qual é o seu tipo de Login
		boolean isFinish = false;
		double dinheiro = 0.0;
		Cargo cargo = null;
		MotivoDemissao motivoDemissao = null;

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

		System.out.print("Informe o nome da empresa: ");
		String nomeEmpresa = scanner.next();
		System.out.print("Informe o email da empresa: ");
		String emailEmpresa = scanner.next();
		System.out.println("Informe o cnpj da empresa (precisa ter 14 números): ");
		String cnpj = scanner.next();
		System.out.print("Informe o nome da rua da empresa: ");
		String ruaEmpresa = scanner.next();
		System.out.print("Informe o da bairro da empresa: ");
		String bairroEmpresa = scanner.next();
		System.out.print("Informe o cep da empresa (precisa ter 8 números): ");
		String cepEmpresa = scanner.next();

		try {
			if (cnpj.length() == 14) {
				throw new Exception("O cnpj está errado");
			}
			if (cnpj.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
				throw new Exception("O cnpj está errado");
			} else if (cnpj.matches("^[0-9]*$")) {
				System.out.println("");
			} else {
				throw new Exception("O cnpj está errado");
			}
			
			if (cepEmpresa.length() == 8) {
				throw new Exception("O cnpj está errado");
			}
			if (cepEmpresa.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
				throw new Exception("O cnpj está errado");
			} else if (cepEmpresa.matches("^[0-9]*$")) {
				System.out.println("");
			} else {
				throw new Exception("O cnpj está errado");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Empresa criada com sucesso");

		// cria uma loja
		Empresa loja = new Empresa(nomeEmpresa, emailEmpresa, produtos, cnpj, ruaEmpresa, bairroEmpresa, cepEmpresa);
		try {
			loja.contratarFuncionario(funcionario.getNome(), funcionario.getEmail(), funcionario.getCargo(),
					funcionario.getSalario(), funcionario.getDataNascimento(), funcionario);
			loja.contratarFuncionario("Lucas", "lucas@gmail.com", Cargo.Repositor, funcionario.getSalario(),
					funcionario.getDataNascimento(), funcionario);
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
				// Verifica se o cliente ou funcionario quer continuar loggado
				if (isLogin != 1) {
					cadastro = verificarValidade("Você possui cadastro?", "1 - para sim / 2 - para não: ", scanner,
							cadastro);
				} else {
					cadastro = 1;
				}
				if (cadastro == 1) {
					if (fazerLogin(scanner, loja, typeLogin, isLogin)) {
						isLogin = 1;
						try {
							System.out.println(nome);
							cliente = loja.returnClienteQueFezLogin(nome, email);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
							System.out.println(cliente.toString(cliente));
							break;
						}

					} else {
						System.out.println("Você não possui cadastro!");
					}
				} else {
					cadastro = 0;
					cadastro = verificarValidade("Você deseja fazer cadastro? ", "1 - para sim / 2 - para não", scanner,
							cadastro);
					if (cadastro == 1) {
						try {
							System.out.print("Informe o seu nome: ");
							scanner.nextLine();
							nome = scanner.nextLine();
							System.out.print("Informe o seu email: ");
							email = scanner.next();
							System.out.println("Informe a sua data de nascimento (dd/MM/yyyy): ");
							dataNascimento.parse(scanner.next());
							System.out.println("Informe um valor que você quer colocar na sua carteira: ");
							dinheiro = scanner.nextDouble();
							loja.cadastrarCliente(nome, email, dinheiro, dataNascimento);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			case 2:
				cadastro = 0;
				// Verifica se o cliente ou funcionario quer continuar loggado
				if (isLogin != 1) {
					cadastro = verificarValidade("Você possui cadastro?", "1 - para sim / 2 - para não: ", scanner,
							cadastro);
				} else {
					cadastro = 1;
				}

				if (cadastro == 1) {
					if (fazerLogin(scanner, loja, typeLogin, isLogin)) {
						isLogin = 1;
						try {
							funcionario = loja.returnFuncionarioQueFezLogin(nome, email);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						int escolhaFuncionario = 0;

						while (escolhaFuncionario == 0) {
							try {
								funcionario.listaTarefasFuncionario(funcionario);
								escolhaFuncionario = scanner.nextInt();
								if (escolhaFuncionario < 1 || escolhaFuncionario > 4) {
									System.out.println("Informe um número valido");
									escolhaFuncionario = 0;
								}
							} catch (NumberFormatException e) {
								System.out.println("Você informou um digito invalido " + e.getMessage());
							}

							System.out.println(
									"-------------------------------------------------------------------------------");
						}

						System.out.println(
								"-------------------------------------------------------------------------------");
						cadastro = 0;
						if (funcionario.getCargo() == Cargo.RH) {
							switch (escolhaFuncionario) {
							case 1:
								try {
									System.out.println("Informe os dados do funcionario que você quer contratar: ");
									System.out.print("Informe o nome do funcionario: ");
									scanner.nextLine();
									nome = scanner.nextLine();
									System.out.print("Informe o email do funcionario: ");
									email = scanner.next();
									System.out.print("Informe a data de nascimento do funcionario (dd/MM/yyyy): ");
									dataNascimento.parse(scanner.next());
									System.out.println("Escolha um cargo para o funcionario");
									System.out.println("1 - " + Cargo.Repositor.toString());
									System.out.println("2 - " + Cargo.RH.toString());
									System.out.print("Esolha uma opção: ");
									int auxCargo = 0;
									auxCargo = scanner.nextInt();
									switch (auxCargo) {
									case 1:
										cargo = Cargo.Repositor;
										break;
									case 2:
										cargo = Cargo.RH;
										break;

									default:
										break;
									}
									System.out.print("Informe o salario do funcionario: ");
									dinheiro = scanner.nextDouble();

									loja.contratarFuncionario(nome, email, cargo, dinheiro, dataNascimento,
											funcionario);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								break;
							case 2:
								try {
									System.out.print("Informe os dados do funcionario que você quer demitir: ");
									System.out.print("Informe o nome do funcionario: ");
									scanner.nextLine();
									nome = scanner.nextLine();
									System.out.print("Informe o email do funcionario: ");
									email = scanner.next();
									System.out.println("Informe o motivo da demissão");
									System.out.println("1 - " + MotivoDemissao.Justa_Causa.toString());
									System.out.println("2 - " + MotivoDemissao.Pediu_As_Contas.toString());
									System.out.print("Informe o motivo: ");
									int auxCargo = 0;
									auxCargo = scanner.nextInt();
									switch (auxCargo) {
									case 1:
										motivoDemissao = MotivoDemissao.Justa_Causa;
										break;
									case 2:
										motivoDemissao = MotivoDemissao.Pediu_As_Contas;
										break;
									}
									loja.demitirFuncionario(nome, email, motivoDemissao, funcionario);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} else {

							switch (escolhaFuncionario) {
							case 1:
								try {
									System.out.println("Informe o que você quer alterar no produto");
									System.out.println("1 - nome");
									System.out.println("2 - preco");
									System.out.println("3 - estoque");
									System.out.println("4 - tudo");
									System.out.print("Escolha uma opção: ");
									int auxEscolhaOpcao = 0;
									auxEscolhaOpcao = scanner.nextInt();
									String nomeProduto = "";
									double preco = 0.0;
									int estoque = 0;
									switch (auxEscolhaOpcao) {
									case 1:
										loja.mostrarProdutos();
										System.out.print("Informe o nome do produto que deseja alterar o nome: ");
										scanner.nextLine();
										nomeProduto = scanner.nextLine();
										if (loja.verificarProdutoExiste(nomeProduto)) {
											System.out.println("Informe o novo nome do produto");
											scanner.nextLine();
											nomeProduto = scanner.nextLine();
											loja.alterarDadosProduto(funcionario, escolhaFuncionario, nomeProduto);

										}
										break;
									case 2:
										loja.mostrarProdutos();
										System.out.print("Informe o nome do produto que deseja alterar o nome: ");
										scanner.nextLine();
										nomeProduto = scanner.nextLine();
										if (loja.verificarProdutoExiste(nomeProduto)) {
											System.out.print("Informe o novo preço do produto: ");
											preco = scanner.nextDouble();
											loja.alterarDadosProduto(funcionario, escolhaFuncionario, nomeProduto,
													preco);

										}
										break;
									case 3:
										loja.mostrarProdutos();
										System.out.print("Informe o nome do produto que deseja alterar o nome: ");
										nomeProduto = "";
										scanner.nextLine();
										nomeProduto = scanner.nextLine();
										if (loja.verificarProdutoExiste(nomeProduto)) {
											System.out.print("Informe o novo estoque do produto: ");
											estoque = scanner.nextInt();
											loja.alterarDadosProduto(funcionario, escolhaFuncionario, nomeProduto,
													estoque);

										}
									case 4:
										loja.mostrarProdutos();
										System.out.print("Informe o nome do produto que deseja alterar o nome: ");
										nomeProduto = "";
										scanner.nextLine();
										nomeProduto = scanner.nextLine();
										if (loja.verificarProdutoExiste(nomeProduto)) {
											System.out.print("Informe o novo nome do produto: ");
											scanner.nextLine();
											nomeProduto = scanner.nextLine();
											System.out.print("Informe o novo preço do produto: ");
											preco = scanner.nextDouble();
											System.out.print("Informe o novo estoque do produto: ");
											estoque = scanner.nextInt();
											loja.alterarDadosProduto(funcionario, escolhaFuncionario, nomeProduto,
													preco, estoque);
										}

									default:
										break;
									}
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								break;
							case 2:
								try {
									System.out.print("Informe os dados do produto que você quer cadastrar: ");
									System.out.print("Informe o nome do produto: ");
									scanner.nextLine();
									String nomeProduto = "";
									nomeProduto = scanner.nextLine();
									System.out.print("Informe o preco do produto: ");
									double preco = 0.0;
									preco = scanner.nextDouble();
									System.out.print("Informe o estoque do produto: ");
									int estoqueProduto = 0;
									estoqueProduto = scanner.nextInt();
									System.out.println(funcionario.getCargo().toString());
									loja.cadastrarProduto(nomeProduto, preco, estoqueProduto, funcionario);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					} else {
						System.out.println("Você não possui cadastro!");
					}
				}

				break;
			}
			System.out.println("-------------------------------------------------------------------------------");
			if (isLogin == 1) {
				isLogin = 0;
				isLogin = verificarValidade("Deseja continuar loggado", "1 - para sim / 2 - para não: ", scanner,
						isLogin);
			}
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

	private static boolean fazerLogin(Scanner scanner, Empresa loja, int clienteOuFunc, int isLogin) {
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
