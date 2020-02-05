package br.com.contmatic.constantes.controller;

import java.math.BigDecimal;
import br.com.contmatic.constantes.Constante;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;

public class AlterarDadosProduto {
    
    private Funcionario funcionario;

    public AlterarDadosProduto(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public void alterarDadosProduto(int escolhaOpcoes, String nomeProduto, String nomeNovoProduto, Empresa loja) {
        if (this.funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException(Constante.APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
        }
        if (escolhaOpcoes == 1) {
            for(Produto prod : loja.getProduto()) {
                if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                    prod.setNome(nomeNovoProduto);
                }
            }
        }
    }

    public void alterarDadosProduto(int escolhaOpcoes, String nomeProduto, BigDecimal preco, Empresa loja) {
        if (this.funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException(Constante.APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
        }
        if (escolhaOpcoes == 2) {
            for(Produto prod : loja.getProduto()) {
                if (prod.getNome().equals(nomeProduto)) {
                    prod.setPreco(preco);
                }
            }
        }
    }

    public void alterarDadosProduto(int escolhaOpcoes, String nomeProduto, int estoque, Empresa loja) {
        if (this.funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException(Constante.APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
        }
        if (escolhaOpcoes == 3) {
            for(Produto prod : loja.getProduto()) {
                if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                    prod.setEstoque(estoque);
                }
            }
        }
    }

    public void alterarDadosProduto(int escolhaOpcoes, String nomeProduto, BigDecimal preco, int estoque, String nomeNovoProduto, Empresa loja) {
        if (this.funcionario.getCargo() != Cargo.REPOSITOR) {
            throw new IllegalArgumentException(Constante.APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO);
        }
        if (escolhaOpcoes == 4) {
            for(Produto prod : loja.getProduto()) {
                if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                    prod.setNome(nomeNovoProduto);
                    prod.setPreco(preco);
                    prod.setEstoque(estoque);
                }
            }
        }
    }
    
}
