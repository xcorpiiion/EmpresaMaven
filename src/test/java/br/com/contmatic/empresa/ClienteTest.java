package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaCliente;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEndereco;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.javafaker.Faker;

import br.com.contmatic.services.EmptyStringException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_letras(){
		cliente.setNome("aaa45");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_3_caracteres(){
		cliente.setNome("aa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_30_caracteres(){
		cliente.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_null(){
		cliente.setNome(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_empty() {
		cliente.setNome(StringUtils.SPACE);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio() {
		cliente.setNome(StringUtils.EMPTY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		cliente.setNome("#$@");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_email_null() {
		cliente.setEmail(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio_empty() {
		cliente.setEmail(StringUtils.SPACE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio() {
		cliente.setEmail(StringUtils.EMPTY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_sem_arroba() {
		cliente.setEmail(faker.name().firstName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_com_caracteres_especiais() {
		cliente.setEmail(faker.name().firstName() + "%$");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_null() {
		cliente.setEndereco(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void dataNascimento_nao_deve_ser_null_exception() {
		cliente.setDataNascimento(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido() {
		cliente.setCpf("1");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cpf_null() {
		cliente.setCpf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_null_com_letras_com_espaco() {
		StringBuilder cpf = new StringBuilder();
		cpf.append(String.valueOf(faker.number().numberBetween(1, 10))).append(" ");
		cliente.setCpf(cpf.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_vazio() {
		String cpf = StringUtils.EMPTY;
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_espaco_em_branco() {
		cliente.setCpf(StringUtils.SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_letras() {
		String cpf = "a123854120";
		cliente.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_letras_e_numeros_juntos() {
		String cpf = "1234567890123a";
		cliente.setCpf(cpf);
	}
	
	@Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        EqualsVerifier.forClass(Cliente.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }
	
	@After
	public void deve_conter_toString() {
		System.out.println(cliente);
	}

}
