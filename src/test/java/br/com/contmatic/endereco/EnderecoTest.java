package br.com.contmatic.endereco;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.endereco.EstadosBrasil.ACRE;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.com.contmatic.constantes.Constante;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {

	private static Faker faker;

	/** The endereco. */
	private static Endereco endereco;
	
	/** The endereco 2. */
	private static Endereco endereco2;
	
	/**
	 * Fixture factory dados.
	 */
	@BeforeClass
	public static void fixtureFactoryDados() {
	    faker = new Faker();
		loadTemplates("br.com.contmatic.fixture.factory");
	}
	
	/**
	 * Add endereco.
	 */
	@Before
	public void addEndereco() {
		endereco = from(Endereco.class).gimme(VALID);
		endereco2 = from(Endereco.class).gimme(VALID);
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
        assertTrue(returnAnnotationMsgError(endereco, PRECISA_SER_UM_VALOR_MAIOR));
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
		assertTrue(!isNumeric(endereco.getCidade()));
	}
	
	@Test
    public void deve_retornar_true_caso_cidade_seja_possua_menos_3_caracter() {
        endereco = from(Endereco.class).gimme(CIDADE_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_cidade_seja_possua_mais_50_caracter() {
        endereco = from(Endereco.class).gimme(CIDADE_GREATER_50_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_cidade_seja_possua_caracteres_especiais() {
        endereco = from(Endereco.class).gimme(CIDADE_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
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
        assertTrue(endereco.getEstado() == ACRE);
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
        assertTrue(returnAnnotationMsgError(endereco, PRECISA_SER_UM_VALOR_MAIOR));
    }
	
	/**
	 * Nao deve aceitar rua vazia.
	 */
	@Test
	public void deve_retornar_true_caso_rua_esteja_com_espaco_em_branco() {
	    endereco = from(Endereco.class).gimme(RUA_BLANK_SPACE);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_ESTA_VAZIO));
	}
	
	@Test
    public void deve_retornar_true_caso_rua_esteja_vazia() {
        endereco = from(Endereco.class).gimme(RUA_EMPTY);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_ESTA_VAZIO));
    }
	
	@Test
    public void deve_retornar_true_caso_rua_seja_possua_menos_3_caracter() {
        endereco = from(Endereco.class).gimme(RUA_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_rua_seja_possua_mais_50_caracter() {
        endereco = from(Endereco.class).gimme(RUA_GREATER_50_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_rua_seja_possua_caracteres_especiais() {
        endereco = from(Endereco.class).gimme(RUA_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
	
	/**
	 * Nao deve aceitar cep null.
	 */
	@Test
	public void nao_deve_aceitar_cep_null() {
	    endereco = from(Endereco.class).gimme(CEP_NULL);
	    assertTrue(returnAnnotationMsgError(endereco, VALOR_ESTA_NULLO));
	}
	
	/**
	 * Nao deve aceitar cep vazio.
	 */
	@Test
	public void nao_deve_aceitar_cep_vazio() {
	    endereco = from(Endereco.class).gimme(CEP_EMPTY);
	    endereco.setCep(endereco.getCep());
	    assertTrue(returnAnnotationMsgError(endereco, VALOR_ESTA_VAZIO));
	}
	
	/**
	 * Nao deve aceitar tamanho de cep diferente oito.
	 */
	@Test
	public void deve_retornar_true_caso_cep_contenha_tamanho_diferente_de_8() {
	    endereco = from(Endereco.class).gimme(VALID);
		endereco.setCep(endereco.getCep());
		assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
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
        assertTrue(returnAnnotationMsgError(endereco, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_esteja_vazio() {
        endereco = from(Endereco.class).gimme(BAIRRO_EMPTY);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_menos_3_caracter() {
        endereco = from(Endereco.class).gimme(BAIRRO_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_mais_50_caracter() {
        endereco = from(Endereco.class).gimme("bairroGreater50Caracter");
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_caracteres_especiais() {
        endereco = from(Endereco.class).gimme(BAIRRO_WITH_SPECIAL_CARACTER);
        assertTrue(returnAnnotationMsgError(endereco, VALOR_NAO_E_VALIDO));
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
	
	/**
	 * Nao deve conter enderecos iguais.
	 */
	@Test
	public void deve_retornar_false_caso_compare_com_classe_diferente() {
		assertFalse(endereco.equals(new Object()));
	}
	
	/**
	 * Nao deve conter enderecos null.
	 */
	@Test
	public void nao_deve_conter_enderecos_null() {
		assertFalse(endereco.equals(null));
	}
	
	/**
	 * Endereco deve conter endereco o mesmo cep para serem iguais.
	 */
	@Test
	public void deve_retornar_true_caso_compare_com_mesmo_objeto() {
		assertTrue(endereco.equals(endereco));
	}
	
	/**
	 * Deve conter numero residencia iguais para serem igausi.
	 */
	@Test
	public void deve_conter_numero_residencia_iguais_para_serem_igausi() {
		endereco2.setNumeroResidencia(endereco.getNumeroResidencia());
		endereco2.setCep(endereco.getCep());
		assertTrue(endereco.equals(endereco2));
	}
	
	/**
	 * Devem ter os mesmos hash code para serem iguais.
	 */
	@Test
	public void devem_ter_os_mesmos_hashCode_para_serem_iguais() {
	    endereco.setCep(endereco2.getCep());
	    endereco.setNumeroResidencia(endereco2.getNumeroResidencia());
		assertEquals("Os enderecos são iguais", endereco.hashCode(), endereco2.hashCode());
	}
	
	/**
	 * Mostrar dados.
	 */
	@AfterClass
	public static void mostrarDados() {
		System.out.println(endereco);
	}
}
