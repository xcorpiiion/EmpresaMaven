package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.contmatic.fixture.factory.FixtureFactoryEndereco;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;
import br.com.contmatic.services.StringSizeException;

public class EnderecoTest {
	
	private Endereco endereco;
	
	private Endereco endereco2;
	
	@Before
	public void addEndereco() {
		endereco = new Endereco("Arnaldo", "Jardim Maria", "02676020", "150-A", "São Paulo", EstadosBrasil.PIAUI);
		endereco2 = new Endereco("Arnaldo", "Jardim Maria", "02676020", "150-A", "São Paulo", EstadosBrasil.PARANA);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_numero_residencia_null_expection() {
		endereco.setNumeroResidencia(null);
	}
	
	@Test
	public void deve_conter_numero() {
		endereco.setNumeroResidencia("6");
		assertThat(endereco.getNumeroResidencia(), is("6"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_numero_residencia_vazio_expection() {
		endereco.setNumeroResidencia("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_numero_residencia_com_espaco_em_branco_expection() {
		endereco.setNumeroResidencia(" ");
	}
	
	@Test
	public void nao_deve_aceitar_cidade_null() {
	    endereco = FixtureFactoryEndereco.cidade();
		assertNotNull(endereco.getCidade());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_null_expection() {
		endereco.setCidade(FixtureFactoryEndereco.cidadeNull().getCidade());
	}
	
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		assertFalse(FixtureFactoryEndereco.cidade().getCidade().isEmpty());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_vazia_exception() {
		endereco.setCidade(FixtureFactoryEndereco.cidadeEmpty().getCidade());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_com_espaco_em_branco_expection() {
		endereco.setCidade(FixtureFactoryEndereco.cidadeBlankSpace().getCidade());
	}
	
	@Test
	public void nao_deve_aceitar_numero_em_nome_cidade() {
		endereco.setCidade("Sorocaba");
		assertThat(endereco.getCidade(), is("Sorocaba"));
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_numero_em_nome_cidade_exception() {
		endereco.setCidade("0");
	}
	
	@Test
	public void nao_deve_aceitar_estado_null() {
		endereco.setEstado(EstadosBrasil.ACRE);
		assertThat(endereco.getEstado(), is(EstadosBrasil.ACRE));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_estado_null_exception() {
		endereco.setEstado(null);
	}
	
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco.setRua("Afronta");
		assertThat(endereco.getRua(), is("Afronta"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_null_() {
		endereco.setRua(null);
	}
	
	@Test
	public void nao_deve_aceitar_rua_vazia() {
		endereco.setRua("Afronta");
		assertTrue(StringUtils.equalsIgnoreCase(endereco.getRua(), "Afronta"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_vazia_exception() {
		endereco.setRua("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_com_espaco_em_branco() {
		endereco.setRua(" ");
	}
	
	@Test
	public void nao_deve_aceitar_cep_null() {
		endereco.setCep("12345678");
		assertThat(endereco.getCep(), is("12345678"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_null_exception() {
		endereco.setCep(null);
	}
	
	@Test
	public void nao_deve_aceitar_cep_vazio() {
		endereco.setCep("12345678");
		assertThat(endereco.getCep(), is("12345678"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_vazio_exception() {
		endereco.setCep("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_com_espaco_em_branco() {
		endereco.setCep(" ");
	}
	
	@Test
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito() {
		endereco.setCep("12345678");
		assertThat(endereco.getCep(), is("12345678"));
	}
	
	@Test(expected = StringSizeException.class)
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito_expection() {
		endereco.setCep("123456789");
	}
	
	@Test
	public void nao_deve_aceitar_letras_no_cep() {
		endereco.setCep("12345678");
		assertThat(endereco.getCep(), is("12345678"));
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_letras_no_cep_exception() {
		endereco.setCep("123ss678");
	}
	
	@Test
	public void nao_deve_aceitar_bairro_null() {
		endereco.setBairro("Bairro");
		assertThat(endereco.getBairro(), is("Bairro"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_null_exception() {
		endereco.setBairro(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_com_espaco_em_branco() {
		endereco.setBairro(" ");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_vazio_exception() {
		endereco.setBairro("");
	}
	
	@Test
	public void nao_deve_conter_enderecos_iguais() {
		assertTrue(endereco.equals(endereco2));
	}
	
	@Test
	public void nao_deve_conter_enderecos_null() {
		assertFalse(endereco.equals(null));
	}
	
	@Test
	public void endereco_deve_conter_endereco_o_mesmo_cep_para_serem_iguais() {
		endereco2.setCep("01234567");
		assertFalse(endereco.equals(endereco2));
	}
	
	@Test
	public void deve_conter_enderecos_numero_residencia_iguais_para_serem_igausi() {
		endereco2.setNumeroResidencia("0");
		assertFalse(endereco.equals(endereco2));
	}
	
	@Test
	public void devem_ter_os_mesmos_hashCode_para_serem_iguais() {
		assertEquals("Os enderecos são iguais", endereco.hashCode(), endereco2.hashCode());
	}
	
	@After
	public void mostrarDados() {
		System.out.println(endereco);
	}
}
