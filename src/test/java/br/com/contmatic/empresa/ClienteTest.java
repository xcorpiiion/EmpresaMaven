package br.com.contmatic.empresa;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Produtos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

	private Double dinheiroCarteira;

	private List<Produtos> carrinhoProduto = new ArrayList<Produtos>();

	private List<Produtos> produto = new ArrayList<Produtos>();

	private SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");

	private Date data;

	private List<Cliente> cliente = new ArrayList<Cliente>();

	private Empresa loja;

	@Before
	public void addDadosProduto() {
		produto.add(new Produtos("Tablet", 25.00, 50));
		produto.add(new Produtos("Computador", 3500.00, 70));
		produto.add(new Produtos("Smartphone", 2500.00, 150));
		produto.add(new Produtos("Fone de Ouvido", 50.00, 200));

	}

	@Before
	public void cadastrar_empresa() {
		List<Produtos> prod = new ArrayList<>();
		prod.add(new Produtos("sla", 250.00, 5));
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
	}

	@Before
	public void addDadosCliente() {
		try {
			data = nascimento.parse("19/10/1992");
			cliente.add(new Cliente("Matheus", "matheus@gmail.com", 25000.00, data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("20/11/1999");
			cliente.add(new Cliente("Vergil", "vergil@gmail.com", 1500.00, data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("9/1/1992");
			cliente.add(new Cliente("Dante", "dante@gmail.com", 900.00, data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("19/9/1996");
			cliente.add(new Cliente("Harry", "harry@gmail.com", 1300.00, data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, "Tablet", 2);
		
		cliente.get(0).cadastrarCliente("Matheus", "matheus@gmail.com", 250.00, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
	}

	@Before
	public void addDinheiroCarteira() {
		this.dinheiroCarteira = 100000.00;
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

	@Test
	public void nao_deve_aceitar_numero_estoque_maior_que_numero_estoque_produtos() {
		cliente.get(0).addItensCarrinho(cliente.get(0), this.loja, "Tablet", 5);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_numero_estoque_maior_que_numero_estoque_produtos_2() throws Exception {
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, "Tablet", 51);
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_produto_null_() {
		produto = null;
		if (produto == null) {
			throw new NullPointerException("O produto está null");
		}
	}

	@Test()
	public void nao_deve_aceitar_cliente_null() {
		assertNotNull("O cliente esta null", cliente);
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cliente_null_2() {
		cliente = null;
		if(cliente == null) {
			throw new NullPointerException();
		}
	}

	@Test()
	public void deve_existir_produto_na_lista_2() {
		String nome = "Tablet";
		assertTrue("O produto não existe", cliente.get(0).produtoEstaNoCarrinho(nome, cliente.get(0)));
	}

	@Test()
	public void dinheiro_deve_ser_mais_do_que_total_do_valor_dos_produtos_2() {
		dinheiroCarteira = 0.0;
		double totalPreco = 0.0;
		for (Produtos prod : carrinhoProduto) {
			totalPreco += prod.getPreco();
		}
		assertTrue("Você não possui dinheiro suficiente", dinheiroCarteira >= totalPreco);
	}

	@Test()
	public void nao_deve_existir_cliente_com_os_mesmos_dados() {
		String nome = "a";
		String email = "matheus@gmail.com";
		assertFalse("O cliente já está cadastrado", cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email)));
	}

	@Test()
	public void nao_deve_existir_cliente_com_os_mesmos_dados_2() throws Exception {
		String nome = "a";
		String email = "matheus@gmail.com";
		assertFalse("O cliente já está cadastrado", cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email)));
	}
	
	@Test()
	public void dataNascimento_deve_ser_valida() {
		try {
			data = nascimento.parse("01/01/1999");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cliente.get(0).setDataNascimento(data);
	}

	@Test(expected = ParseException.class)
	public void dataNascimento_deve_ser_valida_2() throws ParseException {
		data = nascimento.parse("/01/1999");
		cliente.get(0).setDataNascimento(data);
	}

	@Test()
	public void deve_ter_dados_validos_para_cadastrar_cliente() {
		String nome = "lalau";
		String email = "lalau@gmail.com";
		cliente.add(new Cliente(nome, email, 2500.00, data,
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
	}

	@Test()
	public void deve_add_produto_no_carrinho() {
		String nomeProduto = "Tablet";
		int qtdProdutoAddCarrinho = 1;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
	}

	@Test(expected = RuntimeException.class)
	public void deve_add_produto_no_carrinho_2() {
		String nomeProduto = "Tablet";
		int qtdProdutoAddCarrinho = 0;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
	}
	
	@Test(expected = RuntimeException.class)
	public void deve_add_produto_no_carrinho_3() {
		String nomeProduto = "";
		int qtdProdutoAddCarrinho = 0;
		cliente.get(0).addItensCarrinho(cliente.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
	}

	@Test
	public void deve_add_dinheiro_carteira() {
		double dinheiro = 2500.00;
		cliente.get(0).addDinheiroCarteira(dinheiro);
	}

	@Test(expected = RuntimeException.class)
	public void deve_add_dinheiro_carteira_2() {
		double dinheiro = 0;
		cliente.get(0).addDinheiroCarteira(dinheiro);
	}
	
	@Test()
	public void deve_existir_cliente() {
		String nome = "matheus";
		String email = "matheus@gmail.com";
		assertTrue("Cliente não existe", cliente.get(0).clienteExiste(loja, nome, email));
	}

	@Test
	public void deve_compra_produto() {
		String nomeProduto = "Tablet";
		int qtdProdutosCompra = 1;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test(expected = RuntimeException.class)
	public void deve_compra_produto_2() {
		String nomeProduto = "";
		int qtdProdutosCompra = 50;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test(expected = RuntimeException.class)
	public void deve_compra_produto_3() {
		String nomeProduto = "Tablet";
		int qtdProdutosCompra = 25;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test(expected = RuntimeException.class)
	public void deve_compra_produto_4() {
		String nomeProduto = "Tablet";
		int qtdProdutosCompra = 500;
		cliente.get(0).compraProduto(cliente.get(0), nomeProduto, qtdProdutosCompra);
	}
	
	@Test()
	public void deve_ser_clientes_diferentes() {
		assertFalse("Os cliente são iguais", cliente.get(0).equals(cliente.get(1)));
	}
	
	@Test()
	public void deve_ser_clientes_diferentes_2() {
		cliente.get(1).setNome(cliente.get(0).getNome());
		cliente.get(1).setEmail(cliente.get(0).getEmail());
		assertFalse("Os cliente são iguais", cliente.get(0).equals(cliente.get(1)));
	}
	
	@Test()
	public void deve_ser_clientes_diferentes_3() {
		cliente.get(1).setNome(cliente.get(0).getNome());
		cliente.get(1).setEmail(cliente.get(0).getEmail());
		assertFalse("Os cliente são iguais", cliente.get(0).equals(cliente.get(1)));
	}
	
	@Test()
	public void deve_ser_clientes_diferentes_4() {
		cliente.get(1).setNome(cliente.get(0).getNome());
		cliente.get(1).setEmail(cliente.get(0).getEmail());
		assertEquals("Os clientes são igauis", cliente.get(0).hashCode(), cliente.get(1).hashCode());
	}
	
	@Test()
	public void nao_deve_aceitar_dinheiro_negativo() {
		cliente.get(0).cadastrarCliente("lucas", "lucas@gmail.com", 250.00, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
	}
	
	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_dinheiro_negativo_2() {
		cliente.get(0).cadastrarCliente("lucas", "lucas@gmail.com", -250.00, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
	}
	
	@Test()
	public void deve_conter_toString() {
		assertEquals("Os toString não são iguais", cliente.get(0), "Cliente [nome=" + cliente.get(0).getNome() + ", email=" + cliente.get(0).getEmail() + ", dataNascimento=" + cliente.get(0).getDataNascimento()
				+ ", dinheiroCarteira=" + dinheiroCarteira + ", endereco=" + cliente.get(0).getEndereco() + ", carrinhoProduto="
				+ cliente.get(0).getCarrinhoProduto() + ", produtosComprados=" + cliente.get(0).getProdutosComprados() + "]");
	}

	@AfterClass
	public static void mostrarCliente() {
		System.out.println("Cliente foi cadastrado com sucesso");
	}

}
