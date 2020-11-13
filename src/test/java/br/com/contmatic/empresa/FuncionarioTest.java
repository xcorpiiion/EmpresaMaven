package br.com.contmatic.empresa;

import br.com.contmatic.telefone.Telefone;
import com.github.javafaker.Faker;
import org.joda.time.DateTime;
import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.enums.EnumCargo.REPOSITOR;
import static br.com.contmatic.enums.EnumCargo.RH;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    private static Faker faker;

    private static Funcionario funcionario;

    private Funcionario funcionario2;

    private Set<Telefone> telefones;

    @BeforeClass
    public static void addDadosIniciais() {
        faker = new Faker();
        loadTemplates("br.com.contmatic.fixture.factory");
    }

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
        funcionario = from(Funcionario.class).gimme(NOME_NULL);
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
        funcionario.setEnumCargo(REPOSITOR);
        assertSame(REPOSITOR, funcionario.getEnumCargo());
    }

    @Test
    public void deve_alterar_cargo_para_rh() {
        funcionario.setEnumCargo(RH);
        assertSame(RH, funcionario.getEnumCargo());
    }

    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        funcionario = from(Funcionario.class).gimme(DATA_NASCIMENTO_NULL);
        assertTrue(returnAnnotationMsgError(funcionario, DATA_NASCIMENTO_FUNCIONARIO_VAZIO));
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

    @AfterClass
    public static void mostrarDados() {
        System.out.println(funcionario);
    }

}
