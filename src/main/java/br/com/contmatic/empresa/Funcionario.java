package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.enums.EnumCargo;
import br.com.contmatic.telefone.Telefone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

import static br.com.contmatic.constantes.Constante.ILLEGAL_WORD;
import static br.com.contmatic.constantes.Constante.VALIDATION_EMAIL;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.empresa.utils.FieldValidation.*;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@NoArgsConstructor
@Getter
@Setter
public class Funcionario {

    @NotBlank(message = NOME_FUNCIONARIO_VAZIO)
    @NotEmpty(message = NOME_FUNCIONARIO_VAZIO)
    @Size(min = 3, max = 50, message = NOME_FUNCIONARIO_TAMANHO)
    @Pattern(regexp = ILLEGAL_WORD, message = NOME_FUNCIONARIO_CARACTERE_INVALIDO)
    private String nome;

    @NotEmpty(message = EMAIL_FUNCIONARIO_VAZIO)
    @NotBlank(message = EMAIL_FUNCIONARIO_VAZIO)
    @Size(min = 10, max = 100, message = EMAIL_FUNCIONARIO_TAMANHO)
    @Pattern(regexp = VALIDATION_EMAIL, message = EMAIL_FUNCIONARIO_CARACTERE_INVALIDO)
    private String email;

    @CPF(message = CPF_FUNCIONARIO_VAZIO)
    @NotNull
    private String cpf;

    @NotNull(message = DATA_NASCIMENTO_FUNCIONARIO_VAZIO)
    private DateTime dataNascimento;

    @NotNull(message = CARGO_FUNCIONARIO_VAZIO)
    private EnumCargo enumCargo;

    @NotNull(message = SALARIO_FUNCIONARIO_VAZIO)
    @Min(value = 1000, message = SALARIO_FUNCIONARIO_TAMANHO)
    private BigDecimal salario;

    @NotNull
    private DateTime dataSaida;

    @NotNull
    private DateTime dataEntrada;

    @NotNull(message = ENDERECO_FUNCIONARIO_VAZIO)
    private Endereco endereco;

    @NotEmpty(message = TELEFONE_FUNCIONARIO_VAZIO)
    private Set<Telefone> telefones;

    public void setDataSaida(DateTime dataSaida) {
        checkArgument(dataSaida.isAfter(dataEntrada), "A data de saida da empresa precisa ser maior do que a data de entrada");
        this.dataSaida = dataSaida;
    }

    public void setDataEntrada(DateTime dataEntrada) {
        isLessThanCreateDate(new DateTime(), dataEntrada,
                "A data de entrada do funcionario não pode ser menor do que a data de criação da empresa");
        isDataGreaterThanCurrent(dataEntrada,
                "data de entrada do funcionario não pode ser maior" + " do que hora atual");
        this.dataEntrada = dataEntrada;
    }

    public void setDataNascimento(DateTime dataNascimento) {
        isDataGreaterThanCurrent(dataNascimento,
                "data de nascimento de funcionario não pode" + " ser maior do que hora atual");
        isDataLessThan1920(dataNascimento, "A data de cadastro do funcionario não pode ser menor do que 1920");
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cpf).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        return new EqualsBuilder().append(cpf, other.cpf).isEquals();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}