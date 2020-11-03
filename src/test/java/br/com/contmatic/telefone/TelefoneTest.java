package br.com.contmatic.telefone;

import static br.com.contmatic.constantes.Constante.DDD_NULL;
import static br.com.contmatic.constantes.Constante.PHONE_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.PHONE_EMPTY;
import static br.com.contmatic.constantes.Constante.PHONE_INVALID_SIZE;
import static br.com.contmatic.constantes.Constante.PHONE_NULL;
import static br.com.contmatic.constantes.Constante.PHONE_VALIDATION;
import static br.com.contmatic.constantes.Constante.TIPO_TELEFONE_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.contmatic.constantes.Mensagem.TELEFONE_CARACTERE_INVALIDO;
import static br.com.contmatic.constantes.Mensagem.TELEFONE_DDD_VAZIO;
import static br.com.contmatic.constantes.Mensagem.TELEFONE_TIPO_TELEFONE_VAZIO;
import static br.com.contmatic.constantes.Mensagem.TELEFONE_VAZIO;
import static br.com.contmatic.telefone.DddBrasil.ARACAJU;
import static br.com.contmatic.telefone.DddBrasil.SAO_JOSE_DOS_CAMPOS;
import static br.com.contmatic.telefone.TipoTelefone.FIXO;
import static br.com.contmatic.telefone.TipoTelefone.MOVEL;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
        assertTrue(returnAnnotationMsgError(telefone, TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_vazio() {
        telefone = from(Telefone.class).gimme(PHONE_EMPTY);
        assertTrue(returnAnnotationMsgError(telefone, TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_tenha_apenas_espacos_em_branco() {
        telefone = from(Telefone.class).gimme(PHONE_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(telefone, TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_com_tamanho_invalido() {
        telefone = from(Telefone.class).gimme(PHONE_INVALID_SIZE);
        assertTrue(returnAnnotationMsgError(telefone, TELEFONE_CARACTERE_INVALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_ddd_seja_null() {
        telefone = from(Telefone.class).gimme(DDD_NULL);
        assertTrue(returnAnnotationMsgError(telefone, TELEFONE_DDD_VAZIO));
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
        assertNotSame(telefone.getDddTelefone().getDdd(), telefone2.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_true_caso_sigla_sejam_iguais() {
        telefone2.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        telefone.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        assertSame(telefone.getDddTelefone().getSigla(), telefone2.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_false_caso_sigla_seja_diferente() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone2 = from(Telefone.class).gimme(VALID);
        assertNotSame(telefone.getDddTelefone().getSigla(), telefone2.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
        telefone = from(Telefone.class).gimme(TIPO_TELEFONE_NULL);
        assertTrue(returnAnnotationMsgError(telefone, TELEFONE_TIPO_TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_fixo() {
        telefone.setTipoTelefone(FIXO);
        assertSame(FIXO, telefone.getTipoTelefone());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_movel() {
        telefone.setTipoTelefone(MOVEL);
        assertSame(MOVEL, telefone.getTipoTelefone());
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Telefone.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_object() {
        telefone2.setPhone(telefone.getPhone());
        assertNotEquals(telefone2, new Object());
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
