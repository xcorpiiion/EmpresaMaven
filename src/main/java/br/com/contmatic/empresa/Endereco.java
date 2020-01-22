package br.com.contmatic.empresa;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;
import br.com.contmatic.services.StringSizeException;

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
		ruaIsNull(rua);
		ruaIsEmpty(rua);
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		bairroIsNull(bairro);
		bairroIsEmpty(bairro);
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		cepIsNull(cep);
		cepIsEmpty(cep);
		cepContainsWord(cep);
		cepSizeValidation(cep);
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		cidadeIsNull(cidade);
		cidadeIsEmpty(cidade);
		cidadeContainsNumber(cidade);
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		estadoIsNull(estado);
		estadoIsEmpty(estado);
		estadoContainsNumber(estado);
		this.estado = estado;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		numeroResidenciaIsNull(numeroResidencia);
		numeroResidenciaIsEmpty(numeroResidencia);
		this.numeroResidencia = numeroResidencia;
	}

	private void numeroResidenciaIsNull(String numeroResidencia) {
		if (numeroResidencia == null) {
			throw new NullPointerException("O número esta null");
		}
	}
	
	private void numeroResidenciaIsEmpty(String numeroResidencia) {
		if (numeroResidencia.isEmpty() || numeroResidencia.trim().equals("")) {
			throw new EmptyStringException("O número esta vazio");
		}
	}
	
	private void cidadeIsNull(String cidade) {
		if (cidade == null) {
			throw new NullPointerException("A cidade esta null");
		}
	}
	
	private void cidadeIsEmpty(String cidade) {
		if (cidade.isEmpty() || cidade.trim().equals("")) {
			throw new EmptyStringException("A cidade esta vazia");
		}
	}
	
	private void cidadeContainsNumber(String cidade) {
		if (cidade.matches(Constante.illegalNumber)) {
			throw new StringFormatException("A cidade contém algum número");
		}
	}
	
	private void estadoIsNull(String estado) {
		if (estado == null) {
			throw new NullPointerException("O estado esta null");
		}
	}
	
	private void estadoIsEmpty(String estado) {
		if (estado.isEmpty() || estado.trim().equals("")) {
			throw new EmptyStringException("O estado esta vazio");
		}
	}
	
	private void estadoContainsNumber(String estado) {
		if (estado.matches(Constante.illegalNumber)) {
			throw new StringFormatException("O estado contém algum número");
		}
	}
	
	private void ruaIsNull(String rua) {
		if (rua == null) {
			throw new NullPointerException("A rua está null");
		}
	}
	
	private void ruaIsEmpty(String rua) {
		if (rua.isEmpty() || rua.trim().equals("")) {
			throw new EmptyStringException("A rua esta vazia");
		}
	}
	
	private void cepIsNull(String cep) {
		if (cep == null) {
			throw new NullPointerException("O cep está null");
		}
	}
	
	private void cepIsEmpty(String cep) {
		if (cep.isEmpty() || cep.trim().equals("")) {
			throw new EmptyStringException("O cep esta vazio");
		}
	}
	
	private void cepSizeValidation(String cep) {
		if (cep.length() != 8) {
			throw new StringSizeException("O tamanho do cep é incorreto");
		}
	}
	
	private void cepContainsWord(String cep) {
		if (cep.matches(Constante.illegalWord)) {
			throw new StringFormatException("O cep contém alguma letra");
		} else if (cep.matches(Constante.illegalNumber)) {
		} else {
			throw new StringFormatException("O cep contém alguma letra");
		}
	}
	
	private void bairroIsNull(String bairro) {
		if (bairro == null) {
			throw new NullPointerException("O bairro está null");
		}
	}
	
	private void bairroIsEmpty(String bairro) {
		if (bairro.isEmpty() || bairro.trim().equals("")) {
			throw new EmptyStringException("O bairro esta vazio");
		}
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
		return "Endereco [rua=" + rua + ", bairro=" + bairro + ", cep=" + cep + ", numeroResidencia=" + numeroResidencia
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}

}
