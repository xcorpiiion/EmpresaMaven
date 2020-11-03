package br.com.contmatic.endereco;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.*;

import static br.com.contmatic.constantes.Constante.ILLEGAL_NUMBER;
import static br.com.contmatic.constantes.Constante.ILLEGAL_WORD;
import static br.com.contmatic.constantes.Mensagem.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The rua. */
    @NotNull(message = RUA_ENDERECO_VAZIO)
    @NotEmpty(message = RUA_ENDERECO_VAZIO)
    @NotBlank(message = RUA_ENDERECO_VAZIO)
    @Size(min = 5, max = 50, message = RUA_ENDERECO_TAMANHO)
    private String rua;

    /** The bairro. */
    @NotEmpty(message = BAIRRO_ENDERECO_VAZIO)
    @NotBlank(message = BAIRRO_ENDERECO_VAZIO)
    @NotNull(message = BAIRRO_ENDERECO_VAZIO)
    @Size(min = 3, max = 50, message = BAIRRO_ENDERECO_TAMANHO)
    @Pattern(regexp = ILLEGAL_WORD, message = BAIRRO_ENDERECO_CARACTERE_INVALIDO)
    private String bairro;

    /** The cep. */
    @NotEmpty(message = CEP_ENDERECO_VAZIO)
    @NotBlank(message = CEP_ENDERECO_VAZIO)
    @NotNull(message = CEP_ENDERECO_VAZIO)
    @Pattern(regexp = ILLEGAL_WORD, message = CEP_ENDERECO_CARACTERE_INVALIDO)
    private String cep;

    /** The numero residencia. */
    @Min(value = 1, message = NUMERO_RESIDENCIA_ENDERECO_TAMANHO)
    private int numeroResidencia;

    /** The cidade. */
    @NotNull(message = CIDADE_ENDERECO_VAZIO)
    @NotEmpty(message = CIDADE_ENDERECO_VAZIO)
    @NotBlank(message = CIDADE_ENDERECO_VAZIO)
    @Min(value = 5, message = CIDADE_ENDERECO_TAMANHO)
    @Pattern(regexp = ILLEGAL_NUMBER, message = CIDADE_ENDERECO_CARACTERE_INVALIDO)
    private String cidade;

    /** The estado. */
    @NotNull(message = ESTADO_ENDERECO_VAZIO)
    private EstadosBrasil estado;

    /**
     * Gets the rua.
     *
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * Sets the rua.
     *
     * @param rua the new rua
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the bairro.
     *
     * @param bairro the new bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets the cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the cep.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Gets the cidade.
     *
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets the cidade.
     *
     * @param cidade the new cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public EstadosBrasil getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(EstadosBrasil estado) {
        this.estado = estado;
    }

    /**
     * Gets the numero residencia.
     *
     * @return the numero residencia
     */
    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    /**
     * Sets the numero residencia.
     *
     * @param numeroResidencia the new numero residencia
     */
    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cep).append(numeroResidencia).toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return new EqualsBuilder().append(cep, other.cep).append(numeroResidencia, other.numeroResidencia).isEquals();
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}
