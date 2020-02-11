package br.com.contmatic.controller;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class CadastrarProdutoTest {

    private static List<Produto> produtos;

    private Funcionario funcionario;

    private static Empresa loja;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setProduto(produtos);
    }

    @Before
    public void add_dados_funcionario() {
        funcionario = Fixture.from(Funcionario.class).gimme("valid");
        funcionario.setCargo(Cargo.REPOSITOR);
    }
    
    @Test
    public void deve_cadastrar_produto() {
        CadastrarProduto.cadastrarNovoProdutoNaLoja("Tablet", new BigDecimal(350.00), 50, loja, funcionario);
        assertTrue("O produto não existe", loja.getProduto().get(4).getNome().equalsIgnoreCase("tablet"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_expection_caso_produto_esteja_cadastrado() {
        CadastrarProduto.cadastrarNovoProdutoNaLoja("Tablet", new BigDecimal(350.00), 50, loja, funcionario);
        CadastrarProduto.cadastrarNovoProdutoNaLoja("Tablet", new BigDecimal(350.00), 50, loja, funcionario);
        assertTrue("O produto não existe", loja.getProduto().get(4).getNome().equalsIgnoreCase("tablet"));
    }

}
