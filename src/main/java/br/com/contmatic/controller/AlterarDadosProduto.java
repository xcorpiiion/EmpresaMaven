package br.com.contmatic.controller;

import java.math.BigDecimal;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.services.CargoFuncionario;

public final class AlterarDadosProduto {

    private AlterarDadosProduto() {

    }

    public static void alterarNomeProduto(String nomeProduto, String nomeNovoProduto, Empresa loja, Funcionario funcionario) {
        CargoFuncionario.verificaCargoIsRepositor(funcionario);
        for(Produto produto : loja.getProduto()) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                produto.setNome(nomeNovoProduto);
            }
        }

    }

    public static void alterarPrecoProduto(String nomeProduto, BigDecimal preco, Empresa loja, Funcionario funcionario) {
        CargoFuncionario.verificaCargoIsRepositor(funcionario);
        for(Produto produto : loja.getProduto()) {
            if (produto.getNome().equals(nomeProduto)) {
                produto.setPreco(preco);
            }
        }
    }

    public static void alterarEstoqueProduto(String nomeProduto, int estoque, Empresa loja, Funcionario funcionario) {
        CargoFuncionario.verificaCargoIsRepositor(funcionario);
        for(Produto prod : loja.getProduto()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                prod.setEstoque(estoque);
            }
        }
    }

    public static void alterarTodosDadosDoProduto(String nomeProduto, BigDecimal preco, int estoque, String nomeNovoProduto, Empresa loja, Funcionario funcionario) {
        CargoFuncionario.verificaCargoIsRepositor(funcionario);
        for(Produto prod : loja.getProduto()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                prod.setNome(nomeNovoProduto);
                prod.setPreco(preco);
                prod.setEstoque(estoque);
            }
        }
    }

}
