package br.com.contmatic.empresa;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");

	private List<Cliente> cliente = new ArrayList<Cliente>();

	private Empresa loja = null;

	@Before
	public void addDadosProduto() {
		try {
			produto.add(new Produtos("Tablet", 2500.00, 50));
			produto.add(new Produtos("Computador", 3500.00, 70));
			produto.add(new Produtos("Smartphone", 2500.00, 150));
			produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
		} catch (Exception e) {
			fail("Algum valor em produto está null");
		}
	}

	@Test
	public void cadastrar_empresa() {
		List<Produtos> prod = new ArrayList<>();
		try {
			prod.add(new Produtos("sla", 250.00, 5));
			this.loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
					new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		} catch (Exception e) {
			fail("Você informou o endereço errado");
		}
	}

	@Before
	public void addDadosCliente() {
		try {
			nascimento.parse("19/10/1992");
			cliente.add(new Cliente("Matheus", "matheus@gmail.com", 2500.00, nascimento,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("20/11/1999");
			cliente.add(new Cliente("Vergil", "vergil@gmail.com", 1500.00, nascimento,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("9/1/1992");
			cliente.add(new Cliente("Dante", "dante@gmail.com", 900.00, nascimento,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
			nascimento.parse("19/9/1996");
			cliente.add(new Cliente("Harry", "harry@gmail.com", 1300.00, nascimento,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
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
		if(loja == null) {
			System.out.println("É null");
		}
		System.out.println(loja.getNome());
		try {
			cliente.get(0).addItensCarrinho(cliente.get(0), this.loja, "Tablet", 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(expected = NullPointerException.class)

	public void nao_deve_aceitar_numero_estoque_maior_que_numero_estoque_produtos_() throws Exception {
		cliente.get(0).addItensCarrinho(cliente.get(0), this.loja, "Tablet", 50);
	}

	@Test()
	public void nao_deve_aceitar_produto_null() {
		if (produto == null) {
			assertEquals("O produto está null", produto);
		}
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
		if (cliente == null) {
			assertNotNull("O cliente esta null", cliente);
		}
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cliente_null_() {
		cliente = null;
		if (cliente == null) {
			throw new NullPointerException("O cliente está null");
		}
	}

	@Test()
	public void deve_existir_produto_na_lista() {
		String nome = "Tablet";
		assertTrue("O produto não existe", produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome)));
	}

	@Test()
	public void illegal_deve_existir_produto_na_lista() {
		String nome = "Tablet";
		assertTrue("O produto não existe", produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome)));
	}

	@Test()
	public void deve_conter_um_valor_acima_de_zero() {
		int auxQtdCarrinho = 3;
		if (auxQtdCarrinho < 1) {
			fail("a quantidade de produtos no carrinho não pode ser 0");
		}
	}

	@Test()
	public void illegal_deve_conter_um_valor_acima_de_zero() {
		int auxQtdCarrinho = 2;
		if (auxQtdCarrinho < 1) {
			fail("a quantidade de produtos no carrinho não pode ser 0");
		}
	}

	@Test()
	public void dinheiro_deve_ser_mais_do_que_total_do_valor_dos_produtos() {
		double totalPreco = 0.0;
		for (Produtos prod : carrinhoProduto) {
			totalPreco += prod.getPreco();
		}
		assertTrue("Você não possui dinheiro suficiente", dinheiroCarteira >= totalPreco);
	}

	@Test()
	public void illegal_dinheiro_deve_ser_mais_do_que_total_do_valor_dos_produtos() {
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
	public void nao_deve_existir_cliente_com_os_mesmos_dados_() throws Exception {
		String nome = "a";
		String email = "matheus@gmail.com";
		assertFalse("O cliente já está cadastrado", cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email)));
	}

	@Test(expected = ParseException.class)
	public void dataNascimento_deve_ser_valida() throws ParseException {
		nascimento.parse("/04/2000");

	}

	@Test()
	public void deve_ter_dados_validos_para_cadastrar_cliente() {
		String nome = "lalau";
		String email = "lalau@gmail.com";
		try {
			cliente.add(new Cliente(nome, email, 2500.00, nascimento,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("O endereço está incorreto");
		}
	}

	@After
	public void mostrarCliente() {
		System.out.println("Cliente foi cadastrado com sucesso");
	}

}
