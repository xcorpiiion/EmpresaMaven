package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @NotEmpty(message = Constante.NOME_NAO_PODE_ESTA_VAZIO)
    @Length(min = 3, max = 50)
    private String nome;
    
    @Pattern(regexp = Constante.VALIDATION_EMAIL, message = Constante.EMAIL_INVALIDO)
    @NotEmpty(message = Constante.EMAIL_NAO_PODE_ESTA_VAZIO)
    private String email;

    @NotNull(message = Constante.DATA_DE_NASCIMENTO_NAO_PODE_SER_NULLA)
    private Date dataNascimento;

    @Min(value = 0, message = "O valor é invalido")
    private BigDecimal dinheiroCarteira;

    @NotNull(message = Constante.O_ENDEREÇO_ESTA_NULL)
    private Endereco endereco;

    @NotEmpty(message = Constante.O_CPF_ESTA_VAZIO)
    @NotBlank(message = Constante.O_CPF_ESTA_COM_UM_ESPAÇO_EM_BRANCO)
    @CPF(message = Constante.O_CPF_ESTA_INVALIDO)
    private String cpf;
    
    private List<Produto> carrinhoProduto;

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
    
    public BigDecimal getDinheiroCarteira() {
        return dinheiroCarteira;
    }

    public void setDinheiroCarteira(BigDecimal dinheiroCarteira) {
        this.dinheiroCarteira = new BigDecimal(0).add(dinheiroCarteira);
    }

    public void setCarrinhoProduto(List<Produto> carrinhoProduto) {
        this.carrinhoProduto = carrinhoProduto;
    }

    public List<Produto> getCarrinhoProduto() {
        return carrinhoProduto;
    }

    public void setProdutosComprados(List<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

    public List<Produto> getProdutosComprados() {
        return produtosComprados;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean produtoExisteNaLoja(String nomeProduto, Empresa loja) {
        return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
    }

    public boolean produtoEstaNoCarrinho(String nomeProduto, Cliente carrinhoCliente) {
        return carrinhoCliente.getCarrinhoProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
    }

    public void addItensCarrinho(Cliente cliente, Empresa loja, String nomeProduto, int qtdProdutoAddCarrinho) {
        loja.mostrarProdutos();
        if (produtoExisteNaLoja(nomeProduto, loja)) {

            if (qtdProdutoAddCarrinho < 1) {
                throw new IllegalArgumentException("Você precisa pelo menos add 1 produto ao carrinho");
            }
            if (loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto) && prod.getEstoque() < qtdProdutoAddCarrinho)) {
                throw new IllegalArgumentException("A quantidade de produtos que você quer colocar no carrinho é maior do que a" + " quantidade em estoque");
            }
            for(int i = 0 ; i < qtdProdutoAddCarrinho ; i++) {
                cliente.carrinhoProduto.addAll(loja.getProduto().stream().filter(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)).collect(Collectors.toList()));
            }
        } else {
            throw new IllegalArgumentException("O produto não existe");
        }

    }

    public void compraProduto(Cliente cliente, String nomeProduto, int qtdProdutosCompra) {
        if (!produtoEstaNoCarrinho(nomeProduto, cliente)) {
            throw new IllegalArgumentException("O produto não existe no carrinho");
        }
        BigDecimal totalPreco = new BigDecimal(0);
        for(Produto prod : cliente.getCarrinhoProduto()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                if (qtdProdutosCompra > prod.getEstoque()) {
                    throw new IllegalArgumentException("A quantidade de produtos que você quer comprar é maior do que a " + "quantidade em estoque");
                }
                totalPreco = totalPreco.add(prod.getPreco());
            }
        }

        if (cliente.getDinheiroCarteira().compareTo(totalPreco) < 0) {
            throw new IllegalArgumentException("Dinheiro insulficiente para comprar produtos");
        }
        cliente.dinheiroCarteira = cliente.dinheiroCarteira.subtract(totalPreco);

        for(Produto prod : cliente.getCarrinhoProduto()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                cliente.produtosComprados.add(prod);
            }
        }
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
        append(getCarrinhoProduto()).append(", produtos comprados: ").append(getProdutosComprados()).toString();        
    }

}
