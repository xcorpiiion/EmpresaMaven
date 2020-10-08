package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.FieldValidation.isCpfValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isEmailValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustWord;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

import java.math.BigDecimal;
import java.util.Date;

import br.com.contmatic.enums.Cargo;

public class Funcionario {

	private String nome;

	private String email;
	
	private String cpf;

	private Date dataNascimento;

	private Cargo cargo;

	private BigDecimal salario;

	private Endereco endereco;

	public Funcionario(String nome, String email, BigDecimal salario, Cargo cargo, Date dataNascimento,
			Endereco endereco, String cpf) {
		this.setNome(nome);
		this.setEmail(email);
		this.setDataNascimento(dataNascimento);
		this.setEndereco(endereco);
		this.setSalario(salario);
		this.setCargo(cargo);
		this.setCpf(cpf);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		isNull(endereco);
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		isNull(cargo);
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		isNull(nome);
		isStringEmpty(nome);
		minAndMaxValue(3, 30, nome);
		isStringContaisJustWord(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		isNull(email);
		isEmailValido(email);
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		isNull(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	public void setSalario(BigDecimal salario) {
		isNull(salario);
		minAndMaxValue(BigDecimal.valueOf(500), BigDecimal.valueOf(1000), salario);
		this.salario = salario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		isNull(cpf);
		isCpfValido(cpf);
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Funcionario [getEndereco()=");
		builder.append(this.getEndereco());
		builder.append(", getCargo()=");
		builder.append(this.getCargo());
		builder.append(", getSalario()=");
		builder.append(this.getSalario());
		builder.append(", getNome()=");
		builder.append(this.getNome());
		builder.append(", getEmail()=");
		builder.append(this.getEmail());
		builder.append(", getDataNascimento()=");
		builder.append(this.getDataNascimento());
		builder.append(", getCpf()=");
		builder.append(this.getCpf());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}

	

}
