package br.com.contmatic.endereco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.Valid;
import javax.validation.constraints.*;

import static br.com.contmatic.constantes.Constante.ILLEGAL_WORD;
import static br.com.contmatic.constantes.Mensagem.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@NoArgsConstructor
@Getter
@Setter
public class Endereco {

	@NotEmpty(message = RUA_ENDERECO_VAZIO)
	@NotBlank(message = RUA_ENDERECO_VAZIO)
	@Size(min = 5, max = 50, message = RUA_ENDERECO_TAMANHO)
	private String rua;

	@NotEmpty(message = BAIRRO_ENDERECO_VAZIO)
	@NotBlank(message = BAIRRO_ENDERECO_VAZIO)
	@Size(min = 3, max = 50, message = BAIRRO_ENDERECO_TAMANHO)
	@Pattern(regexp = ILLEGAL_WORD, message = BAIRRO_ENDERECO_CARACTERE_INVALIDO)
	private String bairro;

	@NotEmpty(message = CEP_ENDERECO_VAZIO)
	@NotBlank(message = CEP_ENDERECO_VAZIO)
	@Pattern(regexp = ILLEGAL_WORD, message = CEP_ENDERECO_CARACTERE_INVALIDO)
	private String cep;

	@Min(value = 1, message = NUMERO_ENDERECO_TAMANHO)
	private int numero;

	@NotNull
	@Valid
	private Municipio municipio;

	@NotNull
	@Valid
	private Pais pais;

	public Endereco(String rua, String bairro, String cep, int numero, Pais pais, Municipio municipio) {
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.numero = numero;
		this.municipio = municipio;
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cep).append(numero).toHashCode();
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
		return new EqualsBuilder().append(cep, other.cep).append(numero, other.numero).isEquals();
	}

	@Override
	public String toString() {
		return reflectionToString(this, JSON_STYLE);
	}

}
