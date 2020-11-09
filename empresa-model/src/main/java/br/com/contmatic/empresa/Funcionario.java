package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

import static br.com.contmatic.utils.Constante.ILLEGAL_WORD;
import static br.com.contmatic.utils.Constante.VALIDATION_EMAIL;
import static br.com.contmatic.utils.Mensagem.*;
import static br.com.contmatic.empresa.utils.FieldValidation.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * The Class Funcionario.
 */
public class Funcionario {

    /**
     * The nome.
     */
    @NotBlank(message = NOME_FUNCIONARIO_VAZIO)
    @NotNull(message = NOME_FUNCIONARIO_VAZIO)
    @NotEmpty(message = NOME_FUNCIONARIO_VAZIO)
    @Size(min = 3, max = 50, message = NOME_FUNCIONARIO_TAMANHO)
    @Pattern(regexp = ILLEGAL_WORD, message = NOME_FUNCIONARIO_CARACTERE_INVALIDO)
    private String nome;

    /**
     * The email.
     */
    @NotEmpty(message = EMAIL_FUNCIONARIO_VAZIO)
    @NotBlank(message = EMAIL_FUNCIONARIO_VAZIO)
    @NotNull(message = EMAIL_FUNCIONARIO_VAZIO)
    @Size(min = 10, max = 100, message = EMAIL_FUNCIONARIO_TAMANHO)
    @Pattern(regexp = VALIDATION_EMAIL, message = EMAIL_FUNCIONARIO_CARACTERE_INVALIDO)
    private String email;

    /**
     * The cpf.
     */
    @CPF(message = CPF_FUNCIONARIO_VAZIO)
    private String cpf;

    /**
     * The data nascimento.
     */
    @NotNull(message = DATA_NASCIMENTO_FUNCIONARIO_VAZIO)
    private DateTime dataNascimento;

    /**
     * The salario.
     */
    @NotNull(message = SALARIO_FUNCIONARIO_VAZIO)
    @Min(value = 1000, message = SALARIO_FUNCIONARIO_TAMANHO)
    private BigDecimal salario;

    @NotNull
    private DateTime dataSaida;

    @NotNull
    private DateTime dataEntrada;


    /**
     * The endereco.
     */
    @NotNull(message = ENDERECO_FUNCIONARIO_VAZIO)
    private Endereco endereco;

    /**
     * The telefones.
     */
    @NotEmpty
    @NotNull(message = TELEFONE_FUNCIONARIO_VAZIO)
    private Set<Telefone> telefones;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, DateTime dataNascimento, BigDecimal salario, DateTime dataEntrada, Endereco endereco, Set<Telefone> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.dataEntrada = dataEntrada;
        this.endereco = endereco;
        this.telefones = telefones;
    }

    public DateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(DateTime dataSaida) {
        isGreaterThanEmpresaCreateDate(dataSaida, dataEntrada);
        this.dataSaida = dataSaida;
    }

    public DateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(DateTime dataEntrada) {
        isLessThanCreateDate(new DateTime(), dataEntrada,
                "A data de entrada do funcionario não pode ser menor do que a data de criação da empresa");
        isDataGreaterThanCurrent(dataEntrada, "data de entrada do funcionario não pode ser maior" +
                " do que hora atual");
        this.dataEntrada = dataEntrada;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Sets the endereco.
     *
     * @param endereco the new endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Gets the salario.
     *
     * @return the salario
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * Sets the salario.
     *
     * @param salario the new salario
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the data nascimento.
     *
     * @return the data nascimento
     */
    public DateTime getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the data nascimento.
     *
     * @param dataNascimento the new data nascimento
     */
    public void setDataNascimento(DateTime dataNascimento) {
        isDataGreaterThanCurrent(dataNascimento, "data de nascimento de funcionario não pode" +
                " ser maior do que hora atual");
        isDataLessThan1920(dataNascimento,
                "A data de cadastro do funcionario não pode ser menor do que 1920");
        this.dataNascimento = dataNascimento;
    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the cpf.
     *
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Gets the telefones.
     *
     * @return the telefones
     */
    public Set<Telefone> getTelefones() {
        return telefones;
    }

    /**
     * Sets the telefones.
     *
     * @param telefone1s the new telefones
     */
    public void setTelefones(Set<Telefone> telefone1s) {
        this.telefones = telefone1s;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cpf).toHashCode();
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
        Funcionario other = (Funcionario) obj;
        return new EqualsBuilder().append(cpf, other.cpf).isEquals();
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