package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.EMPRESA;
import static br.com.contmatic.constantes.Constante.campoInvalidoMensagemPadrao;
import static br.com.contmatic.constantes.Constante.campoVazioOrNullMensagemPadrao;
import static br.com.contmatic.constantes.Constante.dataInvalidaMensagem;
import static br.com.contmatic.constantes.Constante.isDataGreaterThanCurrent;
import static br.com.contmatic.constantes.Constante.stringJustContainsWordAndNumberMensagemPadrao;
import static br.com.contmatic.constantes.Constante.tamanhoCamposMensagemPadrao;
import static br.com.contmatic.empresa.utils.FieldValidation.isCnpjValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataGreaterThanCurrent;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataLessThan1998;
import static br.com.contmatic.empresa.utils.FieldValidation.isEmailValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isListEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisWordAndNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

import java.util.List;

import org.joda.time.DateTime;

public class Empresa {

	private String nome;

	private String email;

	private String cnpj;

	private Endereco endereco;

	private DateTime dataCriacao;

	private DateTime dataAlteracao;

	private List<Produto> produtos;

	private List<Funcionario> funcionarios;

	private List<Cliente> clientes;

	public Empresa(String nome, String email, List<Produto> produtos, String cnpj, Endereco endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.setProduto(produtos);
		this.dataCriacao = new DateTime();
		this.dataAlteracao = new DateTime();
		this.setCnpj(cnpj);
		this.setEndereco(endereco);
	}

	public Empresa(String nome, String email, String cnpj, Endereco endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.dataCriacao = new DateTime();
		this.dataAlteracao = new DateTime();
		this.setCnpj(cnpj);
		this.setEndereco(endereco);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		isNull(endereco, campoVazioOrNullMensagemPadrao("endereco", EMPRESA));
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		isStringEmpty(cnpj, campoVazioOrNullMensagemPadrao("cnpj", EMPRESA));
		isCnpjValido(cnpj, campoInvalidoMensagemPadrao("cnpj", EMPRESA));
		this.cnpj = cnpj;
	}

	public void setNome(String nome) {
		isStringEmpty(nome, campoVazioOrNullMensagemPadrao("nome", EMPRESA));
		minAndMaxValue(3, 70, nome, tamanhoCamposMensagemPadrao(3, 70, "nome", EMPRESA));
		isStringContaisWordAndNumber(nome, stringJustContainsWordAndNumberMensagemPadrao("nome", EMPRESA));
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		isStringEmpty(email, campoVazioOrNullMensagemPadrao("email", EMPRESA));
		isEmailValido(email, campoInvalidoMensagemPadrao("email", EMPRESA));
		this.email = email;
	}

	public void setProduto(List<Produto> produtos) {
		isListEmpty(produtos, campoVazioOrNullMensagemPadrao("produtos", EMPRESA));
		this.produtos = produtos;
	}

	public List<Produto> getProduto() {
		return produtos;
	}

	public void setFuncionario(List<Funcionario> funcionarios) {
		isListEmpty(funcionarios, campoVazioOrNullMensagemPadrao("funcionarios", EMPRESA));
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionario() {
		return funcionarios;
	}

	public void setCliente(List<Cliente> clientes) {
		isListEmpty(clientes, campoVazioOrNullMensagemPadrao("clientes", EMPRESA));
		this.clientes = clientes;
	}

	public List<Cliente> getCliente() {
		return clientes;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(DateTime dataCriacao) {
		isNull(dataCriacao, campoVazioOrNullMensagemPadrao("data de criação", EMPRESA));
		isDataGreaterThanCurrent(dataCriacao, isDataGreaterThanCurrent("data criação", EMPRESA));
		isDataLessThan1998(dataCriacao,
				dataInvalidaMensagem("A data de criação da empresa não pode ser menor do que 1998"));
		this.dataCriacao = dataCriacao;
	}

	public DateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(DateTime dataAlteracao) {
		isNull(dataAlteracao, campoVazioOrNullMensagemPadrao("data de alteração", EMPRESA));
		isDataGreaterThanCurrent(dataAlteracao, isDataGreaterThanCurrent("data criação", EMPRESA));
		isDataLessThan1998(dataAlteracao,
				dataInvalidaMensagem("A data de criação da empresa não pode ser menor do que 1998"));
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empresa [nome=").append(nome).append(", email=").append(email).append(", cnpj=").append(cnpj)
				.append(", endereco=").append(endereco).append(", dataCriacao=").append(dataCriacao)
				.append(", dataAlteracao=").append(dataAlteracao).append(", produtos=").append(produtos)
				.append(", funcionarios=").append(funcionarios).append(", clientes=").append(clientes).append("]");
		return builder.toString();
	}

}
