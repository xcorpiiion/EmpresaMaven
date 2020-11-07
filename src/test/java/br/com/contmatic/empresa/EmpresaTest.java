package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import com.github.javafaker.Faker;
import org.joda.time.DateTime;
import org.junit.*;

import java.util.*;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
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
    private static Empresa empresa;

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
        empresa = from(Empresa.class).gimme(VALID);
        empresa.getEndereco().add(from(Endereco.class).gimme(VALID));
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
        empresa.setNome(nome);
        assertEquals(nome, empresa.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        empresa = from(Empresa.class).gimme(NOME_NULL);
        assertTrue(returnAnnotationMsgError(empresa, NOME_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        empresa = from(Empresa.class).gimme(NOME_EMPTY);
        assertTrue(returnAnnotationMsgError(empresa, NOME_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        empresa = from(Empresa.class).gimme(NOME_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(empresa, NOME_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        empresa = from(Empresa.class).gimme(NOME_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(empresa, NOME_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        empresa = from(Empresa.class).gimme(NOME_GREATER_CARACTER);
        assertTrue(returnAnnotationMsgError(empresa, NOME_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        empresa = from(Empresa.class).gimme(NOME_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(empresa, NOME_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_mudar_email_empresa() {
        final String email = faker.internet().emailAddress();
        empresa.setEmail(email);
        assertEquals(email, empresa.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        empresa = from(Empresa.class).gimme(EMAIL_NULL);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        empresa = from(Empresa.class).gimme(EMAIL_EMPTY);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        empresa = from(Empresa.class).gimme(EMAIL_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        empresa = from(Empresa.class).gimme(EMAIL_WITH_BLANK_SPACE_IN_WORD);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        empresa = from(Empresa.class).gimme(EMAIL_WITHOUT_ARROBA);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        empresa = from(Empresa.class).gimme(EMAIL_WITHOUT_PONTO_COM);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        empresa = from(Empresa.class).gimme(EMAIL_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(empresa, EMAIL_EMPRESA_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_add_telefones_na_lista_telefoness() {
        empresa.setTelefones(telefones);
        assertTrue(empresa.getTelefones().size() > 0);
    }

    @Test
    public void deve_add_produto_na_loja() {
        empresa.setProduto(produtos);
        assertTrue(empresa.getProduto().size() > 0);
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
        empresa = from(Empresa.class).gimme(CNPJ_NULL);
        assertNull(empresa.getCnpj());
    }

    /**
     * Nao deve aceitar cnpj com letras.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_letras() {
        empresa = from(Empresa.class).gimme(CNPJ_CONTAINS_WORD);
        assertTrue(returnAnnotationMsgError(empresa, CNPJ_EMPRESA_INVALIDO));
    }

    /**
     * Nao deve aceitar cnpj null com menos 14 numeros.
     */
    @Test
    public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
        empresa = from(Empresa.class).gimme(CNPJ_WRONG_SIZE);
        assertTrue(returnAnnotationMsgError(empresa, CNPJ_EMPRESA_INVALIDO));
    }

    @Test
    public void deve_add_funcionario_na_empresa() {
        empresa.setFuncionario(funcionarios);
        assertTrue(empresa.getFuncionario().size() > 0);
    }

    @Test
    public void deve_add_cliente_na_empresa() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(from(Cliente.class).gimme(VALID));
        empresa.setCliente(clientes);
        assertTrue(empresa.getCliente().size() > 0);
    }
    
    @Test
    public void nao_deve_aceitar_endereco_null() {
        assertNotNull(funcionarios.get(0).getEndereco());
    }

    @Test
    public void deve_alterar_data_criacao() {
        final DateTime dataCriacao = new DateTime();
        empresa.setDataCriacao(dataCriacao);
        assertSame(dataCriacao, empresa.getDataCriacao());
    }

    @Test
    public void deve_alterar_data_Alteracao() {
        final DateTime dataAlteracao = new DateTime();
        empresa.setDataAlteracao(dataAlteracao);
        assertSame(dataAlteracao, empresa.getDataAlteracao());
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Empresa.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    @Test()
    public void deve_ter_o_mesmo_hashCode_para_serem_iguais() {
        loja2 = from(Empresa.class).gimme(VALID);
        loja2.setCnpj(empresa.getCnpj());
        assertEquals(empresa.hashCode(), loja2.hashCode());
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_nome_no_toString() {
        assertTrue(empresa.toString().contains("nome"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_email_no_toString() {
        assertTrue(empresa.toString().contains("email"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_cnpj_no_toString() {
        assertTrue(empresa.toString().contains("cnpj"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_telefoness_no_toString() {
        assertTrue(empresa.toString().contains("telefones"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_enderecos_no_toString() {
        assertTrue(empresa.toString().contains("enderecos"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_produtos_no_toString() {
        assertTrue(empresa.toString().contains("produtos"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_funcionarios_no_toString() {
        assertTrue(empresa.toString().contains("funcionarios"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_clientes_no_toString() {
        assertTrue(empresa.toString().contains("clientes"));
    }
    
    /**
     * Mostrar dados empresa.
     */
    @AfterClass
    public static void mostrar_dados_empresa() {
        System.out.println(empresa);
    }

}
