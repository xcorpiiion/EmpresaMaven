package br.com.contmatic.endereco;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class Pais {

    public static final Long CODIGO_BRASIL = 1058L;

    private Long codigo;

    public boolean isExterior() {
        return this.codigo != null && !this.codigo.equals(CODIGO_BRASIL);
    }

    public Pais() {
    }

    public static Long getCodigoBrasil() {
        return CODIGO_BRASIL;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Pais pais = (Pais) o;

        return new EqualsBuilder()
                .append(codigo, pais.codigo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(codigo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }
}
