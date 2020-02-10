package br.com.contmatic.services;

import com.google.common.base.Preconditions;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;

public final class CargoFuncionario {

    private CargoFuncionario() {

    }

    public static void verificaCargoIsRepositor(Funcionario funcionario) {
        Preconditions.checkArgument(funcionario.getCargo() == Cargo.REPOSITOR, new IllegalArgumentException("Precisa ser repositor para executar essa tarefa"));
    }
    
    public static void verificaCargoIsRH(Funcionario funcionario) {
        Preconditions.checkArgument(funcionario.getCargo() == Cargo.RH, new IllegalArgumentException("Precisa ser do RH para executar essa tarefa"));
    }
}
