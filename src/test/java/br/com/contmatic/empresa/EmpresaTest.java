package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaCliente;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEmpresa;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEndereco;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaProduto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
public class EmpresaTest {

	private static Produto produto;

	private Funcionario funcionario;

	private static Cliente cliente;

	private static Endereco endereco;

	private static Empresa empresa;

	private static Faker faker;

	@BeforeClass
	public static void cadastraEmpresa() {
		faker = new Faker();
		produto = criaProduto();
		endereco = criaEndereco();
		cliente = criaCliente(endereco);
		empresa = criaEmpresa(endereco, false);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_3_caracteres(){
		empresa.setNome("aa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_70_caracteres(){
		empresa.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_null(){
		empresa.setNome(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_empty() {
		empresa.setNome(StringUtils.EMPTY);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio() {
		empresa.setNome(StringUtils.SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_invalido() {
		empresa.setCnpj("1");
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cnpj_null() {
		empresa.setCnpj(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco() {
		StringBuilder cnpj = new StringBuilder();
		cnpj.append(String.valueOf(faker.number().numberBetween(1, 10))).append(" ");
		empresa.setCnpj(cnpj.toString());
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_vazio() {
		String cnpj = "";
		empresa.setCnpj(cnpj);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_com_espaco_em_branco() {
		String cnpj = " ";
		empresa.setCnpj(cnpj);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_com_letras() {
		String cnpj = "a";
		empresa.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_com_letras_e_numeros_juntos() {
		String cnpj = "1234567890123a";
		empresa.setCnpj(cnpj);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_com_menos_14_numeros() {
		String cnpj = "1234567890123";
		empresa.setCnpj(cnpj);
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_email_null() {
		empresa.setEmail(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_email_vazio_empty() {
		empresa.setEmail(StringUtils.SPACE);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_email_vazio() {
		empresa.setEmail(StringUtils.EMPTY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_sem_arroba() {
		empresa.setEmail(faker.name().firstName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_com_caracteres_especiais() {
		empresa.setEmail(faker.name().firstName() + "%$");
	}

	@Test
	public void deve_criar_empresa_com_produtos() {
		empresa = criaEmpresa(endereco, true);
		assertFalse(empresa.getProduto().isEmpty());
	}

	@Test()
	public void nao_deve_aceitar_produto_null() {
		assertNotNull("O produto esta null", produto);
	}

	@Test
	public void deve_settar_produto() {
		empresa.setProduto(Arrays.asList(produto));
		assertFalse(empresa.getProduto().isEmpty());
	}

	@Test
	public void deve_settar_o_funcionario() {
		empresa.setFuncionario(Arrays.asList(funcionario));
		assertFalse(empresa.getFuncionario().isEmpty());
	}

	@Test
	public void deve_settar_o_cliente() {
		empresa.setCliente(Arrays.asList(cliente));
		assertFalse(empresa.getCliente().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_endereco_null() {
		assertNotNull(empresa.getEndereco());
	}

	@Test
	public void deve_settar_data_criacao() {
		Date past = faker.date().past(1, TimeUnit.DAYS);
		empresa.setDataCriacao(past);
		assertEquals(past, empresa.getDataCriacao());
	}

	@Test
	public void deve_settar_data_alteracao() {
		Date past = faker.date().past(1, TimeUnit.DAYS);
		empresa.setDataAlteracao(past);
		assertEquals(past, empresa.getDataAlteracao());
	}

	@Test()
	public void deve_retornar_true_no_equals_para_serem_iguais() {
		EqualsVerifier.forClass(Empresa.class).usingGetClass()
				.suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
	}

	@AfterClass
	public static void mostrar_dados_empresa() {
		System.out.println(empresa);
	}

}
