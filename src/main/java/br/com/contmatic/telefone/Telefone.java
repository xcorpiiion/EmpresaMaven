package br.com.contmatic.telefone;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import br.com.contmatic.constantes.Constante;
import br.com.contmatic.constantes.Mensagem;

/**
 * The Class Telefone.
 */
public final class Telefone {

    /** The phone. */
    @NotBlank(message = Mensagem.VALOR_ESTA_VAZIO)
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    @NotEmpty(message = Mensagem.VALOR_ESTA_VAZIO)
    @Pattern(regexp = Constante.PHONE_VALIDATION, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String phone;

    /** The tipo telefone. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    private TipoTelefone tipoTelefone;

    /** The ddd telefone. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    private DddBrasil dddTelefone;

    /**
     * Gets the phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone.
     *
     * @param phone the new phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * Gets the ddd telefone.
     *
     * @return the ddd telefone
     */
    public DddBrasil getDddTelefone() {
        return dddTelefone;
    }

    /**
     * Sets the ddd telefone.
     *
     * @param dddTelefone the new ddd telefone
     */
    public void setDddTelefone(DddBrasil dddTelefone) {
        this.dddTelefone = dddTelefone;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phone).append(dddTelefone).toHashCode();
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
        return new EqualsBuilder().append(phone, other.phone).append(dddTelefone, other.dddTelefone).isEquals();
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
