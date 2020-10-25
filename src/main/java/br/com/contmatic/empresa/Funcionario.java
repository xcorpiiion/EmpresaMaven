package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.FUNCIONARIO;
import static br.com.contmatic.constantes.Constante.campoInvalidoMensagemPadrao;
import static br.com.contmatic.constantes.Constante.campoVazioOrNullMensagemPadrao;
import static br.com.contmatic.constantes.Constante.dataInvalidaMensagem;
import static br.com.contmatic.constantes.Constante.isDataGreaterThanCurrent;
import static br.com.contmatic.constantes.Constante.stringJustContainsWordMensagemPadrao;
import static br.com.contmatic.constantes.Constante.tamanhoCamposMensagemPadrao;
import static br.com.contmatic.empresa.utils.FieldValidation.isCpfValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataGreaterThanCurrent;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataLessThan1920;
import static br.com.contmatic.empresa.utils.FieldValidation.isEmailValido;
import static br.com.contmatic.empresa.utils.FieldValidation.isGreaterThanEmpresaCreateDate;
import static br.com.contmatic.empresa.utils.FieldValidation.isLessThanCreateDate;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustWord;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import br.com.contmatic.enums.Cargo;

public class Funcionario {

	private String nome;

	private String email;

	private String cpf;

	private DateTime dataNascimento;

	private DateTime dataEntrada;

	private DateTime dataSaida;

	private Cargo cargo;

	private BigDecimal salario;

	private Endereco endereco;

	public Funcionario(String nome, String email, BigDecimal salario, Cargo cargo, DateTime dataNascimento,
			Endereco endereco, String cpf) {
		this.setNome(nome);
		this.setEmail(email);
		this.setDataNascimento(dataNascimento);
		this.setEndereco(endereco);
		this.setSalario(salario);
		this.setCargo(cargo);
		this.setCpf(cpf);
		this.dataEntrada = new DateTime();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		isNull(endereco, campoVazioOrNullMensagemPadrao("endereco", FUNCIONARIO));
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		isNull(cargo, campoVazioOrNullMensagemPadrao("cargo", FUNCIONARIO));
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		isStringEmpty(nome, campoVazioOrNullMensagemPadrao("nome", FUNCIONARIO));
		minAndMaxValue(3, 30, nome, tamanhoCamposMensagemPadrao(3, 30, "nome", FUNCIONARIO));
		isStringContaisJustWord(nome, stringJustContainsWordMensagemPadrao("Nome", FUNCIONARIO));
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		isStringEmpty(email, campoVazioOrNullMensagemPadrao("email", FUNCIONARIO));
		isEmailValido(email, campoInvalidoMensagemPadrao("email", FUNCIONARIO));
		this.email = email;
	}

	public DateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(DateTime dataNascimento) {
		isNull(dataNascimento, campoVazioOrNullMensagemPadrao("data de nascimento", FUNCIONARIO));
		isDataGreaterThanCurrent(dataNascimento, isDataGreaterThanCurrent("data de nascimento", FUNCIONARIO));
		isDataLessThan1920(dataNascimento,
				dataInvalidaMensagem("A data de cadastro do funcionario não pode ser menor do que 1920"));
		this.dataNascimento = dataNascimento;
	}

	public DateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(DateTime dataEntrada) {
		isNull(dataEntrada, campoVazioOrNullMensagemPadrao("data de entrada", FUNCIONARIO));
		isLessThanCreateDate(new DateTime(), dataEntrada, dataInvalidaMensagem(
				"A data de entrada do funcionario não pode ser menor do que a data de criação da empresa"));
		isDataGreaterThanCurrent(dataEntrada, isDataGreaterThanCurrent("data de entrada", FUNCIONARIO));
		this.dataEntrada = dataEntrada;
	}

	public DateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(DateTime dataSaida) {
		isNull(dataSaida, campoVazioOrNullMensagemPadrao("data de saida", FUNCIONARIO));
		isDataGreaterThanCurrent(dataSaida, isDataGreaterThanCurrent("data de saida", FUNCIONARIO));
		isGreaterThanEmpresaCreateDate(dataSaida, dataEntrada);
		this.dataSaida = dataSaida;
	}

	public void setSalario(BigDecimal salario) {
		isNull(salario, campoVazioOrNullMensagemPadrao("salario", FUNCIONARIO));
		minAndMaxValue(valueOf(500), valueOf(1000), salario,
				tamanhoCamposMensagemPadrao(500, 1000, "salario", FUNCIONARIO));
		this.salario = salario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		isStringEmpty(cpf, campoVazioOrNullMensagemPadrao("cpf", FUNCIONARIO));
		isCpfValido(cpf, campoInvalidoMensagemPadrao("cpf", FUNCIONARIO));
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
		builder.append("Funcionario [nome=").append(nome).append(", email=").append(email).append(", cpf=").append(cpf)
				.append(", dataNascimento=").append(dataNascimento).append(", dataEntrada=").append(dataEntrada)
				.append(", cargo=").append(cargo).append(", salario=").append(salario).append(", endereco=")
				.append(endereco).append("]");
		return builder.toString();
	}

}
