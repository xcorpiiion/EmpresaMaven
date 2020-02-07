package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;
import java.util.Date;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;

public final class ContratarFuncionario{

    private ContratarFuncionario() {
        
    }
    
    public static void contratarFuncionario(String nome, String email, Cargo cargo, BigDecimal salario, Date dataNascimento, Empresa loja, TipoContrato tipoContrato,
                                     Endereco endereco, String cpf, Funcionario funcionario) {
        cargoFuncionario(funcionario);
        if (funcinarioIsCadastrado(loja, cpf)) {
            throw new IllegalArgumentException("O funcionario já foi contratado");
        }
        loja.getFuncionario().add(new Funcionario(nome, email, salario, cargo, dataNascimento, tipoContrato, endereco, cpf));
    }

    private static boolean funcinarioIsCadastrado(Empresa loja, String cpf) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getCpf().equals(cpf));
    }

    private static void cargoFuncionario(Funcionario funcionario) {
        if (funcionario.getCargo() != Cargo.RH) {
            throw new IllegalArgumentException("Apenas o RH pode contratar funcionarios");
        }
        
    }
        
}
