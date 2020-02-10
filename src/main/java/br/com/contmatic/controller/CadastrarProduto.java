package br.com.contmatic.controller;

import java.math.BigDecimal;
import com.google.common.base.Preconditions;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.services.CargoFuncionario;

public class CadastrarProduto {

    private CadastrarProduto() {

    }

    public static void cadastrarNovoProdutoNaLoja(String nomeProduto, BigDecimal precoProduto, int estoqueProduto, Empresa loja, Funcionario funcionario) {
        CargoFuncionario.verificaCargoIsRepositor(funcionario);
        Preconditions.checkArgument(hasProdutoInLoja(loja, nomeProduto, precoProduto), new IllegalArgumentException("O produto já existe"));
        loja.getProduto().stream().filter(produto -> produto.getNome().equalsIgnoreCase(nomeProduto) && produto.getPreco().equals(precoProduto))
                .forEach(produto -> produto.setEstoque(produto.getEstoque() + estoqueProduto));
    }

    private static boolean hasProdutoInLoja(Empresa loja, String nomeProduto, BigDecimal precoProduto) {
        return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto) && prod.getPreco().equals(precoProduto));
    }

}
