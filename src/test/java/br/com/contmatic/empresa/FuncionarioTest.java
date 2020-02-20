package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

    /** The produtos. */
    private static List<Produto> produtos;

    /** The funcionarios. */
    private static Funcionario funcionario;
    
    private Funcionario funcionario2;

    /** The loja. */
    private static Empresa loja;
    
    private Set<Telefone> telefones;

    /**
     * Add dados iniciais.
     */
    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        loja = Fixture.from(Empresa.class).gimme("valid");
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionario = (Fixture.from(Funcionario.class).gimme("valid"));
        funcionario2 = (Fixture.from(Funcionario.class).gimme("valid"));
    }
    
    @Before
    public void addDadosTelefone() {
        telefones = new HashSet<>();
        telefones.add(Fixture.from(Telefone.class).gimme("valid"));
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefones.add(Fixture.from(Telefone.class).gimme("valid"));
    }

    @Test
    public void deve_mudar_nome_funcionario() {
        funcionario.setNome("kratos");
        assertEquals("kratos", funcionario.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        funcionario= Fixture.from(Funcionario.class).gimme("nomeNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        funcionario = Fixture.from(Funcionario.class).gimme("nomeEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        funcionario = Fixture.from(Funcionario.class).gimme("nomeBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        funcionario = Fixture.from(Funcionario.class).gimme("nomeLess3Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        funcionario = Fixture.from(Funcionario.class).gimme("nomeGreaterCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        funcionario = Fixture.from(Funcionario.class).gimme("nomeWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_alterar_email() {
        funcionario.setEmail("kratos@gmail.com");
        assertEquals("kratos@gmail.com", funcionario.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailLess10Caracteres");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_mais_100_caracteres() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailGreater100Caracteres");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailWithBlankSpaceInWord");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailWithNumberAfterArroba");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailWithoutArroba");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailWithoutPontoCom");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_com() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailWithoutCom");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        funcionario = Fixture.from(Funcionario.class).gimme("emailWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_se_cargo_for_null() {
        funcionario = Fixture.from(Funcionario.class).gimme("cargoNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_cargo_para_repositor() {
        funcionario.setCargo(Cargo.REPOSITOR);
        assertTrue(funcionario.getCargo() == Cargo.REPOSITOR);
    }
    
    @Test
    public void deve_alterar_cargo_para_rh() {
        funcionario.setCargo(Cargo.RH);
        assertTrue(funcionario.getCargo() == Cargo.RH);
    }
    
    @Test
    public void deve_retornar_true_se_tipoContrato_for_null() {
        funcionario = Fixture.from(Funcionario.class).gimme("tipoContratoNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_clt() {
        funcionario.setTipoContrato(TipoContrato.CLT);
        assertTrue(funcionario.getTipoContrato() == TipoContrato.CLT);
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_pj() {
        funcionario.setTipoContrato(TipoContrato.PJ);
        assertTrue(funcionario.getTipoContrato() == TipoContrato.PJ);
    }
    
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        funcionario = Fixture.from(Funcionario.class).gimme("dataNascimentoNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_dataNascimento() {
        funcionario.setDataNascimento(new DateTime());
        assertEquals(new DateTime(), funcionario.getDataNascimento());
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
        funcionario = (Fixture.from(Funcionario.class).gimme("salarioLess1"));
        funcionario2.setSalario(funcionario.getSalario());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario2, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        funcionario = (Fixture.from(Funcionario.class).gimme("valid"));
        funcionario2.setCpf(funcionario.getCpf());
        assertTrue("Os Funcionario são iguais", funcionario.equals(funcionario2));
    }
    
    @Test()
    public void deve_retornar_true_quando_compara_com_mesmo_objeto() {
        assertTrue(funcionario.equals(funcionario));
    }

    @Test()
    public void deve_retornar_false_quando_compara_com_classe_diferente() {
        assertFalse(funcionario.equals(new Object()));
    }
    /**
     * Deve ter hash code iguais para serem funcionarios iguais.
     */
    @Test()
    public void deve_ter_hashCode_iguais_para_serem_funcionarios_iguais() {
        funcionario = (Fixture.from(Funcionario.class).gimme("valid"));
        funcionario2.setCpf(funcionario.getCpf());
        assertTrue(funcionario.hashCode() == funcionario2.hashCode());
    }

    /**
     * Nao deve ter equals null para comparar funcionarios.
     */
    @Test()
    public void nao_deve_ter_equals_null_para_comparar_funcionarios() {
        assertFalse("Os funcionarios são igauis", funcionario.equals(null));
    }

    /**
     * Mostrar dados.
     */
    @AfterClass
    public static void mostrarDados() {
        System.out.println(funcionario);
    }

}
