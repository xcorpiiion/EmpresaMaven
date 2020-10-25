package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaProduto;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.Faker;

public class ProdutoTest {

	private Produto produto;

	private Faker faker;

	@Before
	public void dadosProdutos() throws Exception {
		faker = new Faker();
		produto = criaProduto();
	}

	/* testa nome */

	@Test
	public void deve_aceitar_nome_valido() {
		final String firstName = faker.name().firstName();
		produto.setNome(firstName);
		assertEquals(firstName, produto.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_2_caracteres() {
		produto.setNome("a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_30_caracteres() {
		produto.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		produto.setNome(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_empty() {
		produto.setNome(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_vazio() {
		produto.setNome(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_null() {
		produto.setNome(null);
	}

	/* Testa preço */

	@Test
	public void deve_aceitar_preco_valido() {
		final BigDecimal ten = BigDecimal.valueOf(15.00);
		produto.setPreco(ten);
		assertEquals(ten, produto.getPreco());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_preco_menor_que_um() {
		produto.setPreco(BigDecimal.valueOf(-1.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_preco_valor_maior_que_trezentos() {
		produto.setPreco(BigDecimal.valueOf(3001));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_preco_null() {
		produto.setPreco(null);
	}

	/* Testa estoque */
	
	@Test
	public void deve_aceitar_estoque_valido() {
		final Integer numberBetween = faker.number().numberBetween(1, 30);
		produto.setEstoque(numberBetween);
		assertEquals(numberBetween, produto.getEstoque());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estoque_menor_do_que_um() {
		produto.setEstoque(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estoque_maior_que_30() {
		produto.setEstoque(31);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estoque_null() {
		produto.setEstoque(null);
	}
	
	/* Testa equals e hashcode */
	
	@Test()
	public void deve_retornar_true_no_equals_para_serem_iguais() {
		forClass(Produto.class).usingGetClass()
				.suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
	}

	@After
	public void mostrarDados() {
		System.out.println(produto);
	}

}
