package br.com.contmatic.empresa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.TipoTelefone;

public class Telefone {

    @NotBlank(message = Constante.O_TELEFONE_NAO_PODE_FICAR_VAZIO)
    @NotEmpty(message = Constante.O_TELEFONE_NAO_PODE_FICAR_VAZIO)
    @NotNull(message = Constante.O_TELEFONE_NAO_PODE_FICAR_VAZIO)
    @Pattern(regexp = Constante.PHONE_VALIDATION, message = Constante.O_TELEFONE_NAO_E_VALIDO)
    private String phone;

    private TipoTelefone tipoTelefone;

    public Telefone(String phone,TipoTelefone tipoTelefone) {
        this.phone = phone;
        this.tipoTelefone = tipoTelefone;
    }

    public Telefone() {

    }

    public String getPhones() {
        return phone;
    }

    public void setPhones(String phones) {
        
        this.phone = phones;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phone).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        return new EqualsBuilder().append(phone, other).isEquals();
    }

    @Override
    public String toString() {
        return "Telefone [telefone=" + phone + ", tipoTelefone=" + tipoTelefone + "]";
    }

}
