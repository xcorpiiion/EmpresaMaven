package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;

public class CadastrarProduto {

    private CadastrarProduto() {
        
    }
    
    public static void cadastrarProduto(String nome, BigDecimal preco, int estoque, Empresa loja, Funcionario funcionario) {
        cargoFuncionario(funcionario);
        if (hasProdutoInList(loja, nome, preco)) {
            loja.getProduto().stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco)).forEach(prod -> prod.setEstoque(prod.getEstoque() + estoque));
        } else {
            loja.getProduto().add(new Produto(nome, preco, estoque));
        }
    }

    private static boolean hasProdutoInList(Empresa loja, String nome, BigDecimal preco) {
        return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco));
    }

    private static void cargoFuncionario(Funcionario funcionario) {
        if (funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException("Apenas repositores podem cadastra os produtos");
        }
        
    }

}
