package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;

public class Funcionario {

	private static final String APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO = "Apenas repositores podem alterar os dados do produto";

	@NotEmpty(message = "O nome não pode esta vazio")
	private String nome;

	@NotEmpty(message = "O email não pode esta vazio")
	private String email;

	@NotNull(message = "A data de nascimento não pode esta nullo")
	private Date dataNascimento;

	@NotNull(message = "O cargo não pode esta vazio")
	private Cargo cargo;

	@NotNull(message = "O salario não pode esta nullo")
	@Min(value = 1000, message = "O valor precisa ser um salario minimo")
	private BigDecimal salario;

	@NotNull(message = "O endereco não pode ser nullo")
	private Endereco endereco;

	@NotNull(message = "O tipo de contrato não pode ser nullo")
	private TipoContrato tipoContrato;

    public Funcionario(String nome, String email, BigDecimal salario, Cargo cargo, Date dataNascimento,
			TipoContrato tipoContrato, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.salario = salario;
        this.endereco = endereco;
        this.tipoContrato = tipoContrato;
	}
    
    public Funcionario() {
        
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
		cargoIsNull(cargo);
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
        this.salario = salario;
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

	private void funcionarioIsNull(Funcionario funcionario) {
		if (funcionario == null) {
			throw new NullPointerException("O funcionario esta null");
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(email).toHashCode();
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
		return new EqualsBuilder().append(email, other.email).append(nome, other.nome).isEquals();
	}

	@Override
	public String toString() {
		return new StringBuilder().append( "------Dados do funcionario------").append("\nNome: ").append(getNome())
		        .append("\nEmail: ").append(getEmail()).append("\nData de nascimento: ").append(getDataNascimento())
		        .append("\nCargo: ").append(getCargo()).append("\nTipo de contrato: ").append(getTipoContrato())
		        .append("\nSalario: ").append(getSalario()).append("\n------Endereço------").append("\nRua: ")
				.append(getEndereco().getRua()).append("\nNúmero residência: ").append(getEndereco().getNumeroResidencia())
				.append("\nBairro: ").append(getEndereco().getBairro()).append("\nCep: ").append(getEndereco().getCep())
				.append("\nCidade: ").append(getEndereco().getCidade()).append("\nEstado: ").append(getEndereco().getEstado())
				.toString();
	}

}
