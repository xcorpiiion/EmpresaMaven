package br.com.contmatic.empresa;

import org.junit.Before;
import org.junit.Test;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;
import br.com.contmatic.services.StringSizeException;

public class EnderecoTest {
	
	private Endereco endereco;
	
	@Before
	public void addEndereco() {
		endereco = new Endereco("Arnaldo", "Jardim Maria", "02676020", "150-A", "São Paulo", "SP");
	}
	
	@Test
	public void nao_deve_aceitar_numero_residencia_null() {
		endereco.setNumeroResidencia("5");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_numero_residencia_null_() {
		endereco.setNumeroResidencia(null);
	}
	
	@Test
	public void nao_deve_aceitar_numero_residencia_vazio() {
		endereco.setNumeroResidencia("6");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_numero_residencia_vazio_() {
		endereco.setNumeroResidencia("");
	}
	
	@Test
	public void nao_deve_aceitar_cidade_null() {
		endereco.setCidade("Rio de Janeiro");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cidade_null_() {
		endereco.setCidade(null);
	}
	
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		endereco.setCidade("a");
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_cidade_vazia_() {
		endereco.setCidade("");
	}
	
	@Test
	public void nao_deve_aceitar_numero_em_nome_cidade() {
		endereco.setCidade("Sorocaba");
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_numero_em_nome_cidade_() {
		endereco.setCidade("0");
	}
	
	@Test
	public void nao_deve_aceitar_estado_null() {
		endereco.setEstado("Rio de Janeiro");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_estado_null_() {
		endereco.setEstado(null);
	}
	
	@Test
	public void nao_deve_aceitar_estado_vazio() {
		endereco.setEstado("a");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_estado_vazio_() {
		endereco.setEstado("");
	}
	
	@Test
	public void nao_deve_aceitar_numero_em_nome_estado() {
		endereco.setEstado("Sorocaba");
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_numero_em_nome_estado_() {
		endereco.setEstado("0");
	}
	
	@Test
	public void nao_deve_aceitar_rua_null() {
		endereco.setRua("Afronta");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_rua_null_() {
		endereco.setRua(null);
	}
	
	@Test
	public void nao_deve_aceitar_rua_vazia() {
		endereco.setRua("Afronta");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_rua_vazia_() {
		endereco.setRua("");
	}
	
	@Test
	public void nao_deve_aceitar_cep_null() {
		endereco.setCep("12345678");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cep_null_() {
		endereco.setCep(null);
	}
	
	@Test
	public void nao_deve_aceitar_cep_vazio() {
		endereco.setCep("12345678");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cep_vazio_() {
		endereco.setCep("");
	}
	
	@Test
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito() {
		endereco.setCep("12345678");
	}
	
	@Test(expected = StringSizeException.class)
	public void nao_deve_aceitar_tamanho_de_cep_diferente_oito_() {
		endereco.setCep("123456789");
	}
	
	@Test
	public void nao_deve_aceitar_letras_no_cep() {
		endereco.setCep("12345678");
	}
	
	@Test(expected = StringFormatException.class)
	public void nao_deve_aceitar_letras_no_cep_() {
		endereco.setCep("123ss678");
	}
	
	@Test
	public void nao_deve_aceitar_bairro_null() {
		endereco.setBairro("Bairro");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_bairro_null_() {
		endereco.setBairro(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_bairro_vazio() {
		endereco.setBairro("Bairro");
	}
}
