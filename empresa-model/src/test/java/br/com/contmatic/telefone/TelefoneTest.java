package br.com.contmatic.telefone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.constantes.easyrandom.EasyRandomTelefone;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteTelefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

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
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO);
		telefone2 = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO);
	}

	/**
	 * Deve ter mesmo telefone para ser igual.
	 */

	@Test
	public void deve_retornar_true_caso_telefone_seja_valido() {
		Faker faker = new Faker();
		System.out.println(faker.address().firstName());
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO);
		assertTrue(telefone.getPhone().matches(Constante.PHONE_VALIDATION));
	}

	@Test
	public void deve_retornar_true_caso_telefone_seja_null() {
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.PHONE_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_retornar_true_caso_telefone_esteja_vazio() {
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.PHONE_EMPTY);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_VAZIO));
	}

	@Test
	public void deve_retornar_true_caso_telefone_tenha_apenas_espacos_em_branco() {
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.PHONE_BLANK_SPACE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_ESTA_VAZIO));
	}

	@Test
	public void deve_retornar_true_caso_telefone_esteja_com_tamanho_invalido() {
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.PHONE_INVALID_SIZE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(telefone, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_ddd_seja_null() {
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.DDD_NULL);
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
		assertSame(telefone.getDddTelefone().getSigla(), telefone2.getDddTelefone().getSigla());
	}

	@Test
	public void deve_retornar_false_caso_sigla_seja_diferente() {
		telefone2 = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO);
		assertFalse(telefone.getDddTelefone().getSigla() == telefone2.getDddTelefone().getSigla());
	}

	@Test
	public void deve_retornar_true_caso_tipoTelefone_esteja_null() {
		
		telefone = EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.TIPO_TELEFONE_NULL);
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
	public void deve_retornar_false_caso_compare_com_null() {
		EqualsVerifier.forClass(Telefone.class).suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
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
