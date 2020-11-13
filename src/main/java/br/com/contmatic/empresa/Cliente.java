package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
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
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @CPF(message = CPF_CLIENTE_INVALIDO)
    @NotNull
    private String cpf;

    @NotEmpty(message = NOME_CLIENTE_VAZIO)
    @NotBlank(message = NOME_CLIENTE_VAZIO)
    @Size(min = 3, max = 50, message = NOME_CLIENTE_TAMANHO)
    @Pattern(regexp = ILLEGAL_WORD, message = NOME_CLIENTE_CARACTERE_INVALIDO)
    private String nome;

    @NotEmpty(message = EMAIL_CLIENTE_VAZIO)
    @NotBlank(message = EMAIL_CLIENTE_VAZIO)
    @Size(min = 10, max = 100, message = EMAIL_CLIENTE_TAMANHO)
    @Pattern(regexp = VALIDATION_EMAIL, message = EMAIL_CLIENTE_CARACTERE_INVALIDO)
    private String email;

    @NotNull(message = DATA_NASCIMENTO_CLIENTE_VAZIO)
    private DateTime dataNascimento;

    @NotNull
    private DateTime dataCadastro = new DateTime();

    @Min(value = 0, message = DINHEIRO_CARTEIRA_CLIENTE_TAMANHO)
    private BigDecimal dinheiroCarteira;

    @NotNull(message = ENDERECO_CLIENTE_VAZIO)
    private Endereco endereco;

    @NotEmpty(message = TELEFONE_CLIENTE_VAZIO)
    private Set<Telefone> telefones;

    public Cliente(String cpf, String nome, String email, DateTime dataNascimento, Endereco endereco,
                   Set<Telefone> telefones) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefones = telefones;
    }

    public void setDataCadastro(DateTime dataCadastro) {
        isDataGreaterThanCurrent(dataCadastro, "hora de cadastro não pode ser maior do que hora atual");
        isDataLessThan1998(dataCadastro, "A data de cadastro do cliente não pode ser menor do que 1998");
        this.dataCadastro = dataCadastro;
    }

    public void setDataNascimento(DateTime dataNascimento) {
        isDataGreaterThanCurrent(dataNascimento, "data de nascimento de cliente não pode" + " ser maior do que hora atual");
        isDataLessThan1920(dataNascimento, "A data de cadastro do cliente não pode ser menor do que 1920");
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(cpf)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return new EqualsBuilder()
                .append(cpf, cliente.cpf)
                .isEquals();
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}