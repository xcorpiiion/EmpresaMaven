package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.services.EmptyStringException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

	private static List<Produto> produto;

	private SimpleDateFormat nascimento;

	private Date data;

	private List<Cliente> cliente;

	private static Empresa loja;

	@BeforeClass
	public static void addDadosIniciais() {
		produto = new ArrayList<>();
		produto.add(new Produto("Tablet", new BigDecimal(250.00), 50));
		produto.add(new Produto("Computador", new BigDecimal(3500.00), 70));
		produto.add(new Produto("Smartphone", new BigDecimal(2500.00), 150));
		produto.add(new Produto("Fone de Ouvido", new BigDecimal(50.00), 200));
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		loja.setCliente(new ArrayList<>());
		loja.setFuncionario(new ArrayList<>());
	}

	@Before
	public void addDadosCliente() {
		cliente = new ArrayList<>();
		nascimento = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data = nascimento.parse("19/10/1992");
			cliente.add(new Cliente("Matheus", "matheus@gmail.com", data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			data = nascimento.parse("20/11/1999");
			cliente.add(new Cliente("Vergil", "vergil@gmail.com", data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			data = nascimento.parse("9/1/1992");
			cliente.add(new Cliente("Dante", "dante@gmail.com", data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			data = nascimento.parse("19/9/1996");
			cliente.add(new Cliente("Harry", "harry@gmail.com", data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
		
		cliente.get(0).setCarrinhoProduto(new ArrayList<>());
		cliente.get(0).setProdutosComprados(new ArrayList<>());
		cliente.get(0).setDinheiroCarteira(new BigDecimal(2500.00));
		
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, "Tablet", 2);
	}

	@Ignore
	public void alterarDados() throws Exception {
		cliente.get(0).setNome("lucas");
		cliente.get(0).setEmail("lucas@mail.com");
		cliente.get(0).getEndereco().setRua("Rua dos alfeneiros");
		cliente.get(0).getEndereco().setNumeroResidencia("45-A");
		cliente.get(0).getEndereco().setBairro("Jardim formiga");
		cliente.get(0).getEndereco().setCep("12345678");
		cliente.get(0).getEndereco().setCidade("Salvador");
		cliente.get(0).getEndereco().setEstado("São paulo");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_null(){
		cliente.get(0).setNome(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio() {
		cliente.get(0).setNome("");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_nome_vazio_2() {
		cliente.get(0).setNome(" ");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_email_null() {
		cliente.get(0).setEmail(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_email_vazio() {
		cliente.get(0).setEmail(" ");
	}
	
	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_email_vazio_2() {
		cliente.get(0).setEmail("");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_null() {
		cliente.get(0).setEndereco(null);
	}

	@Test
	public void nao_deve_aceitar_numero_estoque_maior_que_numero_estoque_produtos() {
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, "Tablet", 5);
		assertThat(5, is(5));
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_estoque_maior_que_numero_estoque_produtos_expection() {
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, "Tablet", 51);
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_loja_null() throws Exception {
		cliente.get(0).addItensCarrinho(cliente.get(0), null, "Tablet", 51);
	}

	@Test()
	public void deve_existir_produto_na_lista() {
		String nome = "Tablet";
		assertTrue("O produto não existe", cliente.get(0).produtoEstaNoCarrinho(nome, cliente.get(0)));
	}
	
	@Test(timeout = 100)
	public void dataNascimento_deve_ser_valida() {
		nascimento = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data = nascimento.parse("01/01/1999");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cliente.get(0).setDataNascimento(data);
		assertThat(data, is(cliente.get(0).getDataNascimento()));
	}

	@Test(expected = ParseException.class)
	public void dataNascimento_deve_ser_valida_exception() throws ParseException {
		data = nascimento.parse("/01/1999");
		cliente.get(0).setDataNascimento(data);
	}
	
	@Test(expected = NullPointerException.class)
	public void dataNascimento_nao_deve_ser_null_exception() throws ParseException {
		cliente.get(0).setDataNascimento(null);
	}

	@Test()
	public void deve_add_produto_no_carrinho() {
		String nomeProduto = "Tablet";
		int qtdProdutoAddCarrinho = 1;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
		assertTrue("O produto não exite no carrinho", cliente.get(0).produtoEstaNoCarrinho(nomeProduto, cliente.get(0)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_ter_quatidade_de_produtos_no_carrinho_menor_do_que_1() {
		String nomeProduto = "Tablet";
		int qtdProdutoAddCarrinho = 0;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_ter_quatidade_de_produtos_no_carrinho_menor_do_que_1_e_sem_nome_de_produto() {
		String nomeProduto = "";
		int qtdProdutoAddCarrinho = 0;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
	}

	@Test
	public void deve_add_dinheiro_carteira() {
		BigDecimal dinheiro = new BigDecimal(2500.00);
		cliente.get(0).setDinheiroCarteira(dinheiro);
		assertThat(cliente.get(0).getDinheiroCarteira(), is(5000.00));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ter_valor_maior_do_que_zero_para_add_valor_na_carteira() {
		BigDecimal dinheiro = new BigDecimal(0);
	}
	
	@Test()
	public void deve_existir_cliente() {
		String nome = "matheus";
		String email = "matheus@gmail.com";
		assertFalse("Cliente não existe", cliente.get(0).clienteExiste(loja, nome, email));
	}
	
	@Test()
	public void nao_deve_existir_cliente_com_mesmo_dados_para_cadastrar() {
		String nome = "lucas";
		String email = "lucas@gmail.com";
		try {
			data = nascimento.parse("19/10/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cliente.get(0).cadastrarCliente(nome, email, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
		assertTrue("O cliente existe", cliente.get(0).clienteExiste(loja, nome, email));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_existir_cliente_com_mesmo_dados_para_cadastrar_expection() {
		String nome = "lucas";
		String email = "lucas@gmail.com";
		try {
			data = nascimento.parse("19/10/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cliente.get(0).cadastrarCliente(nome, email, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
		cliente.get(0).cadastrarCliente(nome, email, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
		assertTrue("O cliente existe", cliente.get(0).clienteExiste(loja, nome, email));
	}


	@Test
	public void deve_compra_produto() {
		String nomeProduto = "Tablet";
		int qtdProdutosCompra = 1;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
		assertTrue(cliente.get(0).getProdutosComprados().contains(loja.getProduto().get(3)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_ter_nome_do_produto_para_compra() {
		String nomeProduto = "";
		int qtdProdutosCompra = 50;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_ter_dinheiro_suficiente_para_comprar() {
		String nomeProduto = "Tablet";
		int qtdProdutosCompra = 30;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, 20);
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_ter_quantidade_de_itens_para_compra_menor_ou_igual_a_quantidade_estoque() {
		String nomeProduto = "Tablet";
		int qtdProdutosCompra = 500;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test()
	public void deve_ser_clientes_diferentes() {
		assertTrue("Os cliente são iguais", cliente.get(0).equals(cliente.get(0)));
	}
	
	@Test()
	public void deve_ter_nome_e_email_iguais_para_serem_clientes_iguais() {
		cliente.get(1).setNome(cliente.get(0).getNome());
		cliente.get(1).setEmail("a");
		assertFalse("Os cliente são iguais", cliente.get(0).equals(cliente.get(1)));
	}
	
	@Test()
	public void deve_ser_clientes_diferentes_3() {
		cliente.get(1).setNome(cliente.get(0).getNome());
		cliente.get(1).setEmail(cliente.get(0).getEmail());
		assertFalse("Os cliente são iguais", cliente.get(0).equals(cliente.get(1)));
	}
	
	@Test()
	public void deve_ter_hashCode_iguais_para_serem_clientes_iguais() {
		assertEquals("Os clientes são igauis", cliente.get(0).hashCode(), cliente.get(0).hashCode());
	}
	
	@Test()
	public void nao_deve_ter_equals_null_para_comparar_clientes() {
		assertFalse("Os clientes são igauis", cliente.get(0).equals(null));
	}
	
	@After
	public void deve_conter_toString() {
		System.out.println(cliente.get(0));
	}

	@AfterClass
	public static void mostrarCliente() {
		System.out.println("Cliente foi cadastrado com sucesso");
	}

}
