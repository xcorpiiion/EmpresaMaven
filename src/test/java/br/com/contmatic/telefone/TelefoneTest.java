package br.com.contmatic.telefone;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.javafaker.Faker;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.telefone.DddBrasil.ARACAJU;
import static br.com.contmatic.telefone.DddBrasil.SAO_JOSE_DOS_CAMPOS;
import static br.com.contmatic.telefone.TipoTelefone.FIXO;
import static br.com.contmatic.telefone.TipoTelefone.MOVEL;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.*;

/**
 * The Class TelefoneTest.
 */
public class TelefoneTest {
    
    /** The telefone. */
    private static Telefone telefone1;
    
    /** The telefone 2. */
    private static Telefone telefone12;
    
    
    /**
     * Add dados telefone.
     */
    @BeforeClass
    public static void addDadosTelefone() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone1 = from(Telefone.class).gimme(VALID);
        telefone12 = from(Telefone.class).gimme(VALID);
    }
    
    /**
     * Deve ter mesmo telefone para ser igual.
     */
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_valido() {
        telefone1 = from(Telefone.class).gimme(VALID);
        assertTrue(telefone1.getPhone().matches(PHONE_VALIDATION));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_null() {
        telefone1 = from(Telefone.class).gimme(PHONE_NULL);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_vazio() {
        telefone1 = from(Telefone.class).gimme(PHONE_EMPTY);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_tenha_apenas_espacos_em_branco() {
        telefone1 = from(Telefone.class).gimme(PHONE_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_com_tamanho_invalido() {
        telefone1 = from(Telefone.class).gimme(PHONE_INVALID_SIZE);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_ddd_seja_null() {
        telefone1 = from(Telefone.class).gimme(DDD_NULL);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_DDD_VAZIO));
    }
    
    @Test
    public void deve_possuir_o_mesmo_ddd() {
        telefone12.setDddTelefone(telefone1.getDddTelefone());
        assertEquals(telefone12.getDddTelefone().getDdd(), telefone1.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_false_caso_ddd_seja_diferente() {
        telefone12.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        telefone1.setDddTelefone(ARACAJU);
        assertFalse(telefone1.getDddTelefone().getDdd() == telefone12.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_true_caso_sigla_sejam_iguais() {
        telefone12.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        telefone1.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        assertTrue(telefone1.getDddTelefone().getSigla() == telefone12.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_false_caso_sigla_seja_diferente() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone12 = from(Telefone.class).gimme(VALID);
        assertFalse(telefone1.getDddTelefone().getSigla() == telefone12.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
        telefone1 = from(Telefone.class).gimme(TIPO_TELEFONE_NULL);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_TIPO_TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_fixo() {
        telefone1.setTipoTelefone(FIXO);
        assertTrue(telefone1.getTipoTelefone() == FIXO);
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_movel() {
        telefone1.setTipoTelefone(MOVEL);
        assertTrue(telefone1.getTipoTelefone() == MOVEL);
    }
    
    @Test
    public void deve_ter_mesmo_hashcode_para_ser_igual() {
        telefone12.setPhone(telefone1.getPhone());
        telefone12.setDddTelefone(telefone1.getDddTelefone());
        assertTrue(telefone12.hashCode() == telefone1.hashCode());
    }
    
    @Test
    public void deve_retornar_false_caso_hashCode_sejam_diferentes() {
        telefone1.setPhone(String.valueOf(new Faker().number().numberBetween(111111111, 988888888)));
        assertFalse(telefone12.hashCode() == telefone1.hashCode());
    }
    
    /**
     * Deve ter mesmo telefone para ser igua.
     */
    @Test
    public void deve_retornar_true_caso_equals_sejam_iguais() {
        telefone12.setPhone(telefone1.getPhone());
        telefone12.setDddTelefone(telefone1.getDddTelefone());
        assertTrue(telefone12.equals(telefone1));
    }
    
    @Test
    public void deve_retornar_true_caso_sejam_mesmo_objeto() {
        assertTrue(telefone12.equals(telefone12));
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_null() {
        telefone12.setPhone(telefone1.getPhone());
        assertFalse(telefone12.equals(null));
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_object() {
        telefone12.setPhone(telefone1.getPhone());
        assertFalse(telefone12.equals(new Object()));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_phone_no_toString() {
        assertTrue(telefone1.toString().contains("phone"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_tipoTelefone_no_toString() {
        assertTrue(telefone1.toString().contains("tipoTelefone"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_dddTelefone_no_toString() {
        assertTrue(telefone1.toString().contains("dddTelefone"));
    }
    
    /**
     * Mostra mensagem.
     */
    @AfterClass
    public static void mostraMensagem() {
        System.out.println(telefone1);
    }
    
}
