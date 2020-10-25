package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.CLIENTE;
import static br.com.contmatic.constantes.Constante.campoInvalidoMensagemPadrao;
import static br.com.contmatic.constantes.Constante.campoVazioOrNullMensagemPadrao;
import static br.com.contmatic.constantes.Constante.dataInvalidaMensagem;
import static br.com.contmatic.constantes.Constante.isDataGreaterThanCurrent;
import static br.com.contmatic.constantes.Constante.stringJustContainsWordMensagemPadrao;
import static br.com.contmatic.constantes.Constante.tamanhoCamposMensagemPadrao;
import static br.com.contmatic.empresa.utils.FieldValidation.isCpfValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataGreaterThanCurrent;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataLessThan1920;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataLessThan1998;
import static br.com.contmatic.empresa.utils.FieldValidation.isEmailValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustWord;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

import org.joda.time.DateTime;

public class Cliente {

	private String nome;

	private String email;

	private String cpf;

	private DateTime dataNascimento;

	private DateTime cadastro;

	private Endereco endereco;

	public Cliente(String nome, String email, DateTime dataNascimento, Endereco endereco, String cpf) {
		this.setNome(nome);
		this.setEmail(email);
		this.setDataNascimento(dataNascimento);
		this.setEndereco(endereco);
		this.cadastro = new DateTime();
		this.setCpf(cpf);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		isNull(endereco, campoVazioOrNullMensagemPadrao("endereco", CLIENTE));
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		isStringEmpty(nome, campoVazioOrNullMensagemPadrao("nome", CLIENTE));
		minAndMaxValue(3, 30, nome, tamanhoCamposMensagemPadrao(3, 30, "nome", CLIENTE));
		isStringContaisJustWord(nome, stringJustContainsWordMensagemPadrao("Nome", CLIENTE));
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		isStringEmpty(email, campoVazioOrNullMensagemPadrao("email", CLIENTE));
		isEmailValido(email, campoInvalidoMensagemPadrao("email", CLIENTE));
		this.email = email;
	}

	public DateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(DateTime dataNascimento) {
		isNull(dataNascimento, campoVazioOrNullMensagemPadrao("data de nascimento", CLIENTE));
		isDataGreaterThanCurrent(dataNascimento,
				isDataGreaterThanCurrent("data de nascimento", CLIENTE));
		isDataLessThan1920(dataNascimento,
				dataInvalidaMensagem("A data de cadastro do cliente não pode ser menor do que 1920"));
		this.dataNascimento = dataNascimento;
	}

	public DateTime getCadastro() {
		return cadastro;
	}

	public void setCadastro(DateTime cadastro) {
		isNull(cadastro, campoVazioOrNullMensagemPadrao("data de cadastro", "cliente"));
		isDataGreaterThanCurrent(cadastro,
				isDataGreaterThanCurrent("data de cadastro", CLIENTE));
		isDataLessThan1998(cadastro,
				dataInvalidaMensagem("A data de cadastro do cliente não pode ser menor do que 1998"));
		this.cadastro = cadastro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		isStringEmpty(cpf, campoVazioOrNullMensagemPadrao("cpf", CLIENTE));
		isCpfValido(cpf, campoInvalidoMensagemPadrao("cpf", CLIENTE));
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
		builder.append("Cliente [nome=").append(nome).append(", email=").append(email).append(", cpf=").append(cpf)
				.append(", dataNascimento=").append(dataNascimento).append(", cadastro=").append(cadastro)
				.append(", endereco=").append(endereco).append("]");
		return builder.toString();
	}

}
