package br.com.contmatic.telefone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.fixture.factory.GeradorTelefone;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TipoTelefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class TelefoneTest.
 */
public class TelefoneTest {
    
    /** The telefone. */
    private static Telefone telefone;
    
    /** The telefone 2. */
    private static Telefone telefone2;
    
    
    /**
     * Add dados telefone.
     */
    @BeforeClass
    public static void addDadosTelefone() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone = Fixture.from(Telefone.class).gimme("valid");
        telefone2 = Fixture.from(Telefone.class).gimme("valid");
    }
    
    /**
     * Deve ter mesmo telefone para ser igual.
     */
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_valido() {
        telefone = Fixture.from(Telefone.class).gimme("valid");
        assertTrue(telefone.getPhone().matches(Constante.PHONE_VALIDATION));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_null() {
        telefone = Fixture.from(Telefone.class).gimme("phoneNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_vazio() {
        telefone = Fixture.from(Telefone.class).gimme("phoneEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_tenha_apenas_espacos_em_branco() {
        telefone = Fixture.from(Telefone.class).gimme("phoneBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_com_tamanho_invalido() {
        telefone = Fixture.from(Telefone.class).gimme("phoneInvalidSize");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_ddd_seja_null() {
        telefone = Fixture.from(Telefone.class).gimme("dddNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_possuir_o_mesmo_ddd() {
        telefone2.setDddTelefone(telefone.getDddTelefone());
        assertEquals(telefone2.getDddTelefone().getDdd(), telefone.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_false_caso_ddd_seja_diferente() {
        telefone2.setDddTelefone(DddBrasil.SAO_JOSE_DOS_CAMPOS);
        telefone.setDddTelefone(DddBrasil.ARACAJU);
        assertFalse(telefone.getDddTelefone().getDdd() == telefone2.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_true_caso_sigla_sejam_iguais() {
        telefone2.setDddTelefone(DddBrasil.SAO_JOSE_DOS_CAMPOS);
        telefone.setDddTelefone(DddBrasil.SAO_JOSE_DOS_CAMPOS);
        assertTrue(telefone.getDddTelefone().getSigla() == telefone2.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_false_caso_sigla_seja_diferente() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone2 = Fixture.from(Telefone.class).gimme("valid");
        assertFalse(telefone.getDddTelefone().getSigla() == telefone2.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
        telefone = Fixture.from(Telefone.class).gimme("tipoTelefoneNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_fixo() {
        telefone.setTipoTelefone(TipoTelefone.FIXO);
        assertTrue(telefone.getTipoTelefone() == TipoTelefone.FIXO);
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_movel() {
        telefone.setTipoTelefone(TipoTelefone.MOVEL);
        assertTrue(telefone.getTipoTelefone() == TipoTelefone.MOVEL);
    }
    
    @Test
    public void deve_ter_mesmo_hashcode_para_ser_igual() {
        telefone2.setPhone(telefone.getPhone());
        telefone2.setDddTelefone(telefone.getDddTelefone());
        assertTrue(telefone2.hashCode() == telefone.hashCode());
    }
    
    @Test
    public void deve_retornar_false_caso_hashCode_sejam_diferentes() {
        telefone.setPhone(GeradorTelefone.geradorCellPhone());
        assertFalse(telefone2.hashCode() == telefone.hashCode());
    }
    
    /**
     * Deve ter mesmo telefone para ser igua.
     */
    @Test
    public void deve_retornar_true_caso_equals_sejam_iguais() {
        telefone2.setPhone(telefone.getPhone());
        telefone2.setDddTelefone(telefone.getDddTelefone());
        assertTrue(telefone2.equals(telefone));
    }
    
    @Test
    public void deve_retornar_true_caso_sejam_mesmo_objeto() {
        assertTrue(telefone2.equals(telefone2));
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_null() {
        telefone2.setPhone(telefone.getPhone());
        assertFalse(telefone2.equals(null));
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_object() {
        telefone2.setPhone(telefone.getPhone());
        assertFalse(telefone2.equals(new Object()));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_phone_no_toString() {
        assertTrue(telefone.toString().contains("phone"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_tipoTelefone_no_toString() {
        assertTrue(telefone.toString().contains("tipoTelefone"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_dddTelefone_no_toString() {
        assertTrue(telefone.toString().contains("dddTelefone"));
    }
    
    /**
     * Mostra mensagem.
     */
    @AfterClass
    public static void mostraMensagem() {
        System.out.println(telefone);
    }
    
}
