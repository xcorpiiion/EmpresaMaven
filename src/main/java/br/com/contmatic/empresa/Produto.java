package br.com.contmatic.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static br.com.contmatic.constantes.Mensagem.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @NotBlank(message = NOME_PRODUTO_VAZIO)
    @NotEmpty(message = NOME_PRODUTO_VAZIO)
    @Length(min = 3, max = 50, message = NOME_PRODUTO_TAMANHO)
    private String nome;

    @NotNull(message = PRECO_PRODUTO_VAZIO)
    @Min(value = 1, message = PRECO_PRODUTO_TAMANHO)
    private BigDecimal preco;

    @Min(value = 1, message = ESTOQUE_PRODUTO_VAZIO)
    private Integer estoque;

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nome).append(preco).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        return new EqualsBuilder().append(nome, other.nome).append(preco, other.preco).isEquals();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }
}
