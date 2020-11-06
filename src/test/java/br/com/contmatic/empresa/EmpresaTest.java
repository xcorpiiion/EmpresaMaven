package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import com.github.javafaker.Faker;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * The Class EmpresaTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {
    
    /** The produtos. */
    private static List<Produto> produtos;

    /** The funcionarios. */
    private List<Funcionario> funcionarios;

    /** The loja 2. */
    private static Empresa loja;

    private static Empresa loja2;

    private Set<Telefone> telefones;

    private static Faker faker;

    /**
     * Cadastrar empresa.
     */
    @BeforeClass
    public static void cadastrar_empresa() {
        faker = new Faker();
        loadTemplates("br.com.contmatic.fixture.factory");
        loja = from(Empresa.class).gimme(VALID);
        loja.getEndereco().add(from(Endereco.class).gimme(VALID));
        loja2 = from(Empresa.class).gimme(VALID);
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(from(Funcionario.class).gimme(VALID));
    }

    @Before
    public void addDadosTelefone() {
        telefones = new HashSet<>();
        telefones.add(from(Telefone.class).gimme(VALID));
        loadTemplates("br.com.contmatic.fixture.factory");
        telefones.add(from(Telefone.class).gimme(VALID));
    }

    /**
     * Add dados produto.
     */
    @Before
    public void add_dados_produto() {
        produtos = new ArrayList<>();
        produtos.add(from(Produto.class).gimme(VALID));
    }

    @Test
    public void deve_mudar_nome_empresa() {
        final String nome = faker.name().firstName();
        loja.setNome(nome);
        assertEquals(nome, loja.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        loja = from(Empresa.class).gimme(NOME_NULL);
        assertTrue(returnAnnotationMsgError(loja, NOME_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        loja = from(Empresa.class).gimme(NOME_EMPTY);
        assertTrue(returnAnnotationMsgError(loja, NOME_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        loja = from(Empresa.class).gimme(NOME_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(loja, NOME_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        loja = from(Empresa.class).gimme(NOME_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(loja, NOME_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        loja = from(Empresa.class).gimme(NOME_GREATER_CARACTER);
        assertTrue(returnAnnotationMsgError(loja, NOME_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        loja = from(Empresa.class).gimme(NOME_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(loja, NOME_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_mudar_email_empresa() {
        final String email = faker.internet().emailAddress();
        loja.setEmail(email);
        assertEquals(email, loja.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        loja = from(Empresa.class).gimme(EMAIL_NULL);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        loja = from(Empresa.class).gimme(EMAIL_EMPTY);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        loja = from(Empresa.class).gimme(EMAIL_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        loja = from(Empresa.class).gimme(EMAIL_LESS_10_CARACTERES);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_TAMANHO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        loja = from(Empresa.class).gimme(EMAIL_WITH_BLANK_SPACE_IN_WORD);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        loja = from(Empresa.class).gimme(EMAIL_WITHOUT_ARROBA);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        loja = from(Empresa.class).gimme(EMAIL_WITHOUT_PONTO_COM);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        loja = from(Empresa.class).gimme(EMAIL_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(loja, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_add_telefones_na_lista_telefoness() {
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
        loja = from(Empresa.class).gimme(CNPJ_NULL);
        assertNull(loja.getCnpj());
    }

    /**
     * Nao deve aceitar cnpj com letras.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_letras() {
        loja = from(Empresa.class).gimme(CNPJ_CONTAINS_WORD);
        assertTrue(returnAnnotationMsgError(loja, CNPJ_EMPRESA_INVALIDO));
    }

    /**
     * Nao deve aceitar cnpj null com menos 14 numeros.
     */
    @Test
    public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
        loja = from(Empresa.class).gimme(CNPJ_WRONG_SIZE);
        assertTrue(returnAnnotationMsgError(loja, CNPJ_EMPRESA_INVALIDO));
    }

    @Test
    public void deve_add_funcionario_na_empresa() {
        loja.setFuncionario(funcionarios);
        assertTrue(loja.getFuncionario().size() > 0);
    }

    @Test
    public void deve_add_cliente_na_empresa() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(from(Cliente.class).gimme(VALID));
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
        endereco.add(from(Endereco.class).gimme(VALID));
        loja.setEndereco(endereco);
        assertTrue(loja.getEndereco().size() > 0);
    }

    /**
     * Deve ter o mesmo cnpj para serem iguais.
     */
    @Test()
    public void deve_retornar_true_caso_tenham_mesmo_cnpj() {
        loja2.setCnpj(loja.getCnpj());
        assertEquals(loja, loja2);
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
        loja2 = from(Empresa.class).gimme(VALID);
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
    public void deve_retornar_true_caso_contenha_a_palavra_telefoness_no_toString() {
        assertTrue(loja.toString().contains("telefoness"));
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
