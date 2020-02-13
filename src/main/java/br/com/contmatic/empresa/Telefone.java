package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.TipoTelefone;

/**
 * The Class Telefone.
 */
public class Telefone {

    /** The phone. */
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @Pattern(regexp = Constante.PHONE_VALIDATION, message = Constante.VALOR_NAO_E_VALIDO)
    private String phone;

    /** The tipo telefone. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private TipoTelefone tipoTelefone;

    /**
     * Gets the phones.
     *
     * @return the phones
     */
    public String getPhones() {
        return phone;
    }

    /**
     * Sets the phones.
     *
     * @param phones the new phones
     */
    public void setPhones(String phones) {

        this.phone = phones;
    }

    /**
     * Gets the tipo telefone.
     *
     * @return the tipo telefone
     */
    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    /**
     * Sets the tipo telefone.
     *
     * @param tipoTelefone the new tipo telefone
     */
    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phone).toHashCode();
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
        Telefone other = (Telefone) obj;
        return new EqualsBuilder().append(phone, other.phone).isEquals();
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
