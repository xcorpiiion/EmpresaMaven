package br.com.contmatic.endereco;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.utils.Mensagem;
import br.com.contmatic.easyrandom.EasyRandomEndereco;
import br.com.contmatic.easyrandom.enums.TipoDadoParaTesteEndereco;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {
	
	/** The endereco. */
	private static Endereco endereco;


	/**
	 * Add endereco.
	 */
	@Before
	public void addEndereco() {
		endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO);
		System.out.println();
	}
	
	/**
	 * Deve conter numeros positivos.
	 */
	@Test
    public void deve_retornar_true_caso_numeroEndereco_seja_menor_do_que_zero() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.NUMERO_RESIDENCIA_LESS_THAN_ZERO);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
    }
	
	/**
	 * Nao deve aceitar cidade null.
	 */
	@Test
	public void deve_retornar_true_caso_cidade_seja_null() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CIDADE_NULL);
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_NULLO));
	}
	
	@Test
    public void deve_retornar_true_caso_cidade_esteja_com_menos_de_3_caracteres() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CIDADE_LESS_3_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
    }
	
	/**
	 * Nao deve aceitar cidade vazia.
	 */
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CIDADE_EMPTY);
		assertFalse(StringUtils.isEmpty(endereco.getCidade()));
	}
	
	/**
	 * Nao deve aceitar numero em nome cidade.
	 */
	@Test
	public void nao_deve_aceitar_numero_em_nome_cidade() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CIDADE_CONTAINS_NUMBER);
		assertTrue(!StringUtils.isNumeric(endereco.getCidade()));
	}
    
    @Test
    public void deve_retornar_true_caso_cidade_seja_possua_mais_50_caracter() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CIDADE_GREATER_50_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_cidade_seja_possua_caracteres_especiais() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CIDADE_WITH_SPECIAL_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
	
	/**
	 * Nao deve aceitar estado null.
	 */
	@Test
	public void deve_retornar_true_caso_estado_seja_null() {
	    Endereco enderecoTest = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.ESTADO_NULL);
	    endereco.setEstado(enderecoTest.getEstado());
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_NULLO));
	}
	
	@Test
    public void deve_mudar_nome_estado() {
        endereco.setEstado(EstadosBrasil.ACRE);
        assertSame(EstadosBrasil.ACRE, endereco.getEstado());
    }
	
	/**
	 * Nao deve aceitar rua null.
	 */
	@Test
	public void deve_retornar_true_caso_rua_seja_null() {
		endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.RUA_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_NULLO));
	}
	
	@Test
    public void deve_retornar_true_caso_rua_esteja_com_menos_de_5_caracteres() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.RUA_LESS_3_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
    }
	
	/**
	 * Nao deve aceitar rua vazia.
	 */
	@Test
	public void deve_retornar_true_caso_rua_esteja_com_espaco_em_branco() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.RUA_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
	}
	
	@Test
    public void deve_retornar_true_caso_rua_esteja_vazia() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.RUA_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_rua_seja_possua_mais_50_caracter() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.RUA_GREATER_50_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_rua_seja_possua_caracteres_especiais() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.RUA_WITH_SPECIAL_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
	
	/**
	 * Nao deve aceitar cep null.
	 */
	@Test
	public void nao_deve_aceitar_cep_null() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CEP_NULL);
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_NULLO));
	}
	
	/**
	 * Nao deve aceitar cep vazio.
	 */
	@Test
	public void nao_deve_aceitar_cep_vazio() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CEP_EMPTY);
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
	}
	
	/**
	 * Nao deve aceitar tamanho de cep diferente oito.
	 */
	@Test
	public void deve_retornar_true_caso_cep_contenha_tamanho_diferente_de_8() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CEP_LENGTH_DIFFERENCE_8);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
	}
	
	/**
	 * Nao deve aceitar letras no cep.
	 */
	@Test
	public void deve_retornar_false_caso_contenha_alguma_letra_no_cep() {
		endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.CEP_CONTAINS_WORD);
		assertFalse(StringUtils.isNumeric(endereco.getCep()));
	}
	
	/**
	 * Nao deve aceitar bairro null.
	 */
	@Test
	public void deve_retornar_true_caso_bairro_seja_null() {
	    endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.BAIRRO_NULL);
	    assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_NULLO));
	}
	
	@Test
    public void deve_retornar_true_caso_bairro_esteja_com_espaco_em_branco() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.BAIRRO_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_esteja_vazio() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.BAIRRO_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_menos_3_caracter() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.BAIRRO_LESS_3_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_mais_50_caracter() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.BAIRRO_GREATER_50_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(endereco, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_bairro_seja_possua_caracteres_especiais() {
        endereco = EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.BAIRRO_WITH_SPECIAL_CARACTER);
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
	public void deve_verificar_todos_os_campos_de_equals_hashcode() {
		EqualsVerifier.forClass(Endereco.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED).verify();
	}
	
	
	
	/**
	 * Mostrar dados.
	 */
	@AfterClass
	public static void mostrarDados() {
		System.out.println(endereco);
	}
}
