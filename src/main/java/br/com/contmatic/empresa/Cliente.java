package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.VALOR_ESTA_NULLO;
import static br.com.contmatic.constantes.Constante.VALOR_ESTA_VAZIO;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;

import br.com.contmatic.constantes.Constante;

/**
 * The Class Cliente.
 */
public class Cliente {

    /** The cpf. */
    @CPF(message = Constante.VALOR_NAO_E_VALIDO)
    private String cpf;

    /** The nome. */
    @NotEmpty(message = Constante.VALOR_ESTA_VAZIO)
    @NotBlank(message = Constante.VALOR_ESTA_VAZIO)
    @Length(min = 3, max = 50)
    private String nome;

    /** The email. */
    @Email(message = Constante.VALOR_NAO_E_VALIDO)
    @Range(min = 5, max = 500, message = "Não foi possivel criar o email")
    private String email;

    /** The data nascimento. */
    @NotNull(message = Constante.VALOR_ESTA_NULLO)
    private DateTime dataNascimento;

    /** The dinheiro carteira. */
    @Min(value = 0, message = Constante.VALOR_NAO_E_VALIDO)
    private BigDecimal dinheiroCarteira;

    /** The endereco. */
    @NotNull(message = VALOR_ESTA_NULLO)
    private Endereco endereco;

    /** The telefones. */
    @NotNull(message = VALOR_ESTA_VAZIO)
    private Set<Telefone> telefones;

    /** The carrinho produtos. */
    @Valid
    private List<Produto> carrinhoProdutos;

    /** The produtos comprados. */
    @Valid
    private List<Produto> produtosComprados;

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
     * Gets the carrinho produtos.
     *
     * @return the carrinho produtos
     */
    public List<Produto> getCarrinhoProdutos() {
        return carrinhoProdutos;
    }

    /**
     * Sets the carrinho produtos.
     *
     * @param carrinhoProdutos the new carrinho produtos
     */
    public void setCarrinhoProdutos(List<Produto> carrinhoProdutos) {
        this.carrinhoProdutos = carrinhoProdutos;
    }

    /**
     * Gets the produtos comprados.
     *
     * @return the produtos comprados
     */
    public List<Produto> getProdutosComprados() {
        return produtosComprados;
    }

    /**
     * Sets the produtos comprados.
     *
     * @param produtosComprados the new produtos comprados
     */
    public void setProdutosComprados(List<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
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
