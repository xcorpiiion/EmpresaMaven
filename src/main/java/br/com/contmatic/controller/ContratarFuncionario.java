package br.com.contmatic.controller;

import java.math.BigDecimal;
import org.joda.time.DateTime;
import com.google.common.base.Preconditions;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.services.CargoFuncionario;

public final class ContratarFuncionario {

    private ContratarFuncionario() {

    }

    public static void contratarFuncionario(String nome, String email, Cargo cargo, BigDecimal salario, DateTime dataNascimento, Empresa loja, TipoContrato tipoContrato, Endereco endereco, String cpf,
                                            Funcionario funcionario) {
        CargoFuncionario.verificaCargoIsRH(funcionario);
        Preconditions.checkArgument(!funcinarioIsCadastrado(loja, cpf), new IllegalArgumentException("O funcionario já foi contratado"));
        loja.getFuncionario().add(new Funcionario(nome, email, salario, cargo, dataNascimento, tipoContrato, endereco, cpf));
    }

    private static boolean funcinarioIsCadastrado(Empresa loja, String cpf) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getCpf().equals(cpf));
    }

}
