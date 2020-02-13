package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.fixture.factory.FixtureFactoryEndereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {
	
	/** The endereco. */
	private Endereco endereco;
	
	/** The endereco 2. */
	private Endereco endereco2;
	
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
		endereco = FixtureFactoryEndereco.enderecoValido();
		endereco2 = FixtureFactoryEndereco.enderecoValido();
	}
	
	/**
	 * Deve conter numeros positivos.
	 */
	@Test
	public void deve_conter_numeros_positivos() {
	    System.out.println(endereco.getNumeroResidencia());
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
	
	/**
	 * Nao deve aceitar estado null.
	 */
	@Test
	public void nao_deve_aceitar_estado_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getEstado());
	}
	
	/**
	 * Nao deve aceitar rua null.
	 */
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getRua());
	}
	
	/**
	 * Nao deve aceitar rua vazia.
	 */
	@Test
	public void nao_deve_aceitar_rua_vazia() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertFalse(StringUtils.isEmpty(endereco.getRua()));
	}
	
	/**
	 * Nao deve aceitar cep null.
	 */
	@Test
	public void nao_deve_aceitar_cep_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getCep());
	}
	
	/**
	 * Nao deve aceitar cep vazio.
	 */
	@Test
	public void nao_deve_aceitar_cep_vazio() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
	    endereco.setCep(endereco.getCep());
		assertFalse(StringUtils.isEmpty(endereco.getCep()));
	}
	
	/**
	 * Nao deve aceitar tamanho de cep diferente oito.
	 */
	@Test
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setCep(endereco.getCep());
		assertEquals("O endereço não possui 8 digitos", 8, endereco.getCep().length());
	}
	
	/**
	 * Nao deve aceitar letras no cep.
	 */
	@Test
	public void nao_deve_aceitar_letras_no_cep() {
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
	
	/**
	 * Nao deve conter enderecos iguais.
	 */
	@Test
	public void nao_deve_conter_enderecos_iguais() {
		assertFalse(endereco.equals(endereco2));
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
	public void endereco_deve_conter_endereco_o_mesmo_cep_para_serem_iguais() {
		endereco2.setCep(endereco.getCep());
		assertFalse(endereco.equals(endereco2));
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
	@After
	public void mostrarDados() {
		System.out.println(endereco);
	}
}
