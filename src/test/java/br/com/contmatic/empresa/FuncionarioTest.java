package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEndereco;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaFuncionario;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.javafaker.Faker;

import br.com.contmatic.services.EmptyStringException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

	private static Funcionario funcionario;

	private static Endereco endereco;

	private static Faker faker;

	@BeforeClass
	public static void addDadosIniciais() {
		faker = new Faker();
		endereco = criaEndereco();
		funcionario = criaFuncionario(endereco);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_letras(){
		funcionario.setNome("aaa45");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_3_caracteres(){
		funcionario.setNome("aa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_30_caracteres(){
		funcionario.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_null(){
		funcionario.setNome(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_empty() {
		funcionario.setNome(StringUtils.EMPTY);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio() {
		funcionario.setNome(StringUtils.SPACE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		funcionario.setNome("#$@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido() {
		funcionario.setCpf("1");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cpf_null() {
		funcionario.setCpf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_null_com_letras_com_espaco() {
		StringBuilder cpf = new StringBuilder();
		cpf.append(String.valueOf(faker.number().numberBetween(1, 10))).append(" ");
		funcionario.setCpf(cpf.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_vazio() {
		String cpf = StringUtils.EMPTY;
		funcionario.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_espaco_em_branco() {
		funcionario.setCpf(StringUtils.SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_letras() {
		String cpf = "a123854120";
		funcionario.setCpf(cpf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_letras_e_numeros_juntos() {
		String cpf = "1234567890123a";
		funcionario.setCpf(cpf);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_retorna_exception_caso_salario_seja_menor_que_zero() {
		funcionario.setSalario(BigDecimal.valueOf(-1));
	}

	public void deve_possui_salario_maior_que_zero() {
		funcionario.setSalario(BigDecimal.valueOf(faker.number().randomDouble(2, 600, 800)));
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_email_null() {
		funcionario.setEmail(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio_empty() {
		funcionario.setEmail(StringUtils.SPACE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio() {
		funcionario.setEmail(StringUtils.EMPTY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_sem_arroba() {
		funcionario.setEmail(faker.name().firstName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_com_caracteres_especiais() {
		funcionario.setEmail(faker.name().firstName() + "%$");
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_null() {
		funcionario.setEndereco(null);
	}

	@Test(expected = NullPointerException.class)
	public void dataNascimento_nao_deve_ser_null_exception() {
		funcionario.setDataNascimento(null);
	}

	@Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        EqualsVerifier.forClass(Funcionario.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }
	
	@AfterClass
	public static void imprimirFuncionario() {
		System.out.println(funcionario);
	}
	
}
