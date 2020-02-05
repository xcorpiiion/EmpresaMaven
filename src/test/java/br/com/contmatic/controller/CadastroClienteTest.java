package br.com.contmatic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.constantes.controller.CadastroCliente;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.fixture.factory.GeradorCpf;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class CadastroClienteTest {

    private static CadastroCliente cadastroCliente;

    private List<Cliente> clientes;

    private static Empresa loja;

    private static List<Produto> produtos;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setProduto(produtos);
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
        cadastroCliente = new CadastroCliente(loja);
    }

    @Before
    public void addDadosCliente() {
        clientes = new ArrayList<>();
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        cadastroCliente.cadastrarCliente(clientes.get(0).getNome(), clientes.get(0).getEmail(), new Date(), Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf());
    }

    @Test()
    public void deve_existir_cliente() {
        cadastroCliente.cadastrarCliente("lucas", "lucas@gmail.com", new Date(), Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf());
        assertTrue("Cliente não existe", loja.clienteExiste(loja, loja.getCliente().get(0).getCpf()));
    }
    
    @Test
    public void nao_deve_existir_cliente_com_mesmo_dados_para_cadastrar() {
        assertFalse("O cliente existe", loja.clienteExiste(loja, GeradorCpf.gerardorRandomCpf()));
    }

    @Test
    public void nao_deve_settar_empresa_null() {
        cadastroCliente.setLoja(null);
        assertNull(cadastroCliente.getLoja());
    }

    @Test
    public void deve_gerar_hashCodes_iguais_para_diferentes_objetos_com_o_mesmo_atributo_loja() {
        CadastroCliente cadastroCliente1 = new CadastroCliente(loja);
        CadastroCliente cadastroCliente2 = new CadastroCliente(loja);
        assertEquals(cadastroCliente1.hashCode(), cadastroCliente2.hashCode());
    }

    @Test
    public void deve_igualar_diferentes_objetos_se_possuirem_o_mesmo_atributo_loja() {
        CadastroCliente cadastroCliente1 = new CadastroCliente(loja);
        CadastroCliente cadastroCliente2 = new CadastroCliente(loja);
        System.out.println(cadastroCliente1.equals(cadastroCliente2));
        assertTrue(cadastroCliente1.equals(cadastroCliente2));
    }

    @Test
    public void deve_igualar_mesmos_objetos() {
        CadastroCliente cadastroCliente1 = new CadastroCliente(loja);
        assertTrue(cadastroCliente1.equals(cadastroCliente1));
    }

    @After
    public void mostrarLoja() {
        System.out.println(cadastroCliente);
    }

}
