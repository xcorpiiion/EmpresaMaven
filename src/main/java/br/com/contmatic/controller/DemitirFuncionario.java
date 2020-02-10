package br.com.contmatic.controller;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;

public class DemitirFuncionario {
    
    private DemitirFuncionario() { 
        
    }
    
    public static void demitirFuncionario(String cpf, Funcionario funcionario, Empresa loja) {
        if (funcionario.getCargo() != Cargo.RH) {
            throw new IllegalArgumentException("Apenas o RH pode contratar funcionarios");
        }
        if (!funcinarioIsCadastrado(loja, cpf)) {
            throw new IllegalArgumentException("Não existe o funcionario com os dados informados");
        }
        loja.getFuncionario().removeIf(func -> func.getCpf().equals(cpf));
    }

    private static boolean funcinarioIsCadastrado(Empresa loja, String cpf) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getCpf().equals(cpf));
    }
}
