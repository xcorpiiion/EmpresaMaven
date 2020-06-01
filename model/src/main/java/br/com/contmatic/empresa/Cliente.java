package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Cliente.
 */
public class Cliente {

    /** The cpf. */
	@NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    @CPF(message = Mensagem.VALOR_NAO_E_VALIDO)
    private String cpf;

    /** The nome. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    @Size(min = 3, max = 50, message = Mensagem.VALOR_NAO_E_VALIDO)
    @Pattern(regexp = Constante.ILLEGAL_WORD, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String nome;

    /** The email. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    @Size(min = 10, max = 100, message = Mensagem.VALOR_NAO_E_VALIDO)
    @Pattern(regexp = Constante.VALIDATION_EMAIL, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String email;

    /** The data nascimento. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    private Date dataNascimento;

    /** The dinheiro carteira. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    @Min(value = 0, message = Mensagem.VALOR_NAO_E_VALIDO)
    private BigDecimal dinheiroCarteira;

    /** The endereco. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    private Endereco endereco;

    /** The telefones. */
    @NotNull(message = Mensagem.VALOR_ESTA_VAZIO)
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
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the data nascimento.
     *
     * @param dataNascimento the new data nascimento
     */
    public void setDataNascimento(Date dataNascimento) {
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
