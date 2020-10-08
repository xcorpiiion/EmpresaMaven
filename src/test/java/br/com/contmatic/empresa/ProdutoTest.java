package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.services.EmptyStringException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ProdutoTest {

	private String nome;

	private BigDecimal preco;

	private Integer estoque;
	
	private Produto produto;
	
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

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_dois_caracteres(){
		produto.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_30_caracteres(){
		produto.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_empty() {
		produto.setNome(StringUtils.EMPTY);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio() {
		produto.setNome(StringUtils.SPACE);
	}
	
	@Test
	public void ndeve_aceitar_preco_nao_null() {
		produto.setPreco(new BigDecimal(250));
		assertThat(produto.getPreco(), is(new BigDecimal(250)));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_preco_null() {
		produto.setPreco(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_valor_menor_que_um() {
		produto.setPreco(BigDecimal.ZERO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_valor_maior_que_trezentos() {
		produto.setPreco(BigDecimal.valueOf(301));
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
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estoque_negativo() {
		produto.setEstoque(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_estoque_maior_que_30() {
		produto.setEstoque(31);
	}
	
	@Test()
	public void nao_deve_aceitar_valor_estoque_entre_1_e_30() {
		produto.setEstoque(30);
		assertEquals(Integer.valueOf(30), produto.getEstoque());
	}
	
	@Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        EqualsVerifier.forClass(Produto.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }
	
	@After
	public void mostrarDados() {
		System.out.println(produto);
	}

}
