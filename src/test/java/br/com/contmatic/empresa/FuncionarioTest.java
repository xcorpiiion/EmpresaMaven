package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.empresa.Cargo.REPOSITOR;
import static br.com.contmatic.empresa.Cargo.RH;
import static br.com.contmatic.telefone.TipoContrato.CLT;
import static br.com.contmatic.telefone.TipoContrato.PJ;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

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
import br.com.contmatic.telefone.TipoContrato;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(NAME_ASCENDING)
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
        loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(from(Produto.class).gimme("valid"));
        loja = from(Empresa.class).gimme("valid");
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionario = (from(Funcionario.class).gimme("valid"));
        funcionario2 = (from(Funcionario.class).gimme("valid"));
    }
    
    @Before
    public void addDadosTelefone() {
        telefones = new HashSet<>();
        telefones.add(from(Telefone.class).gimme("valid"));
        loadTemplates("br.com.contmatic.fixture.factory");
        telefones.add(from(Telefone.class).gimme("valid"));
    }

    @Test
    public void deve_mudar_nome_funcionario() {
        funcionario.setNome("kratos");
        assertEquals("kratos", funcionario.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        funcionario= from(Funcionario.class).gimme("nomeNull");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        funcionario = from(Funcionario.class).gimme("nomeEmpty");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        funcionario = from(Funcionario.class).gimme("nomeBlankSpace");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        funcionario = from(Funcionario.class).gimme("nomeLess3Caracter");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        funcionario = from(Funcionario.class).gimme("nomeGreaterCaracter");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        funcionario = from(Funcionario.class).gimme("nomeWithSpecialCaracter");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        funcionario = from(Funcionario.class).gimme("emailNull");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        funcionario = from(Funcionario.class).gimme("emailEmpty");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        funcionario = from(Funcionario.class).gimme("emailBlankSpace");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_alterar_email() {
        funcionario.setEmail("kratos@gmail.com");
        assertEquals("kratos@gmail.com", funcionario.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        funcionario = from(Funcionario.class).gimme("emailLess10Caracteres");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_mais_100_caracteres() {
        funcionario = from(Funcionario.class).gimme("emailGreater100Caracteres");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        funcionario = from(Funcionario.class).gimme("emailWithBlankSpaceInWord");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
        funcionario = from(Funcionario.class).gimme("emailWithNumberAfterArroba");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        funcionario = from(Funcionario.class).gimme("emailWithoutArroba");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        funcionario = from(Funcionario.class).gimme("emailWithoutPontoCom");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_sem_com() {
        funcionario = from(Funcionario.class).gimme("emailWithoutCom");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        funcionario = from(Funcionario.class).gimme("emailWithSpecialCaracter");
        assertTrue(returnAnnotationMsgError(loja, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_se_cargo_for_null() {
        funcionario = from(Funcionario.class).gimme("cargoNull");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_cargo_para_repositor() {
        funcionario.setCargo(REPOSITOR);
        assertTrue(funcionario.getCargo() == REPOSITOR);
    }
    
    @Test
    public void deve_alterar_cargo_para_rh() {
        funcionario.setCargo(RH);
        assertTrue(funcionario.getCargo() == RH);
    }
    
    @Test
    public void deve_retornar_true_se_tipoContrato_for_null() {
        funcionario = from(Funcionario.class).gimme("tipoContratoNull");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_clt() {
        funcionario.setTipoContrato(CLT);
        assertTrue(funcionario.getTipoContrato() == CLT);
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_pj() {
        funcionario.setTipoContrato(PJ);
        assertTrue(funcionario.getTipoContrato() == PJ);
    }
    
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        funcionario = from(Funcionario.class).gimme("dataNascimentoNull");
        assertTrue(returnAnnotationMsgError(funcionario, VALOR_ESTA_NULLO));
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
        funcionario = (from(Funcionario.class).gimme("salarioLess1"));
        funcionario2.setSalario(funcionario.getSalario());
        assertTrue(returnAnnotationMsgError(funcionario2, PRECISA_SER_UM_VALOR_MAIOR));
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        funcionario = (from(Funcionario.class).gimme("valid"));
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
        funcionario = (from(Funcionario.class).gimme("valid"));
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
