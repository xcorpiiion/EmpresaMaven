package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.EstadosBrasil;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The rua. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @Min(value = 5, message = Constante.PRECISA_SER_UM_VALOR_MAIOR)
    private String rua;

    /** The bairro. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @Min(value = 5, message = Constante.PRECISA_SER_UM_VALOR_MAIOR)
    private String bairro;

    /** The cep. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @Pattern(regexp = Constante.ILLEGAL_WORD, message = Constante.VALOR_NAO_E_VALIDO)
    private String cep;

    /** The numero residencia. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @Min(value = 1)
    private int numeroResidencia;

    /** The cidade. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @Pattern(regexp = Constante.ILLEGAL_NUMBER, message = Constante.VALOR_NAO_E_VALIDO)
    @Min(value = 5, message = Constante.PRECISA_SER_UM_VALOR_MAIOR)
    private String cidade;

    /** The estado. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
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
