package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Produto;

public final class CompraProduto {

    private CompraProduto() {

    }

    public static void compraProduto(Cliente cliente, String nomeProduto, int qtdProdutosCompra) {
        if (!produtoEstaNoCarrinho(nomeProduto, cliente)) {
            throw new IllegalArgumentException("O produto não existe no carrinho");
        }
        BigDecimal totalPreco = new BigDecimal(0);
        for(Produto prod : cliente.getProdutosNoCarrinho()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                if (qtdProdutosCompra > prod.getEstoque()) {
                    throw new IllegalArgumentException("A quantidade de produtos que você quer comprar é maior do que a " + "quantidade em estoque");
                }
                totalPreco = totalPreco.add(prod.getPreco());
            }
        }
        System.out.println(totalPreco);
        if (cliente.getDinheiroCarteira().compareTo(totalPreco) < 0) {
            throw new IllegalArgumentException("Dinheiro insulficiente para comprar produtos");
        }
        cliente.setDinheiroCarteira(cliente.getDinheiroCarteira().subtract(totalPreco));

        for(Produto prod : cliente.getProdutosNoCarrinho()) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                cliente.getProdutosComprados().add(prod);
            }
        }
        cliente.getProdutosNoCarrinho().clear();
        totalPreco = new BigDecimal(0);
    }

    private static boolean produtoEstaNoCarrinho(String nomeProduto, Cliente carrinhoCliente) {
        return carrinhoCliente.getProdutosNoCarrinho().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
    }

}
