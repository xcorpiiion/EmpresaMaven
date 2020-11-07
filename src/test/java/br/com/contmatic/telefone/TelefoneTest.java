package br.com.contmatic.telefone;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
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
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
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
        assertNotSame(telefone1.getDddTelefone().getDdd(), telefone12.getDddTelefone().getDdd());
    }
    
    @Test
    public void deve_retornar_true_caso_sigla_sejam_iguais() {
        telefone12.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        telefone1.setDddTelefone(SAO_JOSE_DOS_CAMPOS);
        assertSame(telefone1.getDddTelefone().getSigla(), telefone12.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_false_caso_sigla_seja_diferente() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefone12 = from(Telefone.class).gimme(VALID);
        assertNotSame(telefone1.getDddTelefone().getSigla(), telefone12.getDddTelefone().getSigla());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
        telefone1 = from(Telefone.class).gimme(TIPO_TELEFONE_NULL);
        assertTrue(returnAnnotationMsgError(telefone1, TELEFONE_TIPO_TELEFONE_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_fixo() {
        telefone1.setTipoTelefone(FIXO);
        assertSame(FIXO, telefone1.getTipoTelefone());
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_seja_movel() {
        telefone1.setTipoTelefone(MOVEL);
        assertSame(MOVEL, telefone1.getTipoTelefone());
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Telefone.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
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
