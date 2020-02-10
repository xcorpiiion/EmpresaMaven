package br.com.contmatic.controller;

import java.util.Date;
import com.google.common.base.Preconditions;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;

public final class CadastroCliente {

    private CadastroCliente() {
        
    }
    
    public static void cadastrarClienteNaLoja(String nome, String email, Date dataNascimento, Endereco endereco, String cpf, Empresa loja) {
        Preconditions.checkArgument(!verificaClientePossuiCadastro(loja, cpf), new IllegalArgumentException("O cliente já está cadastrado"));
        loja.getCliente().add(new Cliente(nome, email, dataNascimento, endereco, cpf));
    }
    
    private static boolean verificaClientePossuiCadastro(Empresa loja, String cpf) {
        return loja.getCliente().stream().anyMatch(cliente -> cliente.getCpf().equalsIgnoreCase(cpf));
    }
    
}
