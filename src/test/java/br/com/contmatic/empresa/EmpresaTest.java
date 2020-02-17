package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
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
    private static Empresa loja;

    private static Empresa loja2;

    private Set<Telefone> telefones;

    /**
     * Cadastrar empresa.
     */
    @BeforeClass
    public static void cadastrar_empresa() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.getEndereco().add(Fixture.from(Endereco.class).gimme("valid"));
        loja2 = Fixture.from(Empresa.class).gimme("valid");
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
    }

    @Before
    public void addDadosTelefone() {
        telefones = new HashSet<>();
        telefones.add(Fixture.from(Telefone.class).gimme("valid"));
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefones.add(Fixture.from(Telefone.class).gimme("valid"));
    }

    /**
     * Add dados produto.
     */
    @Before
    public void add_dados_produto() {
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
    }

    @Test
    public void deve_mudar_nome_empresa() {
        loja.setNome("kratos games");
        assertEquals("kratos games", loja.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        loja = Fixture.from(Empresa.class).gimme("nomeNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        loja = Fixture.from(Empresa.class).gimme("nomeEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        loja = Fixture.from(Empresa.class).gimme("nomeBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        loja = Fixture.from(Empresa.class).gimme("nomeLess3Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        loja = Fixture.from(Empresa.class).gimme("nomeGreaterCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        loja = Fixture.from(Empresa.class).gimme("nomeWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_mudar_email_empresa() {
        loja.setEmail("kratos@gmail.com");
        assertEquals("kratos@gmail.com", loja.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        loja = Fixture.from(Empresa.class).gimme("emailNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        loja = Fixture.from(Empresa.class).gimme("emailEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        loja = Fixture.from(Empresa.class).gimme("emailBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        loja = Fixture.from(Empresa.class).gimme("emailLess10Caracteres");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_mais_100_caracteres() {
        loja = Fixture.from(Empresa.class).gimme("emailGreater100Caracteres");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        loja = Fixture.from(Empresa.class).gimme("emailWithBlankSpaceInWord");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
        loja = Fixture.from(Empresa.class).gimme("emailWithNumberAfterArroba");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        loja = Fixture.from(Empresa.class).gimme("emailWithoutArroba");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        loja = Fixture.from(Empresa.class).gimme("emailWithoutPontoCom");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_com() {
        loja = Fixture.from(Empresa.class).gimme("emailWithoutCom");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        loja = Fixture.from(Empresa.class).gimme("emailWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_add_telefone_na_lista_telefones() {
        loja.setTelefones(telefones);
        assertTrue(loja.getTelefones().size() > 0);
    }

    @Test
    public void deve_add_produto_na_loja() {
        loja.setProduto(produtos);
        assertTrue(loja.getProduto().size() > 0);
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
     * Nao deve aceitar cnpj com letras.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_letras() {
        loja = Fixture.from(Empresa.class).gimme("cnpjContainsWord");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar cnpj null com menos 14 numeros.
     */
    @Test
    public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
        loja = Fixture.from(Empresa.class).gimme("cnpjWrongSize");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void deve_retornar_true_caso_funcionario_seja_null() {
        loja = Fixture.from(Empresa.class).gimme("funcionarioNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_add_funcionario_na_empresa() {
        loja.setFuncionario(funcionarios);
        assertTrue(loja.getFuncionario().size() > 0);
    }
    
    @Test
    public void deve_retornar_true_caso_cliente_seja_null() {
        loja = Fixture.from(Empresa.class).gimme("clienteNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_add_cliente_na_empresa() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        loja.setCliente(clientes);
        assertTrue(loja.getCliente().size() > 0);
    }
    
    @Test
    public void nao_deve_aceitar_endereco_null() {
        assertNotNull(funcionarios.get(0).getEndereco());
    }
    
    @Test
    public void deve_add_endereco_empresa() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valid"));
        loja.setEndereco(endereco);
        assertTrue(loja.getEndereco().size() > 0);
    }

    /**
     * Deve ter o mesmo cnpj para serem iguais.
     */
    @Test()
    public void deve_retornar_true_caso_tenham_mesmo_cnpj() {
        loja2.setCnpj(loja.getCnpj());
        assertTrue(loja.equals(loja2));
    }
    
    @Test()
    public void deve_retornar_false_caso_compare_com_mesmo_objeto() {
        assertTrue(loja.equals(loja));
    }

    /**
     * Nao deve aceitar cnpj null para compara lojas.
     */
    @Test()
    public void deve_retornar_false_caso_compare_loja_com_null_usando_equals() {
        assertFalse(loja.equals(null));
    }

    @Test()
    public void deve_retornar_false_caso_compare_getClass_for_diferente() {
        assertFalse(loja.equals(new Object()));
    }
    
    /**
     * Deve ter o mesmo hash code para serem iguais.
     */
    @Test()
    public void deve_ter_o_mesmo_hashCode_para_serem_iguais() {
        loja2 = Fixture.from(Empresa.class).gimme("valid");
        loja2.setCnpj(loja.getCnpj());
        assertEquals(loja.hashCode(), loja2.hashCode());
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_nome_no_toString() {
        assertTrue(loja.toString().contains("nome"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_email_no_toString() {
        assertTrue(loja.toString().contains("email"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_cnpj_no_toString() {
        assertTrue(loja.toString().contains("cnpj"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_telefones_no_toString() {
        assertTrue(loja.toString().contains("telefones"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_enderecos_no_toString() {
        assertTrue(loja.toString().contains("enderecos"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_produtos_no_toString() {
        assertTrue(loja.toString().contains("produtos"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_funcionarios_no_toString() {
        assertTrue(loja.toString().contains("funcionarios"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_clientes_no_toString() {
        assertTrue(loja.toString().contains("clientes"));
    }
    
    /**
     * Mostrar dados empresa.
     */
    @AfterClass
    public static void mostrar_dados_empresa() {
        System.out.println(loja);
    }

}
