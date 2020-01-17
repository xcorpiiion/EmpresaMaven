package entities;

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
		validacaoEndereco(cep);
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

	public void validacaoEndereco(String cep) throws Exception {
		if (cep.length() != 8) {
			throw new Exception("O cep está errado");
		}
		if (cep.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
			throw new Exception("O cnpj está errado");
		} else if (cep.matches("^[0-9]*$")) {
		} else {
			throw new Exception("O cep está errado");
		}
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
	
}
