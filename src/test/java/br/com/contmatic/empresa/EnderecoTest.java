package br.com.contmatic.empresa;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EnderecoTest {

	private static Endereco endereco;

	private static Faker faker;

	@BeforeClass
	public static void addEndereco() {
		faker = new Faker();
	}

	@Test
	public void deve_aceitar_rua_valido() {
		final String firstName = faker.address().streetName();
		endereco.setRua(firstName);
		assertEquals(firstName, endereco.getRua());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_rua_com_mais_3_caracteres() {
		endereco.setRua("aa");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_rua_com_menos_50_caracteres() {
		endereco.setRua(
				"aawwwwwwwertgfdswqafcvfgtdsyhtru" + "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru"
						+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estado_rua_caracteres_especiais() {
		endereco.setRua(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rua_empty() {
		endereco.setRua(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rua_vazio() {
		endereco.setRua(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rua_null() {
		endereco.setRua(null);
	}

	/* Testa número de residencia */

	public void deve_aceitar_numero_residencia_valido() {
		final Integer number = faker.number().numberBetween(1, 1000);
		endereco.setNumeroResidencia(number);
		assertEquals(number, endereco.getNumeroResidencia());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_numero_residencia_maior_que_0_caracteres() {
		endereco.setNumeroResidencia(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_numero_residencia_com_menos_10000_caracteres() {
		endereco.setNumeroResidencia(10001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_residencia_null_expection() {
		endereco.setNumeroResidencia(null);
	}

	/* Testa cidade */

	@Test
	public void deve_aceitar_cidade_valida() {
		final String firstName = faker.name().firstName();
		endereco.setCidade(firstName);
		assertEquals(firstName, endereco.getCidade());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_estado_com_mais_1_caracteres() {
		endereco.setCidade("a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_cidade_com_menos_50_caracteres() {
		endereco.setCidade("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_com_caracteres_especiais() {
		endereco.setCidade(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_empty() {
		endereco.setCidade(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_vazio() {
		endereco.setCidade(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_null() {
		endereco.setCidade(null);
	}

	/* Testa estado */

	@Test
	public void deve_aceitar_estado_valido() {
		final String firstName = faker.name().firstName();
		endereco.setEstado(firstName);
		assertEquals(firstName, endereco.getEstado());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_estado_com_mais_2_caracteres() {
		endereco.setEstado("a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_estado_com_menos_50_caracteres() {
		endereco.setEstado("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estado_com_caracteres_especiais() {
		endereco.setEstado(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estado_empty() {
		endereco.setEstado(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estado_vazio() {
		endereco.setEstado(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estado_null() {
		endereco.setEstado(null);
	}

	/* Testa cep */

	public void deve_aceitar_cep_valido() {
		String zipCode = String.valueOf(faker.number().numberBetween(00000001, 99999999));
		endereco.setCep(zipCode);
		assertThat(endereco.getCep(), is(zipCode));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_letras_no_cep() {
		endereco.setCep("1234567a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_menor_do_que_8_caracteres() {
		endereco.setCep("1234567");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_maior_do_que_8_caracteres() {
		endereco.setCep("123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_vazio_exception() {
		endereco.setCep("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_com_espaco_em_branco() {
		endereco.setCep(" ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_null_exception() {
		endereco.setCep(null);
	}

	/* Testa bairro */

	@Test
	public void deve_aceitar_bairro_valido() {
		final String firstName = faker.name().firstName();
		endereco.setBairro(firstName);
		assertEquals(firstName, endereco.getBairro());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_bairro_com_mais_1_caracteres() {
		endereco.setBairro("a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_bairro_com_menos_50_caracteres() {
		endereco.setBairro("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_com_caracteres_especiais() {
		endereco.setBairro(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_empty() {
		endereco.setBairro(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_vazio() {
		endereco.setBairro(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_null() {
		endereco.setBairro(null);
	}

	@Test()
	public void deve_retornar_true_no_equals_para_serem_iguais() {
		forClass(Endereco.class).usingGetClass()
				.suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
	}

	@After
	public void mostrarDados() {
		System.out.println(endereco);
	}
}
