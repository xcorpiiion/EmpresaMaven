package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class EmpresaTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

    /** The produtos. */
    private static List<Produto> produtos;

    /** The funcionarios. */
    private List<Funcionario> funcionarios;

    /** The loja 2. */
    private static Empresa loja, loja2;

    /**
     * Cadastrar empresa.
     */
    @BeforeClass
    public static void cadastrar_empresa() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.getEndereco().add(Fixture.from(Endereco.class).gimme("valid"));
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
    }

    /**
     * Add dados produto.
     */
    @Before
    public void add_dados_produto() {
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
    }

    /**
     * Nao deve aceitar produto null.
     */
    @Test()
    public void nao_deve_aceitar_produto_null() {
        assertNotNull("O produto esta null", produtos);
    }

    /**
     * Nao deve aceitar cnpj null.
     */
    @Test
    public void nao_deve_aceitar_cnpj_null() {
        loja = Fixture.from(Empresa.class).gimme("cnpjNull");
        assertNotNull(loja.getCnpj());
    }

    /**
     * Nao deve aceitar cnpj com espaco em branco.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_espaco_em_branco() {
        loja = Fixture.from(Empresa.class).gimme("cnpjBlankSpace");
        assertTrue(Constante.VALOR_ESTA_VAZIO, ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Constante.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar cnpj com letras.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_letras() {
        loja = Fixture.from(Empresa.class).gimme("cnpjContainsWord");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, "CNPJ não é valido"));
    }

    /**
     * Nao deve aceitar cnpj null com menos 14 numeros.
     */
    @Test
    public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
        loja = Fixture.from(Empresa.class).gimme("cnpjWrongSize");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, "CNPJ não é valido"));
    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void nao_deve_aceitar_endereco_null() {
        assertNotNull(funcionarios.get(0).getEndereco());
    }

    /**
     * Deve ter o mesmo cnpj para serem iguais.
     */
    @Test()
    public void deve_ter_o_mesmo_cnpj_para_serem_iguais() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        loja2.setCnpj(loja.getCnpj());
        assertTrue("As empresas são iguais", loja.equals(loja2));
    }

    /**
     * Nao deve aceitar null para comparar lojas.
     */
    @Test()
    public void nao_deve_aceitar_null_para_comparar_lojas() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        assertFalse("As empresas são iguais", loja.equals(null));
    }

    /**
     * Nao deve aceitar cnpj null para compara lojas.
     */
    @Test()
    public void nao_deve_aceitar_cnpj_null_para_compara_lojas() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        assertFalse("As empresas são iguais", loja.getCnpj().equals(null));
    }

    /**
     * Deve ter o mesmo hash code para serem iguais.
     */
    @Test()
    public void deve_ter_o_mesmo_hashCode_para_serem_iguais() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        loja2.setCnpj(loja.getCnpj());
        assertEquals("As lojas são iguais", loja.hashCode(), loja2.hashCode());
    }

    /**
     * Mostrar dados empresa.
     */
    @AfterClass
    public static void mostrar_dados_empresa() {
        System.out.println(loja);
    }

}
