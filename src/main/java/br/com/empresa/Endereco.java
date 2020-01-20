package br.com.empresa;

public class Endereco {
	private String rua;
	private String bairro;
	private String cep;
	private String numeroResidencia;
	private String cidade;
	private String estado;
	
	public Endereco(String rua, String bairro, String cep, String numeroResidencia, String cidade, String estado) throws Exception {
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.numeroResidencia = numeroResidencia;
		this.cidade = cidade;
		this.estado = estado;
		validacaoEndereco(rua, bairro, cep, numeroResidencia, cidade, estado);
	}

	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
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
	
	public void validacaoEndereco(String rua, String bairro, String cep, 
			String numeroResidencia, String cidade, String estado) throws Exception {
		
		if(cidade == null || cidade.isEmpty()) {
			throw new Exception("A cidade esta null ou vazia");
		}
		
		if(estado == null || estado.isEmpty()) {
			throw new Exception("O estado esta null ou vazia");
		}
		
		if(rua == null || rua.isEmpty()) {
			throw new Exception("A rua esta null ou vazia");
		}
		
		if(cep == null || cep.isEmpty()) {
			throw new Exception("O cep esta null ou vazia");
		}
		
		if(cidade == null || cidade.isEmpty()) {
			throw new Exception("A cidade esta null ou vazia");
		}
		
		if(bairro == null || bairro.isEmpty()) {
			throw new Exception("O bairro esta null ou vazia");
		}
		
		if (cep.length() != 8) {
			throw new Exception("O cep está errado");
		}
		if (cep.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
			throw new Exception("O cnpj está errado");
		} else if (cep.matches("^[0-9]*$")) {
		} else {
			throw new Exception("O cep está errado");
		}
		
		if (cidade.matches("^[0-9]*$")) {
			throw new Exception("A cidade está errada");
		}
		
		if (estado.matches("^[0-9]*$")) {
			throw new Exception("o estado está errada");
		}
		
	}

	@Override
	public String toString() {
		return "Endereco [rua=" + rua + ", bairro=" + bairro + ", cep=" + cep + ", numeroResidencia=" + numeroResidencia
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
	
}
