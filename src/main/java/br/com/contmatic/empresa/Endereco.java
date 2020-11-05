package br.com.contmatic.empresa;

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

	public Integer getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(Integer numeroResidencia) {
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
