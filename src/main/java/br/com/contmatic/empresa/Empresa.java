package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

import static br.com.contmatic.constantes.Constante.ILLEGAL_NUMBER;
import static br.com.contmatic.constantes.Constante.VALIDATION_EMAIL;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataGreaterThanCurrent;
import static br.com.contmatic.empresa.utils.FieldValidation.isDataLessThan1998;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Empresa {

    @NotEmpty(message = NOME_EMPRESA_VAZIO)
    @NotBlank(message = NOME_EMPRESA_VAZIO)
    @Size(min = 3, max = 100, message = NOME_EMPRESA_TAMANHO)
    @Pattern(regexp = ILLEGAL_NUMBER, message = NOME_EMPRESA_CARACTERE_INVALIDO)
    private String nome;

    @NotEmpty(message = EMAIL_EMPRESA_VAZIO)
    @NotBlank(message = EMAIL_EMPRESA_VAZIO)
    @Size(min = 10, max = 100, message = EMAIL_EMPRESA_TAMANHO)
    @Pattern(regexp = VALIDATION_EMAIL, message = EMAIL_EMPRESA_CARACTERE_INVALIDO)
    private String email;

    @CNPJ(message = CNPJ_EMPRESA_INVALIDO)
    @NotNull
    private String cnpj;

    @NotEmpty
    @Valid
    private Set<Telefone> telefones;

    @NotNull
    private DateTime dataCriacao;

    @NotNull
    private DateTime dataAlteracao;

    @NotNull(message = ENDERECO_EMPRESA_VAZIO)
    private Set<Endereco> enderecos;

    @NotNull
    private List<Produto> produtos;

    @NotNull
    private List<Funcionario> funcionarios;

    @NotNull
    private List<Cliente> clientes;

    public void setDataCriacao(DateTime dataCriacao) {
        isDataGreaterThanCurrent(dataCriacao, "Data de criação da empresa não pode ser maior do que" +
                " hora atual");
        isDataLessThan1998(dataCriacao, "A data de criação da empresa não pode ser menor do que 1998");
        this.dataCriacao = dataCriacao;
    }

    public void setDataAlteracao(DateTime dataAlteracao) {
        isDataLessThan1998(dataAlteracao, "A data de alteração da empresa não pode ser menor do que 1998");
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresa other = (Empresa) obj;
        return new EqualsBuilder().append(cnpj, other.cnpj).isEquals();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}