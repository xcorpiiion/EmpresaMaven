package br.com.contmatic.empresa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.TipoTelefone;
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
        telefone = Fixture.from(Telefone.class).gimme("valid");
        telefone2 = Fixture.from(Telefone.class).gimme("valid");
    }
    
    /**
     * Deve ter mesmo telefone para ser igual.
     */
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_valido() {
        telefone = Fixture.from(Telefone.class).gimme("valid");
        assertTrue(telefone.getPhones().matches(Constante.PHONE_VALIDATION));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_seja_null() {
        telefone = Fixture.from(Telefone.class).gimme("phoneNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_vazio() {
        telefone = Fixture.from(Telefone.class).gimme("phoneEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_tenha_apenas_espacos_em_branco() {
        telefone = Fixture.from(Telefone.class).gimme("phoneBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_com_tamanho_invalido() {
        telefone = Fixture.from(Telefone.class).gimme("phoneInvalidSize");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_telefone_esteja_com_ddd_errado() {
        telefone = Fixture.from(Telefone.class).gimme("phoneDDDInvalid");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_celular_esteja_com_ddd_errado() {
        telefone = Fixture.from(Telefone.class).gimme("phoneDDDInvalid");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
        telefone = Fixture.from(Telefone.class).gimme("tipoTelefoneNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Constante.VALOR_ESTA_NULLO));
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
        telefone2.setPhones(telefone.getPhones());
        assertTrue(telefone2.hashCode() == telefone.hashCode());
    }
    
    @Test
    public void deve_retornar_false_caso_hashCode_sejam_diferentes() {
        telefone.setPhones(GeradorTelefone.geradorCellPhone());
        assertFalse(telefone2.hashCode() == telefone.hashCode());
    }
    
    /**
     * Deve ter mesmo telefone para ser igua.
     */
    @Test
    public void deve_retornar_true_caso_equals_sejam_iguais() {
        telefone2.setPhones(telefone.getPhones());
        assertTrue(telefone2.equals(telefone));
    }
    
    @Test
    public void deve_retornar_true_caso_sejam_mesmo_objeto() {
        assertTrue(telefone2.equals(telefone2));
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_null() {
        telefone2.setPhones(telefone.getPhones());
        assertFalse(telefone2.equals(null));
    }
    
    @Test
    public void deve_retornar_false_caso_compare_com_object() {
        telefone2.setPhones(telefone.getPhones());
        assertFalse(telefone2.equals(new Object()));
    }

    /**
     * Mostra mensagem.
     */
    @AfterClass
    public static void mostraMensagem() {
        System.out.println(telefone);
    }
    
}
