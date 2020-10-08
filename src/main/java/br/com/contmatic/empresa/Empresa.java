package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.FieldValidation.isCnpjValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isEmailValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisWordAndNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

import java.util.Date;
import java.util.List;

public class Empresa {

	private String nome;

	private String email;

	private String cnpj;

	private Endereco endereco;
	
	private Date dataCriacao;
	
	private Date dataAlteracao;

	private List<Produto> produtos;

	private List<Funcionario> funcionarios;

	private List<Cliente> clientes;

	public Empresa(String nome, String email, Date dataCriacao, List<Produto> produtos, String cnpj, Endereco endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.setProduto(produtos);
		this.setCnpj(cnpj);
		this.setEndereco(endereco);
		this.setDataCriacao(dataCriacao);
	}
	
	public Empresa(String nome, String email, Date dataCriacao, String cnpj, Endereco endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.setCnpj(cnpj);
		this.setEndereco(endereco);
		this.setDataCriacao(dataCriacao);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		isNull(endereco);
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		isNull(cnpj);
		isStringEmpty(cnpj);
		isCnpjValido(cnpj);
		this.cnpj = cnpj;
	}

	public void setNome(String nome) {
		isNull(nome);
		isStringEmpty(nome);
		minAndMaxValue(3, 70, nome);
		isStringContaisWordAndNumber(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		isNull(email);
		isStringEmpty(email);
		isEmailValido(email);
		this.email = email;
	}

	public void setProduto(List<Produto> produtos) {
		isNull(produtos);
		this.produtos = produtos;
	}

	public List<Produto> getProduto() {
		return produtos;
	}

	public void setFuncionario(List<Funcionario> funcionarios) {
		isNull(funcionarios);
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionario() {
		return funcionarios;
	}

	public void setCliente(List<Cliente> clientes) {
		isNull(clientes);
		this.clientes = clientes;
	}

	public List<Cliente> getCliente() {
		return clientes;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		isNull(dataCriacao);
		this.dataCriacao = dataCriacao;
	}
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		isNull(dataAlteracao);
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
		builder.append("Empresa [getEndereco()=");
		builder.append(this.getEndereco());
		builder.append(", getNome()=");
		builder.append(this.getNome());
		builder.append(", getCnpj()=");
		builder.append(this.getCnpj());
		builder.append(", getEmail()=");
		builder.append(this.getEmail());
		builder.append(", getProduto()=");
		builder.append(this.getProduto());
		builder.append(", getFuncionario()=");
		builder.append(this.getFuncionario());
		builder.append(", getCliente()=");
		builder.append(this.getCliente());
		builder.append(", getDataCriacao()=");
		builder.append(this.getDataCriacao());
		builder.append(", getDataAlteracao()=");
		builder.append(this.getDataAlteracao());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}

	

}
