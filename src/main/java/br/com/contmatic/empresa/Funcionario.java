package br.com.contmatic.empresa;

import java.util.Date;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.MotivoDemissao;
import br.com.contmatic.enums.TipoContrato;

public class Funcionario {
	
	private String nome;

	private String email;
	
	private Date dataNascimento;
	
	private Cargo cargo;

	private Double salario;

	private Endereco endereco;

	private MotivoDemissao motivoDemissao;

	private TipoContrato tipoContrato;

	public Funcionario(String nome, String email, Double salario, Cargo cargo, Date dataNascimento,
			TipoContrato tipoContrato, Endereco endereco) throws Exception {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cargo = cargo;
		this.salario = salario;
		this.endereco = endereco;
		this.tipoContrato = tipoContrato;
		nome_nao_deve_ser_null_ou_vazio(nome);
		email_nao_deve_ser_null_ou_vazio(email);
		nao_deve_aceitar_salario_negativo(salario);
		nao_deve_aceitar_salario_null(salario);
		nao_deve_aceitar_cargo_null(cargo);
		dataNascimento_nao_deve_ser_null(dataNascimento);
		nao_deve_aceitar_contrato_null(tipoContrato);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public MotivoDemissao getMotivoDemissao() {
		return motivoDemissao;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void listaTarefasFuncionario(Funcionario funcionario) {
		if (funcionario.getCargo() == Cargo.RH) {
			System.out.println("Informe o que você quer fazer");
			System.out.println("1 - contratar um novo funcionario");
			System.out.println("2 - demitir um funcionario");
			System.out.println("3 - ver dados da empresa");
			System.out.println("4 - ver os seus dados");
			System.out.print("Informe uma opção: ");
		} else {
			System.out.println("Informe o que você quer fazer");
			System.out.println("1 - alterar dados de um produto");
			System.out.println("2 - cadastrar um novo produto");
			System.out.println("3 - ver os seus dados");
			System.out.print("Informe uma opção: ");
		}
	}
	
	private boolean isRH(Funcionario funcionario) {
		return funcionario.getCargo() != Cargo.RH;
	}
	
	private boolean isRepositor(Funcionario funcionario) {
		return funcionario.getCargo() != Cargo.REPOSITOR;
		
	}
	
	private boolean funcionario_exite_na_lista(Empresa loja, String nome, String email) {
		return loja.getFuncionario().stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email));
	}
	
	private boolean produto_deve_existir_na_lista(Empresa loja, String nome, Double preco) throws Exception {
		return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) 
				&& prod.getPreco().equals(preco));
	}

	// Apenas o Rh consegue contratar
	public void contratarFuncionario(String nome, String email, Cargo cargo, Double salario,
			Date dataNascimento, Funcionario funcionario, Empresa loja, TipoContrato tipoContrato,
			Endereco endereco) throws Exception {

		if (isRH(funcionario)) {
			throw new Exception("Apenas o RH pode contratar funcionarios");
		}
		
		if (funcionario_exite_na_lista(loja, nome, email)) {
			throw new Exception("O funcionario já foi contratado");
		}
	
		loja.getFuncionario().add(new Funcionario(nome, email, salario, cargo, dataNascimento, tipoContrato, endereco));
	}
	
	public void demitirFuncionario(String nome, String email, MotivoDemissao motivoDemissao, Funcionario funcionario,
			Empresa loja) throws Exception {
		nao_deve_aceitar_funcionario_null(funcionario);
		if (isRH(funcionario)) {
			throw new Exception("Apenas o RH pode contratar funcionarios");
		}
		if (!funcionario_exite_na_lista(loja, nome, email)) {
			throw new Exception("Não existe o funcionario com os dados informados");
		}
		switch (motivoDemissao) {
		case Justa_Causa:
			loja.getFuncionario().removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		case Pediu_As_Contas:
			loja.getFuncionario().removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		}
	}

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto,
			String nomeNovoProduto, Empresa loja) throws Exception {
		nao_deve_aceitar_funcionario_null(funcionario);
		if (isRepositor(funcionario)) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 1) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setNome(nomeNovoProduto);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, Double preco,
			Empresa loja) throws Exception {
		nao_deve_aceitar_funcionario_null(funcionario);
		
		if (isRepositor(funcionario)) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 2) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equals(nomeProduto)) {
					prod.setPreco(preco);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, int estoque,
			Empresa loja) throws Exception {
		nao_deve_aceitar_funcionario_null(funcionario);
		if (isRepositor(funcionario)) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 3) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setEstoque(estoque);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, double preco,
			int estoque, String nomeNovoProduto, Empresa loja) throws Exception {
		nao_deve_aceitar_funcionario_null(funcionario);
		if (isRepositor(funcionario)) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 4) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setNome(nomeNovoProduto);
					prod.setPreco(preco);
					prod.setEstoque(estoque);
				}
			}
		}
	}

	public void cadastrarProduto(String nome, Double preco, int estoque, Funcionario funcionario, Empresa loja)
			throws Exception {
		nao_deve_aceitar_empresas_null(loja);

		if (isRepositor(funcionario)) {
			throw new Exception("Apenas repositores podem cadastra os produtos");
		}
		
		if (produto_deve_existir_na_lista(loja, nome, preco)) {
			loja.getProduto().stream()
					.filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco))
					.forEach(prod -> {
						try {
							prod.setEstoque(prod.getEstoque() + estoque);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
		} else {
			loja.getProduto().add(new Produtos(nome, preco, estoque));
		}
	}
	
	private void nome_nao_deve_ser_null_ou_vazio(String nome) throws Exception {
		if(nome == null || nome.isEmpty() || nome.trim().equals("")) {
			throw new Exception("O nome está null ou vazio");
		}
	}
	
	private void email_nao_deve_ser_null_ou_vazio(String email) throws Exception {
		if(email == null || email.isEmpty() || email.trim().equals("")) {
			throw new Exception("O email está null ou vazio");
		}
	}
	
	private void dataNascimento_nao_deve_ser_null(Date dataNascimento) throws Exception {
		if(dataNascimento == null) {
			throw new Exception("A data de nascimento está null");
		}
	}
	
	private void nao_deve_aceitar_cargo_null(Cargo cargo) throws Exception {
		if(cargo == null) {
			throw new NullPointerException("O cargo esta null");
		}
	}
	
	private void nao_deve_aceitar_salario_null(Double salario) throws Exception {
		if(salario == null) {
			throw new NullPointerException("O salario esta null");
		}
	}
	
	private void nao_deve_aceitar_salario_negativo(Double salario) throws Exception {
		if(salario < 0) {
			throw new Exception("O salario esta null");
		}
	}
	
	private void nao_deve_aceitar_contrato_null(TipoContrato tipoContrato) {
		if(tipoContrato == null) {
			throw new NullPointerException("O contrato esta null");
		}
	}
	
	private void nao_deve_aceitar_funcionario_null(Funcionario funcionario) {
		if(funcionario == null) {
			throw new NullPointerException("O funcionario esta null");
		}
	}
	
	private void nao_deve_aceitar_empresas_null(Empresa loja) {
		if(loja == null) {
			throw new NullPointerException("A empresa esta null");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String toString(Funcionario funcionario) {
		return "------Dados do funcionario------" + "\nNome: " + funcionario.getNome() + "\nEmail: "
				+ funcionario.getEmail() + "\nCargo: " + funcionario.getCargo() + "Salario: " + funcionario.getSalario()
				+ "\n------Endereço------" + "\nRua: " + funcionario.getEndereco().getRua() + "\nNúmero residência: "
				+ funcionario.getEndereco().getNumeroResidencia() + "\nBairro: " + funcionario.getEndereco().getBairro()
				+ "\nCep: " + funcionario.getEndereco().getCep() + "\nCidade: " + funcionario.getEndereco().getCidade()
				+ "\nEstado: " + funcionario.getEndereco().getEstado();
	}

}
