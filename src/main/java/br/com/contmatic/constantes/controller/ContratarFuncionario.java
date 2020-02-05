package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;
import java.util.Date;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.services.IFuncinarioIsCadastrado;

public class ContratarFuncionario implements IFuncinarioIsCadastrado{
    
    private Funcionario funcionario;
    
    public ContratarFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public ContratarFuncionario() { 
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public void contratarFuncionario(String nome, String email, Cargo cargo, BigDecimal salario, Date dataNascimento, Empresa loja, TipoContrato tipoContrato,
                                     Endereco endereco, String cpf) {
        if (this.funcionario.getCargo() != Cargo.RH) {
            throw new IllegalArgumentException("Apenas o RH pode contratar funcionarios");
        }
        if (funcinarioIsCadastrado(loja, cpf)) {
            throw new IllegalArgumentException("O funcionario já foi contratado");
        }
        loja.getFuncionario().add(new Funcionario(nome, email, salario, cargo, dataNascimento, tipoContrato, endereco, cpf));
    }

    @Override
    public boolean funcinarioIsCadastrado(Empresa loja, String cpf) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getCpf().equals(cpf));
    }
        
}
