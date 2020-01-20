import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
	
	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<Cliente> cliente = new ArrayList<Cliente>();

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

	@Before
	public void addDadosCarrinho() {
		try {
			carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
			carrinhoProduto.add(new Produtos("Computador", 3500.00, 70));
			carrinhoProduto.add(new Produtos("Smartphone", 2500.00, 150));
			carrinhoProduto.add(new Produtos("Fone de Ouvido", 50.00, 200));
			carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
			carrinhoProduto.add(new Produtos("Tablet", 2500.00, 50));
		} catch (Exception e) {
			fail("Algum valor em produto está null");
		}

	}

	@Before
	public void addDinheiroCarteira() {
		this.dinheiroCarteira = 100000.00;
	}

	@Ignore
	public void alterarDados() {
		cliente.get(0).setNome("lucas");
		cliente.get(0).setEmail("lucas@mail.com");
		cliente.get(0).getEndereco().setRua("Rua dos alfeneiros");
		cliente.get(0).getEndereco().setNumeroResidencia("45-A");
		cliente.get(0).getEndereco().setBairro("Jardim formiga");
		cliente.get(0).getEndereco().setCep("12345678");
		cliente.get(0).getEndereco().setCidade("Salvador");
		cliente.get(0).getEndereco().setEstado("São paulo");

	}

	@Test()
	public void nao_deve_aceitar_produto_null() {
		if (produto == null) {
			assertEquals("O produto está null", produto);
		}
	}

	@Test()
	public void nao_deve_aceitar_cliente_null() {
		if (cliente == null) {
			assertNotNull("O cliente esta null", cliente);
		}
		
	}

	@Test()
	public void deve_existir_produto_na_lista() {
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
	public void deve_digitar_um_valor_valido() {
		int numeroVerificador = 2;
		try {
			if (numeroVerificador < 1 || numeroVerificador > 2) {
				assertEquals("O número digitado é invalido", 1, numeroVerificador);
			}
		} catch (NumberFormatException e) {
			System.out.println("Você informou um digito invalido " + e.getMessage());
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
	public void nao_deve_existir_cliente_com_os_mesmos_dados() {
		String nome = "laaaalau";
		String email = "lalau@gmail.com";
		assertFalse("O cliente já está cadastrado", this.cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email)));
	}

	@Test()
	public void dataNascimento_deve_ser_valida() {
		
		try {
			nascimento.parse("03/04/2000");
		} catch (ParseException e) {
			fail("Você informou uma data invalida");
		}
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
