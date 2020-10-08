package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.FieldValidation.isCpfValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isEmailValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustWord;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

import java.util.Date;

public class Cliente {

	private String nome;

	private String email;
	
	private String cpf;

	private Date dataNascimento;

	private Endereco endereco;

	public Cliente(String nome, String email, Date dataNascimento, Endereco endereco, String cpf) {
		this.setNome(nome);
		this.setEmail(email);
		this.setDataNascimento(dataNascimento);
		this.setEndereco(endereco);
		this.setCpf(cpf);
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
		Cliente other = (Cliente) obj;
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
		builder.append("Cliente [getEndereco()=");
		builder.append(this.getEndereco());
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
