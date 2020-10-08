package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustWord;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisWordAndNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

public class Endereco {
	
	private String rua;

	private String bairro;

	private String cep;

	private String numeroResidencia;

	private String cidade;

	private String estado;

	public Endereco(String rua, String bairro, String cep, String numeroResidencia, String cidade, String estado) {
		setRua(rua);
		setBairro(bairro);
		setCep(cep);
		setNumeroResidencia(numeroResidencia);
		setCidade(cidade);
		setEstado(estado);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		isNull(rua);
		isStringEmpty(rua);
		minAndMaxValue(3, 50, rua);
		isStringContaisWordAndNumber(rua);
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		isNull(bairro);
		isStringEmpty(bairro);
		minAndMaxValue(2, 50, bairro);
		isStringContaisWordAndNumber(bairro);
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		isNull(cep);
		isStringEmpty(cep);
		isStringContaisJustNumber(cep);
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		isNull(cidade);
		isStringEmpty(cidade);
		minAndMaxValue(2, 50, cidade);
		isStringContaisJustWord(cidade);
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		isNull(estado);
		isStringEmpty(estado);
		minAndMaxValue(2, 50, estado);
		isStringContaisJustWord(estado);
		this.estado = estado;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		isNull(numeroResidencia);
		isStringEmpty(numeroResidencia);
		minAndMaxValue(1, 10000, numeroResidencia);
		isStringContaisJustNumber(numeroResidencia);
		this.numeroResidencia = numeroResidencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((numeroResidencia == null) ? 0 : numeroResidencia.hashCode());
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
		Endereco other = (Endereco) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (numeroResidencia == null) {
			if (other.numeroResidencia != null)
				return false;
		} else if (!numeroResidencia.equals(other.numeroResidencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [rua=" + getRua() + ", bairro=" + getBairro() + ", cep=" + getCep() + ", numeroResidencia="
				+ getNumeroResidencia() + ", cidade=" + getCidade() + ", estado=" + getEstado() + "]";
	}

}
