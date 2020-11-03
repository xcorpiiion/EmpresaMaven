package br.com.contmatic.endereco;

import static br.com.contmatic.constantes.Constante.BAIRRO_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.BAIRRO_EMPTY;
import static br.com.contmatic.constantes.Constante.BAIRRO_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.BAIRRO_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.CEP_EMPTY;
import static br.com.contmatic.constantes.Constante.CEP_NULL;
import static br.com.contmatic.constantes.Constante.CIDADE_GREATER_50_CARACTER;
import static br.com.contmatic.constantes.Constante.CIDADE_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.CIDADE_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.RUA_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.RUA_EMPTY;
import static br.com.contmatic.constantes.Constante.RUA_GREATER_50_CARACTER;
import static br.com.contmatic.constantes.Constante.RUA_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.contmatic.constantes.Mensagem.BAIRRO_ENDERECO_CARACTERE_INVALIDO;
import static br.com.contmatic.constantes.Mensagem.BAIRRO_ENDERECO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.BAIRRO_ENDERECO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.CEP_ENDERECO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.CIDADE_ENDERECO_CARACTERE_INVALIDO;
import static br.com.contmatic.constantes.Mensagem.CIDADE_ENDERECO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.RUA_ENDERECO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.RUA_ENDERECO_VAZIO;
import static br.com.contmatic.endereco.EstadosBrasil.ACRE;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {

	/** The endereco. */
	private static Endereco endereco;

	@BeforeClass
	public static void fixtureFactoryDados() {
		loadTemplates("br.com.contmatic.fixture.factory");
	}

	/**
	 * Add endereco.
	 */
	@Before
	public void addEndereco() {
		endereco = from(Endereco.class).gimme(VALID);
	}

	/**
	 * Deve conter numeros positivos.
	 */
	@Test
	public void deve_conter_numeros_positivos() {
		assertTrue(endereco.getNumeroResidencia() >= 0);
	}

	/**
	 * Nao deve aceitar cidade null.
	 */
	@Test
	public void nao_deve_aceitar_cidade_null() {
		endereco = from(Endereco.class).gimme(VALID);
		assertNotNull(endereco.getCidade());
	}

	@Test
	public void deve_retornar_true_caso_cidade_esteja_com_menos_de_5_caracteres() {
		endereco.setCidade("nike");
		assertTrue(returnAnnotationMsgError(endereco, CIDADE_ENDERECO_TAMANHO));
	}

	/**
	 * Nao deve aceitar cidade vazia.
	 */
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		assertFalse(StringUtils.isEmpty(endereco.getCidade()));
	}

	/**
	 * Nao deve aceitar numero em nome cidade.
	 */
	@Test
	public void nao_deve_aceitar_numero_em_nome_cidade() {
		endereco = from(Endereco.class).gimme(VALID);
		endereco.setCidade(endereco.getCidade());
		assertFalse(isNumeric(endereco.getCidade()));
	}

	@Test
	public void deve_retornar_true_caso_cidade_seja_possua_menos_3_caracter() {
		endereco = from(Endereco.class).gimme(CIDADE_LESS_3_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, CIDADE_ENDERECO_TAMANHO));
	}

	@Test
	public void deve_retornar_true_caso_cidade_seja_possua_mais_50_caracter() {
		endereco = from(Endereco.class).gimme(CIDADE_GREATER_50_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, CIDADE_ENDERECO_TAMANHO));
	}

	@Test
	public void deve_retornar_true_caso_cidade_seja_possua_caracteres_especiais() {
		endereco = from(Endereco.class).gimme(CIDADE_WITH_SPECIAL_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, CIDADE_ENDERECO_CARACTERE_INVALIDO));
	}

	/**
	 * Nao deve aceitar estado null.
	 */
	@Test
	public void nao_deve_aceitar_estado_null() {
		endereco = from(Endereco.class).gimme(VALID);
		assertNotNull(endereco.getEstado());
	}

	@Test
	public void deve_mudar_nome_estado() {
		endereco = from(Endereco.class).gimme(VALID);
		endereco.setEstado(ACRE);
		assertSame(ACRE, endereco.getEstado());
	}

	/**
	 * Nao deve aceitar rua null.
	 */
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco = from(Endereco.class).gimme(VALID);
		assertNotNull(endereco.getRua());
	}

	@Test
	public void deve_retornar_true_caso_rua_esteja_com_menos_de_5_caracteres() {
		endereco.setRua("nike");
		assertTrue(returnAnnotationMsgError(endereco, RUA_ENDERECO_TAMANHO));
	}

	/**
	 * Nao deve aceitar rua vazia.
	 */
	@Test
	public void deve_retornar_true_caso_rua_esteja_com_espaco_em_branco() {
		endereco = from(Endereco.class).gimme(RUA_BLANK_SPACE);
		assertTrue(returnAnnotationMsgError(endereco, RUA_ENDERECO_VAZIO));
	}

	@Test
	public void deve_retornar_true_caso_rua_esteja_vazia() {
		endereco = from(Endereco.class).gimme(RUA_EMPTY);
		assertTrue(returnAnnotationMsgError(endereco, RUA_ENDERECO_VAZIO));
	}

	@Test
	public void deve_retornar_true_caso_rua_seja_possua_menos_3_caracter() {
		endereco = from(Endereco.class).gimme(RUA_LESS_3_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, RUA_ENDERECO_TAMANHO));
	}

	@Test
	public void deve_retornar_true_caso_rua_seja_possua_mais_50_caracter() {
		endereco = from(Endereco.class).gimme(RUA_GREATER_50_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, RUA_ENDERECO_TAMANHO));
	}

	/**
	 * Nao deve aceitar cep null.
	 */
	@Test
	public void nao_deve_aceitar_cep_null() {
		endereco = from(Endereco.class).gimme(CEP_NULL);
		assertTrue(returnAnnotationMsgError(endereco, CEP_ENDERECO_VAZIO));
	}

	/**
	 * Nao deve aceitar cep vazio.
	 */
	@Test
	public void nao_deve_aceitar_cep_vazio() {
		endereco = from(Endereco.class).gimme(CEP_EMPTY);
		endereco.setCep(endereco.getCep());
		assertTrue(returnAnnotationMsgError(endereco, CEP_ENDERECO_VAZIO));
	}

	/**
	 * Nao deve aceitar letras no cep.
	 */
	@Test
	public void deve_retornar_true_caso_contenha_alguma_letra_no_cep() {
		endereco = from(Endereco.class).gimme(VALID);
		assertTrue(isNumeric(endereco.getCep()));
	}

	/**
	 * Nao deve aceitar bairro null.
	 */
	@Test
	public void nao_deve_aceitar_bairro_null() {
		endereco = from(Endereco.class).gimme(VALID);
		endereco.setBairro(endereco.getBairro());
		assertNotNull(endereco.getBairro());
	}

	@Test
	public void deve_retornar_true_caso_bairro_esteja_com_espaco_em_branco() {
		endereco = from(Endereco.class).gimme(BAIRRO_BLANK_SPACE);
		assertTrue(returnAnnotationMsgError(endereco, BAIRRO_ENDERECO_VAZIO));
	}

	@Test
	public void deve_retornar_true_caso_bairro_esteja_vazio() {
		endereco = from(Endereco.class).gimme(BAIRRO_EMPTY);
		assertTrue(returnAnnotationMsgError(endereco, BAIRRO_ENDERECO_VAZIO));
	}

	@Test
	public void deve_retornar_true_caso_bairro_seja_possua_menos_3_caracter() {
		endereco = from(Endereco.class).gimme(BAIRRO_LESS_3_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, BAIRRO_ENDERECO_TAMANHO));
	}

	@Test
	public void deve_retornar_true_caso_bairro_seja_possua_mais_50_caracter() {
		endereco = from(Endereco.class).gimme("bairroGreater50Caracter");
		assertTrue(returnAnnotationMsgError(endereco, BAIRRO_ENDERECO_TAMANHO));
	}

	@Test
	public void deve_retornar_true_caso_bairro_seja_possua_caracteres_especiais() {
		endereco = from(Endereco.class).gimme(BAIRRO_WITH_SPECIAL_CARACTER);
		assertTrue(returnAnnotationMsgError(endereco, BAIRRO_ENDERECO_CARACTERE_INVALIDO));
	}

	@Test
	public void deve_retornar_true_caso_estado_for_igual_getNome_do_enum_estadoBrasil() {
		endereco.setEstado(ACRE);
		assertTrue(endereco.getEstado().getNome().equalsIgnoreCase("acre"));
	}

	@Test
	public void deve_retornar_true_caso_estado_for_igual_getSigla_do_enum_estadoBrasil() {
		endereco.setEstado(ACRE);
		assertTrue(endereco.getEstado().getSigla().equalsIgnoreCase("ac"));
	}

	@Test()
	public void deve_retornar_true_no_equals_para_serem_iguais() {
		forClass(Endereco.class).usingGetClass().suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
	}

	/**
	 * Mostrar dados.
	 */
	@AfterClass
	public static void mostrarDados() {
		System.out.println(endereco);
	}
}
