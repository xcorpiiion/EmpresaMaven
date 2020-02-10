package br.com.contmatic.controller;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Produto;

public final class CompraProduto {

    private static BigDecimal totalPreco;

    private CompraProduto() {

    }

    public static void compraProduto(Cliente cliente, String nomeProduto, int qtdProdutosCompra) {
        totalPreco = new BigDecimal(0);
        checkArgument(produtoEstaNoCarrinho(nomeProduto, cliente), "O produto não existe no carrinho");
        somaValoresProdutos(cliente, nomeProduto, qtdProdutosCompra);
        Preconditions.checkArgument(possuiDinheiroParaCompraProduto(cliente), new IllegalArgumentException("Dinheiro insulficiente para comprar produtos"));
        descontaValorCompraDoDinheiro(cliente, totalPreco);
        addProdutoNaListaProdutosComprados(cliente, nomeProduto);
        removeProdutosCompradoDoCarrinho(cliente);
        totalPreco = new BigDecimal(0);
    }

    private static boolean produtoEstaNoCarrinho(String nomeProduto, Cliente carrinhoCliente) {
        return carrinhoCliente.getCarrinhoProdutos().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
    }

    private static void somaValoresProdutos(Cliente cliente, String nomeProduto, int qtdProdutosCompra) {
        for(Produto produto : cliente.getCarrinhoProdutos()) {
            if (verificaQualProdutoSeraSomado(produto, nomeProduto)) {
                Preconditions.checkArgument(qtdProdutosNoCarrinhoIsMenorEstoque(qtdProdutosCompra, produto),
                    new IllegalArgumentException("A quantidade de produtos que você quer comprar é maior do que a quantidade no estoque"));
                totalPreco = totalPreco.add(produto.getPreco());
            }
        }
    }

    private static boolean verificaQualProdutoSeraSomado(Produto produto, String nomeProduto) {
        return produto.getNome().equalsIgnoreCase(nomeProduto);
    }

    private static boolean qtdProdutosNoCarrinhoIsMenorEstoque(int qtdProdutosCompra, Produto produto) {
        return qtdProdutosCompra < produto.getEstoque();
    }

    private static boolean possuiDinheiroParaCompraProduto(Cliente cliente) {
        return cliente.getDinheiroCarteira().compareTo(totalPreco) < 0;
    }

    private static void descontaValorCompraDoDinheiro(Cliente cliente, BigDecimal valorCompra) {
        cliente.setDinheiroCarteira(cliente.getDinheiroCarteira().subtract(valorCompra));
    }

    private static void addProdutoNaListaProdutosComprados(Cliente cliente, String nomeProduto) {
        for(Produto produto : cliente.getCarrinhoProdutos()) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                cliente.getProdutosComprados().add(produto);
            }
        }
    }
    
    private static void removeProdutosCompradoDoCarrinho(Cliente cliente) {
        for(Produto produto : cliente.getProdutosComprados()) {
            cliente.getCarrinhoProdutos().removeIf(prod -> prod.equals(produto));
        }
    }

}
