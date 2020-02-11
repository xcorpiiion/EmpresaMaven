package br.com.contmatic.empresa;

import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CNPJ;
import br.com.contmatic.constantes.Constante;

public class Empresa {

    @NotEmpty(message = "Nome não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Email não pode ser vazio")
    @Pattern(regexp = Constante.VALIDATION_EMAIL)
    private String email;

    @NotBlank(message = "O cnpj não pode ficar vazio")
    @NotEmpty(message = "O cnpj não pode ficar vazio")
    @CNPJ(message = "CNPJ não é valido")
    private String cnpj;
    
    @NotNull(message = Constante.O_TELEFONE_NAO_PODE_FICAR_VAZIO)
    private Set<Telefone> telefones;

    @NotNull(message = "Endereço não pode ficar nullo")
    private Set<Endereco> enderecos;

    @NotNull(message = "Produto não pode ficar nullo")
    private List<Produto> produtos;

    @NotNull(message = "O funcionairo não pode ser nullo")
    private List<Funcionario> funcionarios;

    @NotNull(message = "O cliente não pode ser nullo")
    private List<Cliente> clientes;

    public Empresa(String nome, String email, String cnpj, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.enderecos.add(endereco);
    }

    public Empresa() {
    }
    
    public Set<Endereco> getEndereco() {
        return enderecos;
    }

    public void setEndereco(Set<Endereco> endereco) {
        this.enderecos = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
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
