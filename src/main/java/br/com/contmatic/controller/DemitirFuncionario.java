package br.com.contmatic.controller;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.services.CargoFuncionario;

public class DemitirFuncionario {

    private DemitirFuncionario() {

    }

    public static void demitirFuncionario(String cpf, Funcionario funcionario, Empresa loja) {
        CargoFuncionario.verificaCargoIsRH(funcionario);
        Preconditions.checkArgument(funcinarioIsCadastrado(loja, cpf), new IllegalArgumentException("Não existe o funcionario com os dados informados"));
        loja.getFuncionario().removeIf(func -> func.getCpf().equals(cpf));
    }

    private static boolean funcinarioIsCadastrado(Empresa loja, String cpf) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getCpf().equals(cpf));
    }
}
