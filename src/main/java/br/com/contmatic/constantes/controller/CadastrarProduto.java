package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.services.IHasProdutoInList;

public class CadastrarProduto implements IHasProdutoInList {

    private Funcionario funcionario;

    public CadastrarProduto(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void cadastrarProduto(String nome, BigDecimal preco, int estoque, Empresa loja) {
        if (this.funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException("Apenas repositores podem cadastra os produtos");
        }

        if (hasProdutoInList(loja, nome, preco)) {
            loja.getProduto().stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco)).forEach(prod -> prod.setEstoque(prod.getEstoque() + estoque));
        } else {
            loja.getProduto().add(new Produto(nome, preco, estoque));
        }
    }

    @Override
    public boolean hasProdutoInList(Empresa loja, String nome, BigDecimal preco) {
        return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco));
    }

}
