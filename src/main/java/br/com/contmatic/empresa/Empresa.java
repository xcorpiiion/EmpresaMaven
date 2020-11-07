package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
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

/**
 * The Class Empresa.
 */
public class Empresa {

    /** The nome. */
    @NotEmpty(message = NOME_EMPRESA_VAZIO)
    @NotBlank(message = NOME_EMPRESA_VAZIO)
    @NotNull(message = NOME_EMPRESA_VAZIO)
    @Size(min = 3, max = 100, message = NOME_EMPRESA_TAMANHO)
    @Pattern(regexp = ILLEGAL_NUMBER, message = NOME_EMPRESA_CARACTERE_INVALIDO)
    private String nome;

    /** The email. */
    @NotEmpty(message = EMAIL_EMPRESA_VAZIO)
    @NotBlank(message = EMAIL_EMPRESA_VAZIO)
    @NotNull(message = EMAIL_EMPRESA_VAZIO)
    @Size(min = 10, max = 100, message = EMAIL_EMPRESA_TAMANHO)
    @Pattern(regexp = VALIDATION_EMAIL, message = EMAIL_EMPRESA_CARACTERE_INVALIDO)
    private String email;

    /** The cnpj. */
    @CNPJ(message = CNPJ_EMPRESA_INVALIDO)
    private String cnpj;

    /** The telefones. */
    @NotNull(message = TELEFONE_EMPRESA_VAZIO)
    @NotEmpty
    @Valid
    private Set<Telefone> telefones;

    @NotNull
    private DateTime dataCriacao;

    @NotNull
    private DateTime dataAlteracao;

    /** The enderecos. */
    @NotNull(message = ENDERECO_EMPRESA_VAZIO)
    private Set<Endereco> enderecos;

    @NotNull
    private List<Produto> produtos;

    @NotNull
    private List<Funcionario> funcionarios;

    @NotNull
    private List<Cliente> clientes;

    public Empresa() {
        super();
    }

    public Empresa(String nome, String cnpj, DateTime dataCriacao, Set<Endereco> enderecos) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.dataCriacao = dataCriacao;
        this.enderecos = enderecos;
        this.setDataAlteracao(dataCriacao);
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public Set<Endereco> getEndereco() {
        return enderecos;
    }

    /**
     * Sets the endereco.
     *
     * @param endereco the new endereco
     */
    public void setEndereco(Set<Endereco> endereco) {
        this.enderecos = endereco;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    public DateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(DateTime dataCriacao) {
        isDataGreaterThanCurrent(dataCriacao, "Data de criação da empresa não pode ser maior do que" +
                "hora atual");
        isDataLessThan1998(dataCriacao, "A data de criação da empresa não pode ser menor do que 1998");
        this.dataCriacao = dataCriacao;
    }

    public DateTime getDataAlteracao() {
        isDataLessThan1998(dataAlteracao, "A data de alteração da empresa não pode ser menor do que 1998");
        return dataAlteracao;
    }

    public void setDataAlteracao(DateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Sets the cnpj.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    /**
     * Sets the produto.
     *
     * @param produtos the new produto
     */
    public void setProduto(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Gets the produto.
     *
     * @return the produto
     */
    public List<Produto> getProduto() {
        return produtos;
    }

    /**
     * Sets the funcionario.
     *
     * @param funcionarios the new funcionario
     */
    public void setFuncionario(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * Gets the funcionario.
     *
     * @return the funcionario
     */
    public List<Funcionario> getFuncionario() {
        return funcionarios;
    }

    /**
     * Sets the cliente.
     *
     * @param clientes the new cliente
     */
    public void setCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    public List<Cliente> getCliente() {
        return clientes;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).toHashCode();
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
        Empresa other = (Empresa) obj;
        return new EqualsBuilder().append(cnpj, other.cnpj).isEquals();
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
