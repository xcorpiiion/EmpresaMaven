package br.com.contmatic.endereco;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.contmatic.utils.Constante.ILLEGAL_WORD;
import static br.com.contmatic.utils.Mensagem.DESCRICAO_MUNICIPIO_CARACTERE_INVALIDO;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class Municipio {

    @Min(1)
    @NotNull
    private Integer codigoMunicipal;

    @Min(1)
    @NotNull
    private Integer codigoFederal;

    @Min(1)
    @NotNull
    private Integer codigoIbge;

    @Pattern(regexp = ILLEGAL_WORD, message = DESCRICAO_MUNICIPIO_CARACTERE_INVALIDO)
    @Min(5)
    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private EnumUF uf;

    public Municipio() {
    }

    public Municipio(Integer codigoMunicipal, Integer codigoFederal, Integer codigoIbge, String descricao, EnumUF uf) {
        this.codigoMunicipal = codigoMunicipal;
        this.codigoFederal = codigoFederal;
        this.codigoIbge = codigoIbge;
        this.descricao = descricao;
        this.uf = uf;
    }

    public Integer getCodigoMunicipal() {
        return codigoMunicipal;
    }

    public void setCodigoMunicipal(Integer codigoMunicipal) {
        this.codigoMunicipal = codigoMunicipal;
    }

    public Integer getCodigoFederal() {
        return codigoFederal;
    }

    public void setCodigoFederal(Integer codigoFederal) {
        this.codigoFederal = codigoFederal;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EnumUF getUf() {
        return uf;
    }

    public void setUf(EnumUF uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Municipio municipio = (Municipio) o;

        return new EqualsBuilder()
                .append(codigoMunicipal, municipio.codigoMunicipal)
                .append(codigoFederal, municipio.codigoFederal)
                .append(codigoIbge, municipio.codigoIbge)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(codigoMunicipal)
                .append(codigoFederal)
                .append(codigoIbge)
                .toHashCode();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }
}
