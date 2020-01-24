package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Date;
import br.com.contmatic.constantes.ValidationNullOrEmpty;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;

public class Funcionario {

	private static final String APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO = "Apenas repositores podem alterar os dados do produto";

	private String nome;

	private String email;

	private Date dataNascimento;

	private Cargo cargo;

	private BigDecimal salario;

	private Endereco endereco;

	private TipoContrato tipoContrato;

	public Funcionario(String nome, String email, BigDecimal salario, Cargo cargo, Date dataNascimento,
			TipoContrato tipoContrato, Endereco endereco) {
		setNome(nome);
		setEmail(email);
		ValidationNullOrEmpty.dataNascimentoIsNull(dataNascimento);
		this.dataNascimento = dataNascimento;
		setCargo(cargo);
		salarioIsNull(salario);
		salarioIsNegative(salario);
		this.salario = salario;
		ValidationNullOrEmpty.enderecoIsNull(endereco);
		setEndereco(endereco);
		contratoIsNull(tipoContrato);
		this.tipoContrato = tipoContrato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		ValidationNullOrEmpty.enderecoIsNull(endereco);
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		cargoIsNull(cargo);
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		ValidationNullOrEmpty.nomeIsNull(nome);
		ValidationNullOrEmpty.nomeIsEmpty(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		ValidationNullOrEmpty.emailIsNull(email);
		ValidationNullOrEmpty.emailIsEmpty(email);
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	private boolean isRH(Funcionario funcionario) {
		return funcionario.getCargo() != Cargo.RH;
	}

	private boolean isRepositor(Funcionario funcionario) {
		return funcionario.getCargo() != Cargo.REPOSITOR;

	}

	private boolean funcinarioIsCadastrado(Empresa loja, String nome, String email) {
		return loja.getFuncionario().stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email));
	}

	private boolean hasProdutoInList(Empresa loja, String nome, BigDecimal preco) {
		return loja.getProduto().stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco));
	}

	public void contratarFuncionario(String nome, String email, Cargo cargo, BigDecimal salario, Date dataNascimento,
			Funcionario funcionario, Empresa loja, TipoContrato tipoContrato, Endereco endereco) {

		if (isRH(funcionario)) {
			throw new IllegalArgumentException("Apenas o RH pode contratar funcionarios");
		}

		if (funcinarioIsCadastrado(loja, nome, email)) {
			throw new IllegalArgumentException("O funcionario já foi contratado");
		}

		loja.getFuncionario().add(new Funcionario(nome, email, salario, cargo, dataNascimento, tipoContrato, endereco));
	}

	public void demitirFuncionario(String nome, String email, Funcionario funcionario,
			Empresa loja) {
		funcionarioIsNull(funcionario);
		if (isRH(funcionario)) {
			throw new IllegalArgumentException("Apenas o RH pode contratar funcionarios");
		}
		if (!funcinarioIsCadastrado(loja, nome, email)) {
			throw new IllegalArgumentException("Não existe o funcionario com os dados informados");
		}
		loja.getFuncionario().removeIf(
				(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
	}

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto,
			String nomeNovoProduto, Empresa loja) {
		funcionarioIsNull(funcionario);
		if (isRepositor(funcionario)) {
			throw new IllegalArgumentException(APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
		}
		if (escolhaOpcoes == 1) {
			for (Produto prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setNome(nomeNovoProduto);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, BigDecimal preco,
			Empresa loja) {
		funcionarioIsNull(funcionario);

		if (isRepositor(funcionario)) {
			throw new IllegalArgumentException(APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
		}
		if (escolhaOpcoes == 2) {
			for (Produto prod : loja.getProduto()) {
				if (prod.getNome().equals(nomeProduto)) {
					prod.setPreco(preco);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, int estoque,
			Empresa loja) {
		funcionarioIsNull(funcionario);
		if (isRepositor(funcionario)) {
			throw new IllegalArgumentException(APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
		}
		if (escolhaOpcoes == 3) {
			for (Produto prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setEstoque(estoque);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, BigDecimal preco,
			int estoque, String nomeNovoProduto, Empresa loja) {
		funcionarioIsNull(funcionario);
		if (isRepositor(funcionario)) {
			throw new IllegalArgumentException(APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
		}
		if (escolhaOpcoes == 4) {
			for (Produto prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setNome(nomeNovoProduto);
					prod.setPreco(preco);
					prod.setEstoque(estoque);
				}
			}
		}
	}

	public void cadastrarProduto(String nome, BigDecimal preco, int estoque, Funcionario funcionario, Empresa loja) {
		funcionarioIsNull(funcionario);

		if (isRepositor(funcionario)) {
			throw new IllegalArgumentException("Apenas repositores podem cadastra os produtos");
		}

		if (hasProdutoInList(loja, nome, preco)) {
			loja.getProduto().stream()
					.filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco))
					.forEach(prod -> prod.setEstoque(prod.getEstoque() + estoque));
		} else {
			loja.getProduto().add(new Produto(nome, preco, estoque));
		}
	}

	private void cargoIsNull(Cargo cargo) {
		if (cargo == null) {
			throw new NullPointerException("O cargo esta null");
		}
	}

	private void salarioIsNull(BigDecimal salario) {
		if (salario == null) {
			throw new NullPointerException("O salario esta null");
		}
	}

	private void salarioIsNegative(BigDecimal salario) {
		if (salario.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("O salario esta null");
		}
	}

	private void contratoIsNull(TipoContrato tipoContrato) {
		if (tipoContrato == null) {
			throw new NullPointerException("O contrato esta null");
		}
	}

	private void funcionarioIsNull(Funcionario funcionario) {
		if (funcionario == null) {
			throw new NullPointerException("O funcionario esta null");
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

	@Override
	public String toString() {
		return "------Dados do funcionario------" + "\nNome: " + getNome() + "\nEmail: " + getEmail()
				+ "\nData de nascimento: " + getDataNascimento() + "\nCargo: " + getCargo() + "\nTipo de contrato: "
				+ getTipoContrato() + "\nSalario: " + getSalario() + "\n------Endereço------" + "\nRua: "
				+ getEndereco().getRua() + "\nNúmero residência: " + getEndereco().getNumeroResidencia() + "\nBairro: "
				+ getEndereco().getBairro() + "\nCep: " + getEndereco().getCep() + "\nCidade: "
				+ getEndereco().getCidade() + "\nEstado: " + getEndereco().getEstado();
	}

}
