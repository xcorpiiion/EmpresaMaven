import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import br.com.empresa.Cliente;
import br.com.empresa.Empresa;
import br.com.empresa.Endereco;
import br.com.empresa.Funcionario;
import br.com.empresa.Produtos;
import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

public class MainClass {

	private static String nome = "";
	private static String email = "";
	private static String rua = "";
	private static String bairro = "";
	private static String cep = "";
	private static String numeroResidencia = "";
	private static String cidade = "";
	private static String estado = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int typeLogin = 0, isLogin = 0; // determina qual é o seu tipo de Login
		boolean isFinish = false;
		double dinheiro = 0.0;
		Cargo cargo = null;
		TipoContrato tipoContrato = null;
		MotivoDemissao motivoDemissao = null;

		SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");

		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);

		List<Produtos> produtos = new ArrayList<Produtos>();
		try {
			produtos.add(new Produtos("God of war", 150.00, 30));
			produtos.add(new Produtos("The last of us", 120.00, 10));
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		// cadastra um funcionario do RH para poder contratar novos funcionarios
		try {
			dataNascimento.parse("20/01/1997");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Funcionario funcionario = null;
		try {
			funcionario = new Funcionario("Jessie", "jessie@gmail.com", 2501.00, Cargo.RH, dataNascimento,
					TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Cria um cliente
		try {
			dataNascimento.parse("01/01/1930");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cliente cliente = null;
		try {
			cliente = new Cliente("Kratos", "kratos@gmail.com", 2000.00, dataNascimento,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		System.out.print("Informe o nome da empresa: ");
		String nomeEmpresa = scanner.nextLine();
		System.out.print("Informe o email da empresa: ");
		String emailEmpresa = scanner.nextLine();
		System.out.println("Informe o cnpj da empresa (precisa ter 14 números): ");
		String cnpj = scanner.nextLine();
		System.out.print("Informe o nome da rua da empresa: ");
		rua = scanner.nextLine();
		System.out.print("Informe o da bairro da empresa: ");
		bairro = scanner.nextLine();
		System.out.print("Informe o cep da empresa (precisa ter 8 números): ");
		cep = scanner.nextLine();
		System.out.print("Informe o nome da cidade da empresa: ");
		cidade = scanner.nextLine();
		System.out.print("Informe o nome do estado da empresa: ");
		estado = scanner.nextLine();

		System.out.println("Empresa criada com sucesso");

		// cria uma loja
		Empresa loja = null;

		try {
			loja = new Empresa(nomeEmpresa, emailEmpresa, produtos, cnpj, 
					new Endereco(rua, bairro, cep, numeroResidencia, cidade, estado));
			funcionario.contratarFuncionario(funcionario.getNome(), funcionario.getEmail(), funcionario.getCargo(),
					funcionario.getSalario(), funcionario.getDataNascimento(), funcionario, loja,
					funcionario.getTipoContrato(), funcionario.getEndereco());
			funcionario.contratarFuncionario("Lucas", "lucas@gmail.com", Cargo.Repositor, 2500.00,
					funcionario.getDataNascimento(), funcionario, loja, TipoContrato.PJ,
					new Endereco("Rua caminhão", "Santa Julia", "02100120", "45", "São paulo", "São Paulo"));
			cliente.cadastrarCliente(cliente.getNome(), cliente.getEmail(), cliente.getDinheiroCarteira(),
					cliente.getDataNascimento(), loja, cliente.getEndereco());
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
						typeLogin = 1;
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
							} catch (Exception e) {
								e.printStackTrace();
							}
							cadastro = 0;
							break;
						case 2:
							try {
								// Mostra os produtos da loja
								loja.mostrarProdutos();
								System.out.println(
										"-------------------------------------------------------------------------------");

								System.out.print("Informe o nome do produto que você deseja adicionar no carrinho: ");
								String nomeProduto = "";
								scanner.nextLine();
								nomeProduto = scanner.nextLine();
								cliente.addItensCarrinho(cliente, loja, scanner, nomeProduto);
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
							cadastro = 0;
							break;
						case 4:
							System.out.println(cliente.toString(cliente));
							cadastro = 0;
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
							cliente.cadastrarCliente(nome, email, dinheiro, dataNascimento, loja,
									cadastrarEndereco(rua, bairro, cep, numeroResidencia, cidade, estado, scanner));
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
				break;
			case 2:

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

					System.out
							.println("-------------------------------------------------------------------------------");
					cadastro = 0;
					if (funcionario.getCargo() == Cargo.RH) {
						switch (escolhaFuncionario) {
						case 1:
							try {
								System.out.println("Informe os dados do funcionario que você quer contratar: ");
								System.out.print("Informe o nome do funcionario: ");
								String nomeNovoFuncionario = "";
								scanner.nextLine();
								nomeNovoFuncionario = scanner.nextLine();
								System.out.print("Informe o email do funcionario: ");
								String emailNovoFuncionario = "";
								emailNovoFuncionario = scanner.next();
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
								System.out.println("Escolha um tipo de contrato para o funcionario");
								System.out.println("1 - " + TipoContrato.CLT.toString());
								System.out.println("2 - " + TipoContrato.PJ.toString());
								System.out.print("Esolha uma opção: ");
								auxCargo = 0;
								auxCargo = scanner.nextInt();
								switch (auxCargo) {
								case 1:
									tipoContrato = TipoContrato.CLT;
									break;
								case 2:
									tipoContrato = TipoContrato.PJ;
									break;

								default:
									break;
								}

								funcionario.contratarFuncionario(nomeNovoFuncionario, emailNovoFuncionario, cargo,
										dinheiro, dataNascimento, funcionario, loja, tipoContrato,
										cadastrarEndereco(rua, bairro, cep, numeroResidencia, cidade, estado, scanner));
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
								String nomeFuncionarioDemitir = "";
								scanner.nextLine();
								nomeFuncionarioDemitir = scanner.nextLine();
								System.out.print("Informe o email do funcionario: ");
								String emailFuncionarioDemitir = "";
								emailFuncionarioDemitir = scanner.next();
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
								funcionario.demitirFuncionario(nomeFuncionarioDemitir, emailFuncionarioDemitir,
										motivoDemissao, funcionario, loja);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case 3:
							System.out.println(loja);
							break;
						case 4:
							System.out.println(funcionario.toString(funcionario));
							break;
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
								System.out.println(
										"-------------------------------------------------------------------------------");
								switch (auxEscolhaOpcao) {
								case 1:
									loja.mostrarProdutos();
									System.out.print("Informe o nome do produto que deseja alterar o nome: ");
									String nomeNovoProduto = "";
									scanner.nextLine();
									nomeProduto = scanner.nextLine();
									if (loja.verificarProdutoExiste(nomeProduto)) {
										System.out.print("Informe o novo nome do produto: ");
										nomeNovoProduto = scanner.nextLine();
										funcionario.alterarDadosProduto(funcionario, 1, nomeProduto, nomeNovoProduto,
												loja);

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
										funcionario.alterarDadosProduto(funcionario, 2, nomeProduto, preco, loja);

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
										funcionario.alterarDadosProduto(funcionario, 3, nomeProduto, estoque, loja);
									}
									break;
								case 4:
									loja.mostrarProdutos();
									System.out.print("Informe o nome do produto que deseja alterar o nome: ");
									nomeProduto = "";
									scanner.nextLine();
									nomeProduto = scanner.nextLine();
									if (loja.verificarProdutoExiste(nomeProduto)) {
										System.out.print("Informe o novo nome do produto: ");
										nomeNovoProduto = "";
										nomeNovoProduto = scanner.nextLine();
										System.out.print("Informe o novo preço do produto: ");
										preco = scanner.nextDouble();
										System.out.print("Informe o novo estoque do produto: ");
										estoque = scanner.nextInt();
										funcionario.alterarDadosProduto(funcionario, 4, nomeProduto, preco, estoque,
												nomeNovoProduto, loja);
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
								funcionario.cadastrarProduto(nomeProduto, preco, estoqueProduto, funcionario, loja);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case 3:
							System.out.println(funcionario.toString(funcionario));
							break;
						}
					}

				} else {
					System.out.println("Você não possui cadastro!");
				}

				break;
			
			default:
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

	private static Endereco cadastrarEndereco(String rua, String bairro, String cep, String numeroResidencia,
			String cidade, String estado, Scanner scanner) throws Exception {
		System.out.print("Informe a sua rua: ");
		scanner.nextLine();
		rua = scanner.nextLine();
		System.out.print("Informe o seu bairro: ");
		bairro = scanner.nextLine();
		System.out.print("Informe o seu cep: ");
		cep = scanner.nextLine();
		System.out.print("Informe o seu número de residência: ");
		numeroResidencia = scanner.nextLine();
		System.out.print("Informe a sua cidade: ");
		cidade = scanner.nextLine();
		System.out.print("Informe o seu estado: ");
		estado = scanner.nextLine();
		return new Endereco(rua, bairro, cep, numeroResidencia, cidade, estado);
	}
}
