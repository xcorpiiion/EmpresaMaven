package br.com.contmatic.constantes.controller;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.services.IFuncinarioIsCadastrado;

public class DemitirFuncionario implements IFuncinarioIsCadastrado {
    private Funcionario funcionario;

    public DemitirFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public void demitirFuncionario(String cpf, Funcionario funcionario, Empresa loja) {
        if (this.funcionario.getCargo() != Cargo.RH) {
            throw new IllegalArgumentException("Apenas o RH pode contratar funcionarios");
        }
        if (!funcinarioIsCadastrado(loja, cpf)) {
            throw new IllegalArgumentException("Não existe o funcionario com os dados informados");
        }
        loja.getFuncionario().removeIf(func -> func.getCpf().equals(cpf));
    }

    @Override
    public boolean funcinarioIsCadastrado(Empresa loja, String cpf) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getCpf().equals(cpf));
    }
}
