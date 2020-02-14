package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.constantes.Constante;

/**
 * The Class Empresa.
 */
public class Empresa {

    /** The nome. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    @Size(min = 3, max = 100, message = "O nome não é valido {max}")
    @Pattern(regexp = Constante.ILLEGAL_NUMBER, message = Constante.VALOR_NAO_E_VALIDO)
    private String nome;

    /** The email. */
    @Email(message = Constante.VALOR_NAO_E_VALIDO)
    private String email;

    /** The cnpj. */
    @CNPJ(message = "CNPJ não é valido")
    private String cnpj;
    
    /** The telefones. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private Set<Telefone> telefones;

    /** The enderecos. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private Set<Endereco> enderecos;

    /** The produtos. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private List<Produto> produtos;

    /** The funcionarios. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private List<Funcionario> funcionarios;

    /** The clientes. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private List<Cliente> clientes;
    
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
