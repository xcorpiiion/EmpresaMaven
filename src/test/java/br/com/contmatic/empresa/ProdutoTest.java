package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.services.EmptyStringException;

public class ProdutoTest {

	private String nome;

	private BigDecimal preco;

	private Integer estoque;
	
	private Produto produto;
	
	private Produto produto2;
	
	@Before
	public void dadosProdutos() throws Exception {
		nome = "Tablet";
		preco = new BigDecimal(250.00);
		estoque = 5;
		produto = new Produto(nome, preco, estoque);
	}

	@Test
	public void deve_aceitar_nome_nao_null() {
		nome = "Tablet";
		produto.setNome(nome);
		assertThat(produto.getNome(), is("Tablet"));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_null() {
		produto.setNome(null);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio() {
		nome = " ";
		produto.setNome(nome);
	}
	
	@Test
	public void deve_aceitar_nome_nao_vazio() {
		produto.setNome("Tablet");
		assertThat(produto.getNome(), is("Tablet"));
		
	}

	@Test
	public void ndeve_aceitar_preco_nao_null() {
		produto.setPreco(new BigDecimal(250.00));
		assertThat(produto.getPreco(), is(250.00));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_preco_null() {
		produto.setPreco(null);
	}

	@Test
	public void deve_aceitar_estoque_nao_nulo() {
		produto.setEstoque(5);
		assertThat(produto.getEstoque(), is(5));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_estoque_null() {
		produto.setEstoque(null);
	}
	
	@Test
	public void deve_aceitar_estoque_que_nao_seja_negativo() {
		produto.setEstoque(5);
		assertThat(produto.getEstoque(), is(5));
	}
	
	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_estoque_negativo() {
		produto.setEstoque(-1);
	}
	
	@Test()
	public void nao_deve_aceitar_produtos_iguais() {
		assertTrue("Os produtos são iguais", produto.equals(produto));
	}
	
	@Test()
	public void nao_deve_aceitar_produtos_iguais_2() {
		assertFalse("Os produtos são iguais", produto.equals(null));
	}
	
	@Test()
	public void nao_deve_aceitar_produtos_iguais_3() {
		produto2 = new Produto("a", new BigDecimal(250.00), 10);
		assertFalse("Os produtos são iguais", produto.equals(produto2));
	}
	
	@Test()
	public void nao_deve_aceitar_produtos_iguais_4() {
		assertEquals("Os produtos são iguais", produto.hashCode(), produto.hashCode());
	}
	
	
	@After
	public void mostrarDados() {
		System.out.println(produto);
	}

}
