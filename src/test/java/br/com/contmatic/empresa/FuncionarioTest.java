package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.CARGO_NULL;
import static br.com.contmatic.constantes.Constante.DATA_NASCIMENTO_NULL;
import static br.com.contmatic.constantes.Constante.EMAIL_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.EMAIL_EMPTY;
import static br.com.contmatic.constantes.Constante.EMAIL_LESS_10_CARACTERES;
import static br.com.contmatic.constantes.Constante.EMAIL_NULL;
import static br.com.contmatic.constantes.Constante.EMAIL_WITHOUT_ARROBA;
import static br.com.contmatic.constantes.Constante.EMAIL_WITHOUT_PONTO_COM;
import static br.com.contmatic.constantes.Constante.EMAIL_WITH_BLANK_SPACE_IN_WORD;
import static br.com.contmatic.constantes.Constante.EMAIL_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.NOME_EMPTY;
import static br.com.contmatic.constantes.Constante.NOME_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_NULL;
import static br.com.contmatic.constantes.Constante.SALARIO_LESS_1;
import static br.com.contmatic.constantes.Constante.TIPO_CONTRATO_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.contmatic.constantes.Mensagem.CARGO_FUNCIONARIO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.DATA_NASCIMENTO_FUNCIONARIO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.EMAIL_FUNCIONARIO_CARACTERE_INVALIDO;
import static br.com.contmatic.constantes.Mensagem.EMAIL_FUNCIONARIO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.EMAIL_FUNCIONARIO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.NOME_FUNCIONARIO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.NOME_FUNCIONARIO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.SALARIO_FUNCIONARIO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.TIPO_CONTRATO_FUNCIONARIO_VAZIO;
import static br.com.contmatic.empresa.Cargo.REPOSITOR;
import static br.com.contmatic.empresa.Cargo.RH;
import static br.com.contmatic.telefone.TipoContrato.CLT;
import static br.com.contmatic.telefone.TipoContrato.PJ;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.contmatic.telefone.Telefone;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    private static Faker faker;

    /** The funcionarios. */
    private static Funcionario funcionario;
    
    private Funcionario funcionario2;

    private Set<Telefone> telefones;

    /**
     * Add dados iniciais.
     */
    @BeforeClass
    public static void addDadosIniciais() {
        faker = new Faker();
        loadTemplates("br.com.contmatic.fixture.factory");
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionario = (from(Funcionario.class).gimme(VALID));
        funcionario2 = (from(Funcionario.class).gimme(VALID));
    }
    
    @Before
    public void addDadosTelefone() {
        telefones = new HashSet<>();
        telefones.add(from(Telefone.class).gimme(VALID));
        loadTemplates("br.com.contmatic.fixture.factory");
        telefones.add(from(Telefone.class).gimme(VALID));
    }

    @Test
    public void deve_mudar_nome_funcionario() {
        final String nome = faker.name().firstName();
        funcionario.setNome(nome);
        assertEquals(nome, funcionario.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        funcionario= from(Funcionario.class).gimme(NOME_NULL);
        assertTrue(returnAnnotationMsgError(funcionario, NOME_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        funcionario = from(Funcionario.class).gimme(NOME_EMPTY);
        assertTrue(returnAnnotationMsgError(funcionario, NOME_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        funcionario = from(Funcionario.class).gimme(NOME_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(funcionario, NOME_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        funcionario = from(Funcionario.class).gimme(NOME_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(funcionario, NOME_FUNCIONARIO_TAMANHO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        funcionario = from(Funcionario.class).gimme(EMAIL_NULL);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        funcionario = from(Funcionario.class).gimme(EMAIL_EMPTY);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        funcionario = from(Funcionario.class).gimme(EMAIL_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_alterar_email() {
        final String email = faker.internet().emailAddress();
        funcionario.setEmail(email);
        assertEquals(email, funcionario.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        funcionario = from(Funcionario.class).gimme(EMAIL_LESS_10_CARACTERES);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_TAMANHO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        funcionario = from(Funcionario.class).gimme(EMAIL_WITH_BLANK_SPACE_IN_WORD);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        funcionario = from(Funcionario.class).gimme(EMAIL_WITHOUT_ARROBA);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        funcionario = from(Funcionario.class).gimme(EMAIL_WITHOUT_PONTO_COM);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        funcionario = from(Funcionario.class).gimme(EMAIL_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(funcionario, EMAIL_FUNCIONARIO_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_se_cargo_for_null() {
        funcionario = from(Funcionario.class).gimme(CARGO_NULL);
        assertTrue(returnAnnotationMsgError(funcionario, CARGO_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_alterar_cargo_para_repositor() {
        funcionario.setCargo(REPOSITOR);
        assertSame(REPOSITOR, funcionario.getCargo());
    }
    
    @Test
    public void deve_alterar_cargo_para_rh() {
        funcionario.setCargo(RH);
        assertSame(RH, funcionario.getCargo());
    }
    
    @Test
    public void deve_retornar_true_se_tipoContrato_for_null() {
        funcionario = from(Funcionario.class).gimme(TIPO_CONTRATO_NULL);
        assertTrue(returnAnnotationMsgError(funcionario, TIPO_CONTRATO_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_clt() {
        funcionario.setTipoContrato(CLT);
        assertSame(CLT, funcionario.getTipoContrato());
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_pj() {
        funcionario.setTipoContrato(PJ);
        assertSame(PJ, funcionario.getTipoContrato());
    }
    
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        funcionario = from(Funcionario.class).gimme(DATA_NASCIMENTO_NULL);
        assertTrue(returnAnnotationMsgError(funcionario, DATA_NASCIMENTO_FUNCIONARIO_VAZIO));
    }
    
    @Test
    public void nao_deve_aceita_data_entrada_null() {
    	funcionario.setDataEntrada(new DateTime());
    	assertNotNull(funcionario.getDataEntrada());
    }
    
    @Test
    public void nao_deve_aceitar_data_entrada_maior_do_que_data_atual() {
		funcionario.setDataEntrada(new DateTime(2020, 01, 01, 0, 0));
		assertTrue(new DateTime().isAfter(funcionario.getDataEntrada().getMillis()));
    }
    
    @Test
    public void nao_deve_aceitar_data_saida_maior_do_que_data_atual() {
		funcionario.setDataSaida(new DateTime(2025, 01, 01, 0, 0));
		assertTrue(funcionario.getDataSaida().isAfter(new DateTime().getMillis()));
    }
    
    @Test
    public void nao_deve_aceitar_data_nascimento_maior_do_que_data_atual() {
    	funcionario.setDataNascimento(new DateTime(2020, 01, 01, 0, 0));
    	assertTrue(new DateTime().isAfter(funcionario.getDataNascimento().getMillis()));
    }
    
    @Test
    public void nao_deve_aceitar_data_nascimento_menor_do_que_1920() {
    	final DateTime dataNascimento = new DateTime(1919, 01, 01, 0, 0);
    	funcionario.setDataNascimento(new DateTime());
    	assertTrue(dataNascimento.isBefore(funcionario.getDataNascimento().getMillis()));
    }
    
    @Test
    public void deve_alterar_dataNascimento() {
        final DateTime dataNascimento = new DateTime();
		funcionario.setDataNascimento(dataNascimento);
        assertEquals(dataNascimento, funcionario.getDataNascimento());
    }
    
    @Test
    public void deve_add_telefone_na_lista_telefones() {
        funcionario.setTelefones(telefones);
        assertTrue(funcionario.getTelefones().size() > 0);
    }
    
    @Test
    public void deve_mudar_endereco() {
        funcionario.setEndereco(funcionario2.getEndereco());
        assertEquals(funcionario2.getEndereco(), funcionario.getEndereco());   
    }
    
    /**
     * Deve ter salario maior do que zero.
     */
    @Test
    public void deve_ter_salario_maior_do_que_zero() {
        funcionario = (from(Funcionario.class).gimme(SALARIO_LESS_1));
        funcionario2.setSalario(funcionario.getSalario());
        assertTrue(returnAnnotationMsgError(funcionario2, SALARIO_FUNCIONARIO_TAMANHO));
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Funcionario.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    /**
     * Mostrar dados.
     */
    @AfterClass
    public static void mostrarDados() {
        System.out.println(funcionario);
    }

}
