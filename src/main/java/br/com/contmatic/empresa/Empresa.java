package br.com.contmatic.empresa;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import br.com.contmatic.constantes.ValidationNullEmptyStringRule;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringSizeException;

public class Empresa {

    private String nome;

    private String email;

    private String cnpj;

    private Endereco endereco;

    private List<Produto> produtos;

    private List<Funcionario> funcionarios;

    private List<Cliente> clientes;

    public Empresa(String nome, String email, List<Produto> produtos, String cnpj, Endereco endereco) {
        setNome(nome);
        setEmail(email);
        setProduto(produtos);
        setCnpj(cnpj);
        setEndereco(endereco);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        ValidationNullEmptyStringRule.enderecoIsNull(endereco);
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        cnpjIsEmpty(cnpj);
        cnpjHasWord(cnpj);
        cnpjSizeValidation(cnpj);
        this.cnpj = cnpj;
    }

    public void setNome(String nome) {
        ValidationNullEmptyStringRule.stringIsEmpty(nome);
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        ValidationNullEmptyStringRule.stringIsEmpty(email);
        ValidationNullEmptyStringRule.emailValidation(email);
        this.email = email;
    }

    public void setProduto(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProduto() {
        return produtos;
    }

    public void setFuncionario(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Funcionario> getFuncionario() {
        return funcionarios;
    }

    public void setCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getCliente() {
        return clientes;
    }

    public boolean produtoExiste(String nome) {
        return produtos.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome));
    }

    public void mostrarProdutos() {
        produtos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
    }

    private void cnpjIsEmpty(String cnpj) {
        if (StringUtils.isEmpty(cnpj) || cnpj.trim().equals("")) {
            throw new EmptyStringException("O cnpj não pode ficar vazio");
        }
    }

    private void cnpjHasWord(String cnpj) {
        if (!StringUtils.isNumeric(cnpj)) {
            throw new IllegalArgumentException("O cnpj contém letras");
        }
    }

    private void cnpjSizeValidation(String cnpj) {
        if (cnpj.length() != 14) {
            throw new StringSizeException("O cnpj está errado");
        }
    }

    public boolean clienteExiste(Empresa lojaCliente, String nome, String email) {
        return lojaCliente.getCliente().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).toHashCode();
    }

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

    @Override
    public String toString() {
        return new StringBuilder().append("Nome: ").append(getNome()).append(", email: ").append(getEmail()).append(", endereco: ").append(getEndereco()).append(", produtos: ").append(getProduto())
                .append(", funcionarios: ").append(getFuncionario()).append(", clientes: ").append(getCliente()).toString();
    }

}
