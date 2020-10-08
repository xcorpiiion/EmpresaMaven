package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.utils.InstanciaClasses;
import br.com.contmatic.services.EmptyStringException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class EnderecoTest {
	
	private static Endereco endereco;
	
	private static Faker faker;
	
	@BeforeClass
	public static void addEndereco() {
		faker = new Faker();
		endereco = InstanciaClasses.criaEndereco();
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_numero_residencia_null_expection() {
		endereco.setNumeroResidencia(null);
	}
	
	@Test
	public void deve_conter_numero_residencia() {
		String numeroResidencia = String.valueOf(faker.number().numberBetween(1, 200));
		endereco.setNumeroResidencia(numeroResidencia);
		assertThat(endereco.getNumeroResidencia(), is(numeroResidencia));
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
		String city = faker.address().city();
		endereco.setCidade(city);
		assertThat(endereco.getCidade(), is(city));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cidade_null_expection() {
		endereco.setCidade(null);
	}
	
	@Test
	public void nao_deve_aceitar_cidade_vazia() {
		String city = faker.address().city();
		endereco.setCidade(city);
		assertThat(endereco.getCidade(), is(city));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_vazia_exception() {
		endereco.setCidade("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cidade_com_espaco_em_branco_expection() {
		endereco.setCidade(" ");
	}
	
	@Test
	public void nao_deve_aceitar_numero_em_nome_cidade() {
		endereco.setCidade("Sorocaba");
		assertThat(endereco.getCidade(), is("Sorocaba"));
	}
	
	@Test
	public void nao_deve_aceitar_estado_null() {
		endereco.setEstado("Rio de Janeiro");
		assertThat(endereco.getEstado(), is("Rio de Janeiro"));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_estado_null_exception() {
		endereco.setEstado(null);
	}
	
	@Test
	public void nao_deve_aceitar_estado_vazio() {
		endereco.setEstado("aa");
		assertThat(endereco.getEstado(), is("aa"));
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_estado_vazio_exception() {
		endereco.setEstado("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_estado_com_espaco_em_branco_exception() {
		endereco.setEstado(" ");
	}
	
	@Test
	public void nao_deve_aceitar_numero_em_nome_estado() {
		endereco.setEstado("Sorocaba");
		assertThat(endereco.getEstado(), is("Sorocaba"));
	}
	
	@Test
	public void nao_deve_aceitar_rua_null() {
		String street = faker.address().streetName();
		endereco.setRua(street);
		assertThat(endereco.getRua(), is(street));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_rua_null_() {
		endereco.setRua(null);
	}
	
	@Test
	public void nao_deve_aceitar_rua_vazia() {
		String street = faker.address().streetName();
		endereco.setRua(street);
		assertThat(endereco.getRua(), is(street));
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
		String zipCode = String.valueOf(faker.number().numberBetween(00000001, 99999999));
		endereco.setCep(zipCode);
		assertThat(endereco.getCep(), is(zipCode));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cep_null_exception() {
		endereco.setCep(null);
	}
	
	@Test
	public void nao_deve_aceitar_cep_vazio() {
		String zipCode = String.valueOf(faker.number().numberBetween(00000001, 99999999));
		endereco.setCep(zipCode);
		assertThat(endereco.getCep(), is(zipCode));
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
	public void nao_deve_aceitar_letras_no_cep() {
		endereco.setCep("12345678");
		assertThat(endereco.getCep(), is("12345678"));
	}
	
	@Test
	public void nao_deve_aceitar_bairro_null() {
		endereco.setBairro("Bairro");
		assertThat(endereco.getBairro(), is("Bairro"));
	}
	
	@Test(expected = NullPointerException.class)
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
	
	@Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        EqualsVerifier.forClass(Endereco.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }
	
	@After
	public void mostrarDados() {
		System.out.println(endereco);
	}
}
