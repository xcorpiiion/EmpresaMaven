package br.com.contmatic.empresa;

import br.com.contmatic.telefone.Telefone;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.services.utils.GeradorCpf.gerardorRandomCpf;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * The Class ClienteTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {
    
    /** The clientes. */
    private Cliente cliente;

    private Set<Telefone> telefone;

    /**
     * Add dados iniciais.
     */
    @BeforeClass
    public static void addDadosIniciais() {
        loadTemplates("br.com.contmatic.fixture.factory");
    }

    /**
     * Add dados cliente.
     */
    @Before
    public void addDadosCliente() {
        cliente = (from(Cliente.class).gimme(VALID));
        telefone = new HashSet<>();
        telefone.add(from(Telefone.class).gimme(VALID));
        cliente.setTelefones(telefone);
    }

    @Test
    public void deve_add_telefone_na_lista_telefones() {
        cliente.setTelefones(telefone);
        assertTrue(cliente.getTelefones().size() > 0);
    }

    /**
     * Nao deve aceitar nome vazio.
     */
    @Test
    public void deve_retornar_true_caso_nome_esteja_vazio() {
        Cliente clienteInvalid = from(Cliente.class).gimme(NOME_EMPTY);
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(returnAnnotationMsgError(cliente, NOME_CLIENTE_VAZIO));
    }

    /**
     * Nao deve aceitar nome com espaco em branco.
     */
    @Test
    public void deve_retornar_true_caso_nome_tenha_apenas_espacos_em_branco() {
        Cliente clienteInvalid = from(Cliente.class).gimme(NOME_BLANK_SPACE);
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(returnAnnotationMsgError(cliente, NOME_CLIENTE_VAZIO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        Cliente clienteInvalid = from(Cliente.class).gimme(NOME_NULL);
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(returnAnnotationMsgError(cliente, NOME_CLIENTE_VAZIO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        Cliente clienteInvalid = from(Cliente.class).gimme(NOME_WITH_SPECIAL_CARACTER);
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(returnAnnotationMsgError(cliente, NOME_CLIENTE_CARACTERE_INVALIDO));
    }

    /**
     * Nao deve aceitar email null.
     */
    @Test
    public void nao_deve_aceitar_email_null() {
        Cliente clienteInvalid = from(Cliente.class).gimme(EMAIL_NULL);
        cliente.setNome(clienteInvalid.getEmail());
        assertNotNull(cliente.getEmail());
    }

    /**
     * Nao deve aceitar email com espaco em branco.
     */
    @Test
    public void deve_retornar_true_caso_email_contenha_espaco_em_branco() {
        Cliente clienteInvalid = from(Cliente.class).gimme(EMAIL_BLANK_SPACE);
        cliente.setEmail(clienteInvalid.getEmail());
        assertTrue(returnAnnotationMsgError(cliente, EMAIL_CLIENTE_VAZIO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        cliente = from(Cliente.class).gimme(EMAIL_WITH_BLANK_SPACE_IN_WORD);
        assertTrue(returnAnnotationMsgError(cliente, EMAIL_CLIENTE_CARACTERE_INVALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        cliente = from(Cliente.class).gimme(EMAIL_WITHOUT_ARROBA);
        assertTrue(returnAnnotationMsgError(cliente, EMAIL_CLIENTE_CARACTERE_INVALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        cliente = from(Cliente.class).gimme(EMAIL_WITHOUT_PONTO_COM);
        assertTrue(returnAnnotationMsgError(cliente, EMAIL_CLIENTE_CARACTERE_INVALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        cliente = from(Cliente.class).gimme(EMAIL_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(cliente, EMAIL_CLIENTE_CARACTERE_INVALIDO));
    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void nao_deve_aceitar_endereco_null() {
        Cliente clienteInvalid = from(Cliente.class).gimme(ENDERECO_NULL);
        cliente.setEndereco(clienteInvalid.getEndereco());
        assertNull(cliente.getEndereco());
    }

    /**
     * Data nascimento nao deve ser null exception.
     *
     */
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        Cliente clienteValid = from(Cliente.class).gimme(DATA_NASCIMENTO_NULL);
        assertTrue(returnAnnotationMsgError(clienteValid, DATA_NASCIMENTO_CLIENTE_VAZIO));
    }

    @Test
    public void deve_alterar_dataNascimento() {
        final DateTime dataNascimento = new DateTime();
        cliente.setDataNascimento(dataNascimento);
        assertEquals(dataNascimento, cliente.getDataNascimento());
    }

    @Test
    public void deve_alterar_data_cadastro() {
        final DateTime dataCadastro = new DateTime();
        cliente.setDataCadastro(dataCadastro);
        assertSame(dataCadastro, cliente.getDataCadastro());
    }

    /**
     * Deve add dinheiro carteira.
     */
    @Test
    public void deve_add_dinheiro_carteira() {
        BigDecimal dinheiro = new BigDecimal(2500);
        BigDecimal dinheiroAnterior = cliente.getDinheiroCarteira();
        cliente.setDinheiroCarteira(cliente.getDinheiroCarteira().add(dinheiro));
        assertTrue(cliente.getDinheiroCarteira().compareTo(dinheiroAnterior) > 0);
    }

    @Test
    public void deve_retornar_true_caso_cpf_nao_seja_valido() {
        cliente.setCpf(String.valueOf(new Random().nextInt(888888888) + 111111111));
        assertTrue(returnAnnotationMsgError(cliente, CPF_CLIENTE_INVALIDO));
    }

    @Test
    public void deve_retornar_cpf_valido() {
        final String cpf = gerardorRandomCpf();
        cliente.setCpf(cpf);
        assertSame(cpf, cliente.getCpf());
    }


    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_nome_no_toString() {
        assertTrue(cliente.toString().contains("nome"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_email_no_toString() {
        assertTrue(cliente.toString().contains("email"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_dataNascimento_no_toString() {
        assertTrue(cliente.toString().contains("dataNascimento"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_dinheiroCarteira_no_toString() {
        assertTrue(cliente.toString().contains("dinheiroCarteira"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_endereco_no_toString() {
        assertTrue(cliente.toString().contains("endereco"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_telefones_no_toString() {
        assertTrue(cliente.toString().contains("telefones"));
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Cliente.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

}
