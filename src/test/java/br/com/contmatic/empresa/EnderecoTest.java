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
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;
import br.com.contmatic.services.StringSizeException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class EnderecoTest {
	
	private Endereco endereco;
	
	private Endereco endereco2;
	
	@BeforeClass
	public static void fixtureFactoryDados() {
	    FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
	}
	
	@Before
	public void addEndereco() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco2 = Fixture.from(Endereco.class).gimme("valid");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_residencia_menor_que_zero_expection() {
	    endereco = Fixture.from(Endereco.class).gimme("numeroResidenciaLessThanZero");
		endereco.setNumeroResidencia(endereco.getNumeroResidencia());
	}
	
	@Test
	public void deve_conter_numeros_positivos() {
	    System.out.println(endereco.getNumeroResidencia());
		assertTrue(endereco.getNumeroResidencia() >= 0);
	}
	
	@Test
	public void nao_deve_aceitar_cidade_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getCidade());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_null_expection() {
	    endereco = Fixture.from(Endereco.class).gimme("cidadeNull");
		endereco.setCidade(endereco.getCidade());
	}
	
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		assertFalse(StringUtils.isEmpty(endereco.getCidade()));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_vazia_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("cidadeEmpty");
		endereco.setCidade(endereco.getCidade());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_com_espaco_em_branco_expection() {
	    endereco = Fixture.from(Endereco.class).gimme("cidadeBlankSpace");
        endereco.setCidade(endereco.getCidade());
	}
	
	@Test
	public void nao_deve_aceitar_numero_em_nome_cidade() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setCidade(endereco.getCidade());
		assertTrue(!StringUtils.isNumeric(endereco.getCidade()));
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_numero_em_nome_cidade_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("cidadeContainsNumber");
	    System.out.println(endereco.getCidade());
		endereco.setCidade("5465gfdgd");
	}
	
	@Test
	public void nao_deve_aceitar_estado_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getEstado());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_estado_null_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("estadoNull");
		endereco.setEstado(endereco.getEstado());
	}
	
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getRua());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_null_() {
	    endereco = Fixture.from(Endereco.class).gimme("ruaNull");
		endereco.setRua(endereco.getRua());
	}
	
	@Test
	public void nao_deve_aceitar_rua_vazia() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertFalse(StringUtils.isEmpty(endereco.getRua()));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_vazia_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("ruaEmpty");
		endereco.setRua(endereco.getRua());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_com_espaco_em_branco() {
	    endereco = Fixture.from(Endereco.class).gimme("ruaBlankSpace");
	    endereco.setRua(endereco.getRua());
	}
	
	@Test
	public void nao_deve_aceitar_cep_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		assertNotNull(endereco.getCep());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_null_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("cepNull");
		endereco.setCep(endereco.getCep());
	}
	
	@Test
	public void nao_deve_aceitar_cep_vazio() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
	    endereco.setCep(endereco.getCep());
		assertFalse(StringUtils.isEmpty(endereco.getCep()));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_vazio_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("cepEmpty");
		endereco.setCep(endereco.getCep());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_com_espaco_em_branco() {
	    endereco = Fixture.from(Endereco.class).gimme("cepBlankSpace");
		endereco.setCep(endereco.getCep());
	}
	
	@Test
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setCep(endereco.getCep());
		assertEquals("O endereço não possui 8 digitos", 8, endereco.getCep().length());
	}
	
	@Test(expected = StringSizeException.class)
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito_expection() {
	    endereco = Fixture.from(Endereco.class).gimme("cepLengthDifference8");
	    System.out.println(endereco.getCep());
		endereco.setCep(endereco.getCep());
	}
	
	@Test
	public void nao_deve_aceitar_letras_no_cep() {
		endereco = Fixture.from(Endereco.class).gimme("valid");
		assertTrue(StringUtils.isNumeric(endereco.getCep()));
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_letras_no_cep_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("cepContainsWord");
		endereco.setCep(endereco.getCep());
	}
	
	@Test
	public void nao_deve_aceitar_bairro_null() {
	    endereco = Fixture.from(Endereco.class).gimme("valid");
		endereco.setBairro(endereco.getBairro());
		assertNotNull(endereco.getBairro());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_null_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("bairroNull");
		endereco.setBairro(endereco.getBairro());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_com_espaco_em_branco() {
	    endereco = Fixture.from(Endereco.class).gimme("bairroBlankSpace");
		endereco.setBairro(endereco.getBairro());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_vazio_exception() {
	    endereco = Fixture.from(Endereco.class).gimme("bairroEmpty");
		endereco.setBairro(endereco.getBairro());
	}
	
	@Test
	public void nao_deve_conter_enderecos_iguais() {
		assertFalse(endereco.equals(endereco2));
	}
	
	@Test
	public void nao_deve_conter_enderecos_null() {
		assertFalse(endereco.equals(null));
	}
	
	@Test
	public void endereco_deve_conter_endereco_o_mesmo_cep_para_serem_iguais() {
		endereco2.setCep(endereco.getCep());
		assertFalse(endereco.equals(endereco2));
	}
	
	@Test
	public void deve_conter_numero_residencia_iguais_para_serem_igausi() {
		endereco2.setNumeroResidencia(endereco.getNumeroResidencia());
		assertTrue(endereco.equals(endereco2));
	}
	
	@Test
	public void devem_ter_os_mesmos_hashCode_para_serem_iguais() {
	    endereco.setCep(endereco2.getCep());
	    endereco.setNumeroResidencia(endereco2.getNumeroResidencia());
		assertEquals("Os enderecos são iguais", endereco.hashCode(), endereco2.hashCode());
	}
	
	@After
	public void mostrarDados() {
		System.out.println(endereco);
	}
}
