package br.com.contmatic.controller;

import java.util.Set;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Telefone;

public final class AddTelefone {
    
    private AddTelefone() {
        
    }
    
    public static void addTelefoneCliente(Cliente cliente, Telefone novoTelefone) {
        cliente.getTelefones().add(novoTelefone);
    }
    
    public static void addTelefoneCliente(Cliente cliente, Set<Telefone> novosTelefones) {
        cliente.getTelefones().addAll(novosTelefones);
    }
    
    public static void addTelefoneEmpresa(Empresa loja, Telefone novoTelefone) {
        loja.getTelefones().add(novoTelefone);
    }
    
    public static void addTelefoneEmpresa(Empresa loja, Set<Telefone> novosTelefones) {
        loja.getTelefones().addAll(novosTelefones);
    }
    
    public static void addTelefoneFuncionario(Funcionario funcionario, Telefone novoTelefone) {
        funcionario.getTelefones().add(novoTelefone);
    }
    
    public static void addTelefoneFuncionario(Funcionario funcionario, Set<Telefone> novosTelefones) {
        funcionario.getTelefones().addAll(novosTelefones);
    }
    
}
