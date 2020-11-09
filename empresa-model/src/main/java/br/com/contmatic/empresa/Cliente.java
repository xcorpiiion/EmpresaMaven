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
 * The Class Cliente.
 */
public class Cliente {

    /** The cpf. */
    @CPF(message = CPF_CLIENTE_INVALIDO)
    private String cpf;

    /** The nome. */
    @NotNull(message = NOME_CLIENTE_VAZIO)
    @NotEmpty(message = NOME_CLIENTE_VAZIO)
    @NotBlank(message = NOME_CLIENTE_VAZIO)
    @Size(min = 3, max = 50, message = NOME_CLIENTE_TAMANHO)
    @Pattern(regexp = ILLEGAL_WORD, message = NOME_CLIENTE_CARACTERE_INVALIDO)
    private String nome;

    /** The email. */
    @NotEmpty(message = EMAIL_CLIENTE_VAZIO)
    @NotBlank(message = EMAIL_CLIENTE_VAZIO)
    @NotNull(message = EMAIL_CLIENTE_VAZIO)
    @Size(min = 10, max = 100, message = EMAIL_CLIENTE_TAMANHO)
    @Pattern(regexp = VALIDATION_EMAIL, message = EMAIL_CLIENTE_CARACTERE_INVALIDO)
    private String email;

    /** The data nascimento. */
    @NotNull(message = DATA_NASCIMENTO_CLIENTE_VAZIO)
    private DateTime dataNascimento;

    @NotNull
    private DateTime dataCadastro;

    /**
     * The dinheiro carteira.
     */
    @Min(value = 0, message = DINHEIRO_CARTEIRA_CLIENTE_TAMANHO)
    private BigDecimal dinheiroCarteira;

    /** The endereco. */
    @NotNull(message = ENDERECO_CLIENTE_VAZIO)
    private Endereco endereco;

    /** The telefones. */
    @NotEmpty
    @NotNull(message = TELEFONE_CLIENTE_VAZIO)
    private Set<Telefone> telefones;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String email, DateTime dataNascimento, Endereco endereco, Set<Telefone> telefones) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.setDataCadastro(new DateTime());
        this.telefones = telefones;
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

    public DateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(DateTime dataCadastro) {
        isDataGreaterThanCurrent(dataCadastro, "hora de cadastro não pode ser maior do que hora atual");
        isDataLessThan1998(dataCadastro, "A data de cadastro do cliente não pode ser menor do que 1998");
        this.dataCadastro = dataCadastro;
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
        isDataGreaterThanCurrent(dataNascimento, "data de nascimento de cliente não pode" +
                " ser maior do que hora atual");
        isDataLessThan1920(dataNascimento,
                "A data de cadastro do cliente não pode ser menor do que 1920");
        this.dataNascimento = dataNascimento;
    }

    /**
     * Gets the dinheiro carteira.
     *
     * @return the dinheiro carteira
     */
    public BigDecimal getDinheiroCarteira() {
        return dinheiroCarteira;
    }

    /**
     * Sets the dinheiro carteira.
     *
     * @param dinheiroCarteira the new dinheiro carteira
     */
    public void setDinheiroCarteira(BigDecimal dinheiroCarteira) {
        this.dinheiroCarteira = new BigDecimal(0).add(dinheiroCarteira);
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

    public Set<Telefone> getTelefones() {
        return telefones;
    }

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
        Cliente other = (Cliente) obj;
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
