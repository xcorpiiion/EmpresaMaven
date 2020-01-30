package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.contmatic.constantes.Constante;
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
		endereco = new Endereco("Arnaldo", "Jardim Maria", "02676020", 150, "São Paulo", EstadosBrasil.PIAUI);
		endereco2 = new Endereco("Arnaldo", "Jardim Maria", "02676020", 150, "São Paulo", EstadosBrasil.PARANA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_residencia_menor_que_zero_expection() {
		endereco.setNumeroResidencia(FixtureFactoryEndereco.numeroResidenciaLessThanZero().getNumeroResidencia());
	}
	
	@Test
	public void deve_conter_numeros_positivos() {
		endereco.setNumeroResidencia(FixtureFactoryEndereco.numeroResidencia().getNumeroResidencia());
		assertTrue(endereco.getNumeroResidencia() >= 0);
	}
	
	@Test
	public void nao_deve_aceitar_cidade_null() {
	    endereco = FixtureFactoryEndereco.cidadeName();
		assertNotNull(endereco.getCidade());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_null_expection() {
		endereco.setCidade(FixtureFactoryEndereco.cidadeNull().getCidade());
	}
	
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		assertFalse(FixtureFactoryEndereco.cidadeName().getCidade().isEmpty());
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
		endereco.setCidade(FixtureFactoryEndereco.cidadeName().getCidade());
		assertTrue(FixtureFactoryEndereco.cidadeName().getCidade() != Constante.ILLEGAL_NUMBER);
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_numero_em_nome_cidade_exception() {
		endereco.setCidade("0");
	}
	
	@Test
	public void nao_deve_aceitar_estado_null() {
	    endereco.setEstado(FixtureFactoryEndereco.estadoName().getEstado());
		assertNotNull(endereco.getEstado());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_estado_null_exception() {
		endereco.setEstado(FixtureFactoryEndereco.estadoNull().getEstado());
	}
	
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco.setRua(FixtureFactoryEndereco.ruaName().getRua());
		assertNotNull(FixtureFactoryEndereco.ruaName().getRua());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_null_() {
		endereco.setRua(FixtureFactoryEndereco.ruaNull().getRua());
	}
	
	@Test
	public void nao_deve_aceitar_rua_vazia() {
	    endereco.setRua(FixtureFactoryEndereco.ruaName().getRua());
		assertFalse(FixtureFactoryEndereco.ruaName().getRua().isEmpty());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_vazia_exception() {
		endereco.setRua(FixtureFactoryEndereco.ruaEmpty().getRua());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_com_espaco_em_branco() {
	    endereco.setRua(FixtureFactoryEndereco.ruaBlankSpace().getRua());
	}
	
	@Test
	public void nao_deve_aceitar_cep_null() {
		endereco.setCep(FixtureFactoryEndereco.cepStringNumber().getCep());
		assertNotNull(endereco.getCep());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_null_exception() {
		endereco.setCep(FixtureFactoryEndereco.cepNull().getCep());
	}
	
	@Test
	public void nao_deve_aceitar_cep_vazio() {
	    endereco.setCep(FixtureFactoryEndereco.cepStringNumber().getCep());
		assertFalse(FixtureFactoryEndereco.cepStringNumber().getCep().isEmpty());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_vazio_exception() {
		endereco.setCep(FixtureFactoryEndereco.cepEmpty().getCep());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_com_espaco_em_branco() {
		endereco.setCep(FixtureFactoryEndereco.cepBlankSpace().getCep());
	}
	
	@Test
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito() {
		endereco.setCep(FixtureFactoryEndereco.cepStringNumber().getCep());
		assertEquals("O endereço não possui 8 digitos", 8, endereco.getCep().length());
	}
	
	@Test(expected = StringSizeException.class)
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito_expection() {
		endereco.setCep("123456789");
	}
	
	@Test
	public void nao_deve_aceitar_letras_no_cep() {
		endereco.setCep(FixtureFactoryEndereco.cepStringNumber().getCep());
		assertTrue(endereco.getCep().matches(Constante.ILLEGAL_NUMBER));
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_letras_no_cep_exception() {
		endereco.setCep("123ss678");
	}
	
	@Test
	public void nao_deve_aceitar_bairro_null() {
		endereco.setBairro(FixtureFactoryEndereco.bairroName().getBairro());
		assertNotNull(endereco.getBairro());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_null_exception() {
		endereco.setBairro(FixtureFactoryEndereco.bairroNull().getBairro());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_com_espaco_em_branco() {
		endereco.setBairro(FixtureFactoryEndereco.bairroBlankSpace().getBairro());
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_vazio_exception() {
		endereco.setBairro(FixtureFactoryEndereco.bairroEmpty().getBairro());
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
		endereco2.setNumeroResidencia(FixtureFactoryEndereco.numeroResidencia().getNumeroResidencia());
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
