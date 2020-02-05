package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;

public class Funcionario {

    @NotEmpty(message = "O nome não pode esta vazio")
    private String nome;

    @NotEmpty(message = "O email não pode esta vazio")
    private String email;

    @NotNull(message = "A data de nascimento não pode esta nullo")
    private Date dataNascimento;

    @NotNull(message = "O cargo não pode esta vazio")
    private Cargo cargo;

    @NotNull(message = "O salario não pode esta nullo")
    @Min(value = 1000, message = "O valor precisa ser um salario minimo")
    private BigDecimal salario;

    @NotNull(message = "O endereco não pode ser nullo")
    private Endereco endereco;

    @NotNull(message = "O tipo de contrato não pode ser nullo")
    private TipoContrato tipoContrato;

    @NotEmpty(message = "O cpf está vazio")
    @NotBlank(message = "O cpf está com um espaço em branco")
    @CPF(message = "O cpf é invalido")
    private String cpf;

    public Funcionario(String nome, String email, BigDecimal salario, Cargo cargo, Date dataNascimento, TipoContrato tipoContrato, Endereco endereco, String cpf) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.salario = salario;
        this.endereco = endereco;
        this.tipoContrato = tipoContrato;
        this.cpf = cpf;
    }

    public Funcionario() {
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cpf).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        return new EqualsBuilder().append(cpf, other.cpf).isEquals();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("------Dados do funcionario------").append("\nNome: ").append(getNome()).append("\nCPF: ").append(getCpf()).append("\nEmail: ").append(getEmail()).append("\nData de nascimento: ")
                .append(getDataNascimento()).append("\nCargo: ").append(getCargo()).append("\nTipo de contrato: ").append(getTipoContrato()).append("\nSalario: ").append(getSalario())
                .append("\n------Endereço------").append("\nRua: ").append(getEndereco().getRua()).append("\nNúmero residência: ").append(getEndereco().getNumeroResidencia()).append("\nBairro: ")
                .append(getEndereco().getBairro()).append("\nCep: ").append(getEndereco().getCep()).append("\nCidade: ").append(getEndereco().getCidade()).append("\nEstado: ")
                .append(getEndereco().getEstado()).toString();
    }

}
