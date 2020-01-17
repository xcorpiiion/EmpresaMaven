package br.com.empresa;

import java.text.SimpleDateFormat;

public abstract class Pessoa {
	private String nome, email;
	private SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");

	public Pessoa(String nome, String email, SimpleDateFormat dataNascimento) throws Exception {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		validacaoDados(nome, email, dataNascimento);
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

	public SimpleDateFormat getDataNascimento() {
		return dataNascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Pessoa other = (Pessoa) obj;
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
	
	public void validacaoDados(String nome, String email, SimpleDateFormat dataNascimento) throws Exception {
		if(nome == null || nome.isEmpty()) {
			throw new Exception("O nome está null ou vazio");
		}
		
		if(email == null || email.isEmpty()) {
			throw new Exception("O email está null ou vazio");
		}
		
		if(dataNascimento == null) {
			throw new Exception("A data de nascimento está null");
		}
	}

}
