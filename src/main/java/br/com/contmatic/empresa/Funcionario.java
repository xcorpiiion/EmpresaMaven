package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TipoContrato;
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
import static br.com.contmatic.empresa.utils.ValidacaoDataUtils.*;
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
     * The cargo.
     */
    @NotNull(message = CARGO_FUNCIONARIO_VAZIO)
    private Cargo cargo;

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
     * The tipo contrato.
     */
    @NotNull(message = TIPO_CONTRATO_FUNCIONARIO_VAZIO)
    private TipoContrato tipoContrato;

    /**
     * The telefones.
     */
    @NotNull(message = TELEFONE_FUNCIONARIO_VAZIO)
    private Set<Telefone> telefones;

    public DateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(DateTime dataSaida) {
        isGreaterThanEmpresaCreateDate(dataSaida, new DateTime());
        this.dataSaida = dataSaida;
    }

    public DateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(DateTime dataEntrada) {
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
     * Gets the cargo.
     *
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * Sets the cargo.
     *
     * @param cargo the new cargo
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
     * Gets the tipo contrato.
     *
     * @return the tipo contrato
     */
    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Sets the tipo contrato.
     *
     * @param tipoContrato the new tipo contrato
     */
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
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
     * @param telefones the new telefones
     */
    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
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
