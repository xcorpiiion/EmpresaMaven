package br.com.contmatic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class AlterarDadosProdutoTest {
    
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
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
        loja.setProduto(produtos);
    }

    @Before
    public void add_dados_funcionario() {
        funcionario = Fixture.from(Funcionario.class).gimme("valid");
        funcionario.setCargo(Cargo.REPOSITOR);
    }
    
    @Test
    public void deve_alterar_nome_produto() {
        AlterarDadosProduto.alterarNomeProduto(produtos.get(0).getNome(), "Tablet", loja, funcionario);
        assertEquals("Tablet", loja.getProduto().get(0).getNome());
    }
    
    @Test
    public void deve_alterar_estoque() {
        AlterarDadosProduto.alterarEstoqueProduto(produtos.get(0).getNome(), 10, loja, funcionario);
        assertTrue(loja.getProduto().get(0).getEstoque() == 10);
    }
    
    @Test
    public void deve_alterar_preco() {
        AlterarDadosProduto.alterarPrecoProduto(produtos.get(0).getNome(), new BigDecimal(350.00), loja, funcionario);
        assertTrue(loja.getProduto().get(0).getPreco().compareTo(new BigDecimal(350.00)) == 0);
    }
    
}
