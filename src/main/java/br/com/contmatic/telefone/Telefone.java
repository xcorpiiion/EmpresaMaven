package br.com.contmatic.telefone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.contmatic.constantes.Constante.PHONE_VALIDATION;
import static br.com.contmatic.constantes.Mensagem.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telefone {

    @NotBlank(message = TELEFONE_VAZIO)
    @NotEmpty(message = TELEFONE_VAZIO)
    @Pattern(regexp = PHONE_VALIDATION, message = TELEFONE_CARACTERE_INVALIDO)
    private String phone;

    @NotNull(message = TELEFONE_TIPO_TELEFONE_VAZIO)
    private TipoTelefone tipoTelefone;

    @NotNull(message = TELEFONE_DDD_VAZIO)
    private DddBrasil dddTelefone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public DddBrasil getDddTelefone() {
        return dddTelefone;
    }

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
