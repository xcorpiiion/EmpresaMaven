package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;
import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;

/**
 * The Class Funcionario.
 */
public class Funcionario {

    /** The nome. */
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @Min(value = 2, message = Constante.NOME_E_MUITO_GRANDE)
    @Max(value = 60, message = Constante.NOME_E_MUITO_PEQUENO)
    private String nome;

    /** The email. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @Email(message = Constante.VALOR_NAO_E_VALIDO)
    private String email;

    /** The cpf. */
    @CPF(message = Constante.VALOR_NAO_E_VALIDO)
    private String cpf;

    /** The data nascimento. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private DateTime dataNascimento;

    /** The cargo. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private Cargo cargo;

    /** The salario. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @Min(value = 1000, message = Constante.PRECISA_SER_UM_VALOR_MAIOR)
    private BigDecimal salario;

    /** The endereco. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private Endereco endereco;

    /** The tipo contrato. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private TipoContrato tipoContrato;

    /** The telefones. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private Set<Telefone> telefones;

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

    public void setDataNascimento(DateTime dataNascimento) {
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
