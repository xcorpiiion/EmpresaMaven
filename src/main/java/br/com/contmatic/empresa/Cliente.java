package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.constantes.Constante;


public class Cliente {

    @NotEmpty(message = Constante.O_CPF_ESTA_VAZIO)
    @NotBlank(message = Constante.O_CPF_ESTA_COM_UM_ESPAÇO_EM_BRANCO)
    @CPF(message = Constante.O_CPF_ESTA_INVALIDO)
    private String cpf;
    
    @NotBlank(message = Constante.NOME_NAO_PODE_ESTA_VAZIO)
    @NotEmpty(message = Constante.NOME_NAO_PODE_ESTA_VAZIO)
    @Length(min = 3, max = 50)
    private String nome;
    
    @NotBlank(message = Constante.EMAIL_NAO_PODE_ESTA_VAZIO)
    @Pattern(regexp = Constante.VALIDATION_EMAIL, message = Constante.EMAIL_INVALIDO)
    @NotEmpty(message = Constante.EMAIL_NAO_PODE_ESTA_VAZIO)
    private String email;

    @NotNull(message = Constante.DATA_DE_NASCIMENTO_NAO_PODE_SER_NULLA)
    private Date dataNascimento;

    @Min(value = 0, message = "O valor é invalido")
    private BigDecimal dinheiroCarteira;

    @NotNull(message = Constante.O_ENDEREÇO_ESTA_NULL)
    private Endereco endereco;
    
    @Valid
    private List<Produto> carrinhoProdutos;

    @Valid
    private List<Produto> produtosComprados;

    public Cliente(String nome, String email, Date dataNascimento, Endereco endereco, String cpf) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public Cliente() {
        
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getDinheiroCarteira() {
        return dinheiroCarteira;
    }

    public void setDinheiroCarteira(BigDecimal dinheiroCarteira) {
        this.dinheiroCarteira = new BigDecimal(0).add(dinheiroCarteira);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getCarrinhoProdutos() {
        return carrinhoProdutos;
    }

    public void setCarrinhoProdutos(List<Produto> carrinhoProdutos) {
        this.carrinhoProdutos = carrinhoProdutos;
    }

    public List<Produto> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(List<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nome).append(email).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return new EqualsBuilder().append(nome, other.nome).append(email, other.email).isEquals();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Cliente: ").append(getNome()).append(", email: ").append(getEmail()).append(", data de nascimento: ").
        append(getDataNascimento()).append(", dinheiro: ").append(getDinheiroCarteira()).append(", produtos no carrinho: ").
        append(getCarrinhoProdutos()).append(", produtos comprados: ").append(getProdutosComprados()).toString();        
    }

}
