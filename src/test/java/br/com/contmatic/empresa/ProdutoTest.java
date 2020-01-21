package br.com.contmatic.empresa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProdutoTest {

	private String nome;

	private Double preco;

	private Integer estoque;
	
	private Produtos produto;
	
	@Before
	public void dadosProdutos() throws Exception {
		nome = "";
		preco = 0.0;
		estoque = 0;
		produto = new Produtos(nome, preco, estoque);
	}

	@Test
	public void deve_aceitar_nome_nao_null() throws Exception {
		produto.setNome(nome);
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_null() throws Exception {
		produto.setNome(nome);
	}

	@Test(expected = Exception.class)
	public void nao_deve_aceitar_nome_vazio() throws Exception {
		produto.setNome(nome);
	}
	
	@Test
	public void deve_aceitar_nome_nao_vazio() throws Exception {
		produto.setNome(nome);
	}

	@Test
	public void ndeve_aceitar_preco_nao_null() throws Exception {
		produto.setPreco(preco);
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_preco_null() throws Exception {
		produto.setPreco(preco);
	}

	@Test
	public void deve_aceitar_estoque_nao_nulo() throws Exception {
		produto.setEstoque(estoque);
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_estoque_null() throws Exception {
		produto.setEstoque(null);
	}
	
	@Test
	public void deve_aceitar_estoque_que_nao_seja_negativo() throws Exception {
		produto.setEstoque(estoque);
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_estoque_negativo() throws Exception {
		produto.setEstoque(-1);
	}
	
	@After
	public void mostrarDados() {
		System.out.println("Nome do produto: " + produto.getNome());
		System.out.println("Nome do produto: " + produto.getPreco());
		System.out.println("Nome do produto: " + produto.getEstoque());
	}

}
