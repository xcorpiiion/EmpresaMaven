package br.com.contmatic.endereco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	
	/** The endereco. */
	private static Endereco endereco;
	
	/** The endereco 2. */
	private static Endereco endereco2;
	
	/**
	 * Fixture factory dados.
	 */
	@BeforeClass
	public static void fixtureFactoryDados() {
	    FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
	}
	
	/**
	 * Add endereco.
	 */
	@Before
	public void addEndereco() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco2 = Fixture.from(Endereco.class).gimme("valid");
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
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getCidade());
	}
	
	@Test
    public void deve_retornar_true_caso_cidade_esteja_com_menos_de_5_caracteres() {
        endereco.setCidade("nike");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
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
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setCidade(endereco.getCidade());
		assertTrue(!StringUtils.isNumeric(endereco.getCidade()));
	}
	
	@Test
    public void deve_retornar_true_caso_cidade_seja_possua_menos_3_caracter() {
        endereco = Fixture.from(Endereco.class).gimme("cidadeLess3Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_cidade_seja_possua_mais_50_caracter() {
        endereco = Fixture.from(Endereco.class).gimme("cidadeGreater50Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_cidade_seja_possua_caracteres_especiais() {
        endereco = Fixture.from(Endereco.class).gimme("cidadeWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
	
	/**
	 * Nao deve aceitar estado null.
	 */
	@Test
	public void nao_deve_aceitar_estado_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getEstado());
	}
	
	@Test
    public void deve_mudar_nome_estado() {
        endereco = Fixture.from(Endereco.class).gimme("valid");
        endereco.setEstado(EstadosBrasil.ACRE);
        assertTrue(endereco.getEstado() == EstadosBrasil.ACRE);
    }
	
	/**
	 * Nao deve aceitar rua null.
	 */
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getRua());
	}
	
	@Test
    public void deve_retornar_true_caso_rua_esteja_com_menos_de_5_caracteres() {
        endereco.setRua("nike");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
    }
	
	/**
	 * Nao deve aceitar rua vazia.
	 */
	@Test
	public void deve_retornar_true_caso_rua_esteja_com_espaco_em_branco() {
	    endereco = Fixture.from(Endereco.class).gimme("ruaBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
	}
	
	@Test
    public void deve_retornar_true_caso_rua_esteja_vazia() {
        endereco = Fixture.from(Endereco.class).gimme("ruaEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
    }
	
	@Test
    public void deve_retornar_true_caso_rua_seja_possua_menos_3_caracter() {
        endereco = Fixture.from(Endereco.class).gimme("ruaLess3Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_rua_seja_possua_mais_50_caracter() {
        endereco = Fixture.from(Endereco.class).gimme("ruaGreater50Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_rua_seja_possua_caracteres_especiais() {
        endereco = Fixture.from(Endereco.class).gimme("ruaWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
	
	/**
	 * Nao deve aceitar cep null.
	 */
	@Test
	public void nao_deve_aceitar_cep_null() {
	    endereco = Fixture.from(Endereco.class).gimme("cepNull");
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_NULLO));
	}
	
	/**
	 * Nao deve aceitar cep vazio.
	 */
	@Test
	public void nao_deve_aceitar_cep_vazio() {
	    endereco = Fixture.from(Endereco.class).gimme("cepEmpty");
	    endereco.setCep(endereco.getCep());
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
	}
	
	/**
	 * Nao deve aceitar tamanho de cep diferente oito.
	 */
	@Test
	public void deve_retornar_true_caso_cep_contenha_tamanho_diferente_de_8() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setCep(endereco.getCep());
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
	}
	
	/**
	 * Nao deve aceitar letras no cep.
	 */
	@Test
	public void deve_retornar_true_caso_contenha_alguma_letra_no_cep() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		assertTrue(StringUtils.isNumeric(endereco.getCep()));
	}
	
	/**
	 * Nao deve aceitar bairro null.
	 */
	@Test
	public void nao_deve_aceitar_bairro_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setBairro(endereco.getBairro());
		assertNotNull(endereco.getBairro());
	}
	
	@Test
    public void deve_retornar_true_caso_bairro_esteja_com_espaco_em_branco() {
        endereco = Fixture.from(Endereco.class).gimme("bairroBlankSpace");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_esteja_vazio() {
        endereco = Fixture.from(Endereco.class).gimme("bairroEmpty");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_menos_3_caracter() {
        endereco = Fixture.from(Endereco.class).gimme("bairroLess3Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_mais_50_caracter() {
        endereco = Fixture.from(Endereco.class).gimme("bairroGreater50Caracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_caracteres_especiais() {
        endereco = Fixture.from(Endereco.class).gimme("bairroWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_estado_for_igual_getNome_do_enum_estadoBrasil() {
        endereco.setEstado(EstadosBrasil.ACRE);
        assertTrue(endereco.getEstado().getNome().equalsIgnoreCase("acre"));
    }
    
    @Test
    public void deve_retornar_true_caso_estado_for_igual_getSigla_do_enum_estadoBrasil() {
        endereco.setEstado(EstadosBrasil.ACRE);
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
