package br.com.contmatic.constantes.controller;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;

public class Login {

    public boolean verificaLoginFuncioanrio(String nome, String email, Empresa loja) {
        return loja.getFuncionario().stream().anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email));
    }

    public boolean verificaLoginCliente(String nome, String email, Empresa loja) {
        return loja.getCliente().stream().anyMatch(clien -> clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email));
    }

    public Cliente clienteThatDoLogin(String nome, String email, Empresa loja) {
        for(Cliente cliente : loja.getCliente()) {
            if (cliente.getNome().equalsIgnoreCase(nome) && cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        throw new IllegalArgumentException("O cliente que fez o login não existe");
    }

    public Funcionario funcionarioThatDoLogin(String nome, String email, Empresa loja) {
        for(Funcionario funcionario : loja.getFuncionario()) {
            if (funcionario.getNome().equalsIgnoreCase(nome) && funcionario.getEmail().equalsIgnoreCase(email)) {
                return funcionario;
            }
        }
        throw new IllegalArgumentException("O funcionario que fez o login não existe");
    }
}
