package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringSizeException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

    private static List<Produto> produtos;

    private List<Funcionario> funcionarios;

    private static Empresa loja, loja2;

    @BeforeClass
    public static void cadastrar_empresa() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
    }

    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
    }

    @Before
    public void add_dados_produto() {
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));

    }

    @Before
    public void deve_mostrar_endereco() {
        System.out.println(loja.getEndereco());
    }

    @Before
    public void ordena_os_produtos() {
        loja.mostrarProdutos();
    }

    @Test()
    public void nao_deve_aceitar_produto_null() {
        assertNotNull("O produto esta null", produtos);
    }

    @Test
    public void deve_settar_o_funcionario() {
        loja.setFuncionario(funcionarios);
    }

    @Test
    public void deve_settar_o_cliente() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        loja.setCliente(clientes);
    }

    @Test()
    public void nao_deve_aceitar_produto_que_nao_exista_na_loja() {
        loja.setProduto(produtos);
        assertTrue("O produto não existe", loja.produtoExiste(loja.getProduto().get(0).getNome()));
    }

    @Test
    public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros() {
        assertEquals(14, loja.getCnpj().length());
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_cnpj_null() {
        loja.setCnpj(null);
    }

    @Test
    public void nao_deve_aceitar_cnpj_vazio() {
        String cnpj = "";
        loja.setCnpj(cnpj);
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_cnpj_com_espaco_em_branco() {
        String cnpj = " ";
        loja.setCnpj(cnpj);
    }

    @Test(expected = RuntimeException.class)
    public void nao_deve_aceitar_cnpj_com_letras() {
        String cnpj = "a";
        loja.setCnpj(cnpj);
    }

    @Test(expected = RuntimeException.class)
    public void nao_deve_aceitar_cnpj_com_letras_e_numeros_juntos() {
        String cnpj = "1234567890123a";
        loja.setCnpj(cnpj);
    }

    @Test(expected = StringSizeException.class)
    public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
        String cnpj = "1234567890123";
        loja.setCnpj(cnpj);
    }

    @Test
    public void nao_deve_aceitar_endereco_null() {
        assertNotNull(funcionarios.get(0).getEndereco());
    }

    @Test
    public void nao_deve_aceitar_endereco_null_exception() {
        loja = Fixture.from(Empresa.class).gimme("enderecoNull");
        System.out.println(loja.getEndereco());
        assertNull(loja.getEndereco());
    }

    @Test()
    public void deve_ter_o_mesmo_cnpj_para_serem_iguais() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        loja2.setCnpj(loja.getCnpj());
        assertTrue("As empresas são iguais", loja.equals(loja2));
    }

    @Test()
    public void nao_deve_aceitar_null_para_comparar_lojas() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        assertFalse("As empresas são iguais", loja.equals(null));
    }

    @Test()
    public void nao_deve_aceitar_cnpj_null_para_compara_lojas() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        assertFalse("As empresas são iguais", loja.getCnpj().equals(null));
    }

    @Test()
    public void deve_ter_o_mesmo_hashCode_para_serem_iguais() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        loja2.setCnpj(loja.getCnpj());
        assertEquals("As lojas são iguais", loja.hashCode(), loja2.hashCode());
    }

    @AfterClass
    public static void mostrar_dados_empresa() {
        System.out.println(loja);
    }

}
