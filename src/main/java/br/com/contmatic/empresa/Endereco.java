package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.ENDERECO;
import static br.com.contmatic.constantes.Constante.campoVazioOrNullMensagemPadrao;
import static br.com.contmatic.constantes.Constante.stringJustContainsWordAndNumberMensagemPadrao;
import static br.com.contmatic.constantes.Constante.tamanhoCamposMensagemPadrao;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisJustNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisWordAndNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

public class Endereco {

	private String rua;

	private String bairro;

	private String cep;

	private Integer numeroResidencia;

	private String cidade;

	private String estado;

	public Endereco(String rua, String bairro, String cep, Integer numeroResidencia, String cidade, String estado) {
		this.setRua(rua);
		this.setBairro(bairro);
		this.setCep(cep);
		this.setNumeroResidencia(numeroResidencia);
		this.setCidade(cidade);
		this.setEstado(estado);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		isStringEmpty(rua, campoVazioOrNullMensagemPadrao("rua", ENDERECO));
		minAndMaxValue(3, 50, rua, tamanhoCamposMensagemPadrao(3, 50, "rua", ENDERECO));
		isStringContaisWordAndNumber(rua, stringJustContainsWordAndNumberMensagemPadrao("rua", ENDERECO));
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		isStringEmpty(bairro, campoVazioOrNullMensagemPadrao("Bairro", ENDERECO));
		minAndMaxValue(2, 50, bairro, tamanhoCamposMensagemPadrao(2, 50, "bairro", ENDERECO));
		isStringContaisWordAndNumber(bairro, stringJustContainsWordAndNumberMensagemPadrao("bairro", ENDERECO));
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		isStringEmpty(cep, campoVazioOrNullMensagemPadrao("cep", ENDERECO));
		minAndMaxValue(8, 8, cep, tamanhoCamposMensagemPadrao(8, 8, "cep", ENDERECO));
		isStringContaisJustNumber(cep, stringJustContainsWordAndNumberMensagemPadrao("cep", ENDERECO));
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		isStringEmpty(cidade, campoVazioOrNullMensagemPadrao("cidade ", ENDERECO));
		minAndMaxValue(2, 50, cidade, tamanhoCamposMensagemPadrao(2, 50, "cidade", ENDERECO));
		isStringContaisWordAndNumber(cidade, stringJustContainsWordAndNumberMensagemPadrao("cidade", ENDERECO));
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		isStringEmpty(estado, campoVazioOrNullMensagemPadrao("Estado", ENDERECO));
		minAndMaxValue(2, 50, estado, tamanhoCamposMensagemPadrao(2, 50, "estado", ENDERECO));
		isStringContaisWordAndNumber(estado, stringJustContainsWordAndNumberMensagemPadrao("estado", ENDERECO));
		this.estado = estado;
	}

	public Integer getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(Integer numeroResidencia) {
		isNull(numeroResidencia, campoVazioOrNullMensagemPadrao("numero da residência", ENDERECO));
		minAndMaxValue(1, 10000, numeroResidencia,
				tamanhoCamposMensagemPadrao(1, 10000, "numero da residência", ENDERECO));
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
		StringBuilder builder = new StringBuilder();
		builder.append("Endereco [rua=").append(rua).append(", bairro=").append(bairro).append(", cep=").append(cep)
				.append(", numeroResidencia=").append(numeroResidencia).append(", cidade=").append(cidade)
				.append(", estado=").append(estado).append("]");
		return builder.toString();
	}

}
