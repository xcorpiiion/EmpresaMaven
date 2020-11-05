package br.com.contmatic.telefone;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.telefone.DddBrasil.ARACAJU;
import static br.com.contmatic.telefone.DddBrasil.SAO_JOSE_DOS_CAMPOS;
import static br.com.contmatic.telefone.TipoTelefone.FIXO;
import static br.com.contmatic.telefone.TipoTelefone.MOVEL;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.fixture.factory.GeradorTelefone;
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
        telefone = from(Telefone.class).gimme(VALID);
        telefone2 = from(Telefone.class).gimme(VALID);
    }
    
    /**
     * Deve ter mesmo telefone para ser igual.
     */
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_valido() {
        telefone = from(Telefone.class).gimme(VALID);
        assertTrue(telefone.getPhone().matches(PHONE_VALIDATION));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_null() {
        telefone = from(Telefone.class).gimme(PHONE_NULL);
        assertTrue(returnAnnotationMsgError(telefone, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_vazio() {
        telefone = from(Telefone.class).gimme(PHONE_EMPTY);
        assertTrue(returnAnnotationMsgError(telefone, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_tenha_apenas_espacos_em_branco() {
        telefone = from(Telefone.class).gimme(PHONE_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(telefone, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_com_tamanho_invalido() {
        telefone = from(Telefone.class).gimme(PHONE_INVALID_SIZE);
        assertTrue(returnAnnotationMsgError(telefone, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_ddd_seja_null() {
        telefone = from(Telefone.class).gimme(DDD_NULL);
        assertTrue(returnAnnotationMsgError(telefone, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_possuir_o_mesmo_ddd() {
        telefone2.setDddTelefone(telefone.getDddTelefone());
        assertEquals(telefone2.getDddTelefone().getDdd(), telefone.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_false_caso_ddd_seja_diferente() {
        telefone2.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        telefone.setDddTelefone(ARACAJU);
        assertFalse(telefone.getDddTelefone().getDdd() == telefone2.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_true_caso_sigla_sejam_iguais() {
        telefone2.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        telefone.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        assertTrue(telefone.getDddTelefone().getSigla() == telefone2.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_false_caso_sigla_seja_diferente() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone2 = from(Telefone.class).gimme(VALID);
        assertFalse(telefone.getDddTelefone().getSigla() == telefone2.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
        telefone = from(Telefone.class).gimme(TIPO_TELEFONE_NULL);
        assertTrue(returnAnnotationMsgError(telefone, VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_fixo() {
        telefone.setTipoTelefone(FIXO);
        assertTrue(telefone.getTipoTelefone() == FIXO);
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_movel() {
        telefone.setTipoTelefone(MOVEL);
        assertTrue(telefone.getTipoTelefone() == MOVEL);
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
