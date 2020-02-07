package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;

public final class AlterarDadosProduto {

    private AlterarDadosProduto() {
        
    }
    
    public static void alterarNomeProduto(String nomeProduto, String nomeNovoProduto, Empresa loja, Funcionario funcionario) {
        cargoFuncionario(funcionario);
        for(Produto prod : loja.getProduto()) {
            Preconditions.checkArgument(prod.getNome().equalsIgnoreCase(nomeProduto));
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                prod.setNome(nomeNovoProduto);
            }
        }

    }

    public static void alterarPrecoProduto(String nomeProduto, BigDecimal preco, Empresa loja, Funcionario funcionario) {
        cargoFuncionario(funcionario);
        for(Produto prod : loja.getProduto()) {
            if (prod.getNome().equals(nomeProduto)) {
                prod.setPreco(preco);
            }
        }

    }

    public static void alterarEstoqueProduto(String nomeProduto, int estoque, Empresa loja, Funcionario funcionario) {
        cargoFuncionario(funcionario);
        for(Produto prod : loja.getProduto()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                prod.setEstoque(estoque);
            }
        }

    }

    public static void alterarTodosDadosDoProduto(String nomeProduto, BigDecimal preco, int estoque, String nomeNovoProduto, Empresa loja, Funcionario funcionario) {
        cargoFuncionario(funcionario);
        for(Produto prod : loja.getProduto()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                prod.setNome(nomeNovoProduto);
                prod.setPreco(preco);
                prod.setEstoque(estoque);
            }
        }

    }

    public static void cargoFuncionario(Funcionario funcionario) {
        if (funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException(Constante.APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
        }
        
    }

}
