package br.com.contmatic.endereco;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.*;

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
		assertTrue(endereco.getNumero() >= 0);
	}

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
        endereco.setMunicipio(from(Municipio.class).gimme(VALID));
        endereco.setPais(from(Pais.class).gimme(VALID));
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
    public void nao_deve_aceitar_municipio_null() {
		endereco = from(Endereco.class).gimme(MUNICIPIO_NULL);
		assertNull(endereco.getMunicipio());
	}

    @Test
    public void nao_deve_aceitar_pais_null() {
		endereco = from(Endereco.class).gimme(PAIS_NULL);
		assertNull(endereco.getPais());
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
