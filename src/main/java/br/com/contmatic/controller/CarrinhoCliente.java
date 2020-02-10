package br.com.contmatic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.common.base.Preconditions;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Produto;

public final class CarrinhoCliente {

    private CarrinhoCliente() {

    }

    public static void addItensCarrinho(Cliente cliente, Empresa loja, String nomeProduto, int qtdProdutoAddCarrinho) {
        Preconditions.checkArgument(qtdProdutoAddCarrinho > 0, new IllegalArgumentException("Você precisa pelo menos add 1 produto ao carrinho"));
        Preconditions.checkArgument(loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto) && prod.getEstoque() > qtdProdutoAddCarrinho),
            new IllegalArgumentException("A quantidade de produtos que você quer colocar no carrinho é maior do que a" + " quantidade em estoque"));
        List<Produto> produtos = new ArrayList<>();
        for(int i = 0 ; i < qtdProdutoAddCarrinho ; i++) {
            produtos.addAll(loja.getProduto().stream().filter(produto -> produto.getNome().equalsIgnoreCase(nomeProduto)).collect(Collectors.toList()));
        }
        cliente.setCarrinhoProdutos(produtos);
    }
}
