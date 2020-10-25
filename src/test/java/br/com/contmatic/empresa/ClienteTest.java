package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaCliente;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEndereco;
import static br.com.contmatic.services.utils.GeradorCpf.gerardorRandomCpf;
import static java.util.concurrent.TimeUnit.DAYS;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.github.javafaker.Faker;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

	private static Cliente cliente;

	private static Endereco endereco;

	private static Faker faker;

	@BeforeClass
	public static void addDadosIniciais() {
		endereco = criaEndereco();
		cliente = criaCliente(endereco);
		faker = new Faker();
	}

	/* testa nome */

	@Test
	public void deve_aceitar_nome_valido() {
		final String firstName = faker.name().firstName();
		cliente.setNome(firstName);
		assertEquals(firstName, cliente.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_3_caracteres() {
		cliente.setNome("aa");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_30_caracteres() {
		cliente.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_numero() {
		cliente.setNome(faker.name().firstName() + "1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		cliente.setNome(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_empty() {
		cliente.setNome(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_vazio() {
		cliente.setNome(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_null() {
		cliente.setNome(null);
	}

	/* Teste cpf */

	@Test
	public void deve_aceitar_cpf_valido() {
		final String cpfValido = gerardorRandomCpf();
		cliente.setCpf(cpfValido);
		assertEquals(cpfValido, cliente.getCpf());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_menos_11_numeros() {
		String cpf = "1234567890123";
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_mais_11_numeros() {
		String cpf = "123456789012345";
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_letras() {
		String cpf = "a";
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_letras_e_numeros_juntos() {
		String cpf = "1234567890123a";
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_espaco_entre_numeros() {
		StringBuilder cpf = new StringBuilder();
		cpf.append(faker.number().numberBetween(1, 10)).append(" ");
		cliente.setCpf(cpf.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_vazio() {
		String cpf = EMPTY;
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_espaco_em_branco() {
		String cpf = SPACE;
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_null() {
		cliente.setCpf(null);
	}

	/* Testa email */

	@Test
	public void deve_aceitar_email_valido() {
		final String emailAddress = faker.internet().emailAddress();
		cliente.setEmail(emailAddress);
		assertEquals(emailAddress, cliente.getEmail());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_sem_arroba() {
		cliente.setEmail(faker.name().firstName());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_com_caracteres_especiais() {
		cliente.setEmail(faker.name().firstName() + "%$");
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_com_espaco_entre_palavras() {
		cliente.setEmail("aa " + faker.internet().emailAddress());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_sem_ponto() {
		cliente.setEmail("a@gmail");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio_empty() {
		cliente.setEmail(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio() {
		cliente.setEmail(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_null() {
		cliente.setEmail(null);
	}

	/* Teste endereço */

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_endereco_null() {
		cliente.setEndereco(null);
	}

	/* testa data de nascimento */

	@Test
	public void deve_settar_data_nascimento() {
		DateTime past = new DateTime(faker.date().past(1, DAYS));
		cliente.setDataNascimento(past);
		assertEquals(past, cliente.getDataNascimento());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_de_nascimento_antes_de_1920() {
		DateTime past = new DateTime(1919, 1, 1, 0, 0);
		cliente.setDataNascimento(past);
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_nascimento_depois_da_hora_atual() {
		DateTime past = new DateTime(2050, 1, 1, 0, 0);
		cliente.setDataNascimento(past);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_nascimento_null() {
		cliente.setDataNascimento(null);
	}

	/* Testa data cadastro */

	@Test
	public void deve_settar_data_cadastro() {
		DateTime past = new DateTime(faker.date().past(1, DAYS));
		cliente.setCadastro(past);
		assertEquals(past, cliente.getCadastro());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_cadastro_antes_de_1998() {
		DateTime past = new DateTime(1997, 1, 1, 0, 0);
		cliente.setCadastro(past);
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_cadastro_depois_da_hora_atual() {
		DateTime past = new DateTime(2050, 1, 1, 0, 0);
		cliente.setCadastro(past);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_cadastro_null() {
		cliente.setCadastro(null);
	}

	/* Testa equals e hashcode */

	@Test()
	public void deve_retornar_true_no_equals_para_serem_iguais() {
		forClass(Cliente.class).usingGetClass()
				.suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
	}

	@After
	public void deve_conter_toString() {
		System.out.println(cliente);
	}

}
