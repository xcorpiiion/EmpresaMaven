package br.com.contmatic.telefone;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.contmatic.utils.Constante.PHONE_VALIDATION;
import static br.com.contmatic.utils.Mensagem.*;
import static br.com.contmatic.utils.Mensagem.TELEFONE_DDD_VAZIO;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class Telefone {

    @NotBlank(message = TELEFONE_VAZIO)
    @NotNull(message = TELEFONE_VAZIO)
    @NotEmpty(message = TELEFONE_VAZIO)
    @Pattern(regexp = PHONE_VALIDATION, message = TELEFONE_CARACTERE_INVALIDO)
    private String phone;

    /**
     * The tipo telefone.
     */
    @NotNull(message = TELEFONE_TIPO_TELEFONE_VAZIO)
    private TipoTelefone tipoTelefone;

    /**
     * The ddd telefone.
     */
    @NotNull(message = TELEFONE_DDD_VAZIO)
    private DddBrasil dddTelefone;

    public Telefone() {
    }

    public Telefone(String phone, TipoTelefone tipoTelefone, DddBrasil dddTelefone) {
        this.phone = phone;
        this.tipoTelefone = tipoTelefone;
        this.dddTelefone = dddTelefone;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Telefone telefone = (Telefone) o;

        return new EqualsBuilder()
                .append(phone, telefone.phone)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(phone)
                .toHashCode();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}
