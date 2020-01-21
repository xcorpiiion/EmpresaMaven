package br.com.contmatic.empresa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produtos;
import enums.Cargo;
import enums.TipoContrato;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");

	private static List<Produtos> produto = new ArrayList<Produtos>();

	private List<Cliente> cliente = new ArrayList<Cliente>();

	private List<Funcionario> funcionario = new ArrayList<Funcionario>();

	private static Empresa loja = null;

	@BeforeClass
	public static void cadastrar_empresa() {
		List<Produtos> prod = new ArrayList<>();
		try {
			prod.add(new Produtos("sla", 250.00, 5));
			loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
					new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		} catch (Exception e) {
			fail("Você informou o endereço errado");
		}
	}

	@Before
	public void add_dados_funcionario() {
		try {
			nascimento.parse("03/07/1992");
			funcionario.add(new Funcionario("Lucas", "lucas@gmail.com", 2500.00, Cargo.RH, nascimento, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("09/04/1990");
			funcionario.add(
					new Funcionario("João", "joao@gmail.com", 2000.00, Cargo.REPOSITOR, nascimento, TipoContrato.CLT,
							new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("03/02/1985");
			funcionario.add(new Funcionario("Weevil", "weevil@gmail.com", 1500.00, Cargo.REPOSITOR, nascimento,
					TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("26/01/1989");
			funcionario.add(new Funcionario("Dante", "dante@gmail.com", 1200.00, Cargo.RH, nascimento, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}
	}

	@Before
	public void add_dados_cliente() {
		try {
			nascimento.parse("19/10/1992");
			cliente.add(new Cliente("Matheus", "matheus@gmail.com", 2500.00, nascimento,
					new Endereco("Rua almeida", "Jardim santana", "02675000", "35-A", "São paulo", "São Paulo")));
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
	public void add_dados_produto() {
		try {
			produto.add(new Produtos("Tablet", 2500.00, 50));
			produto.add(new Produtos("Smartphone", 2500.00, 150));
			produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
			produto.add(new Produtos("Computador", 3500.00, 70));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test()
	public void nao_deve_aceitar_produto_null() {
		assertNotNull("O produto esta null", produto);
	}

	@Test()
	public void nao_deve_aceitar_produto_vazio() {
		String nomeProduto = "tablet";
		assertTrue("O produto não existe",
				produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)));
	}

	@Test()
	public void deve_existir_cliente_para_fazer_login() {
		String nome = "matheus";
		String email = "matheus@gmail.com";
		assertTrue("O cliente não existe", (cliente.stream().anyMatch(
				clien -> clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email))));
	}

	@Test()
	public void deve_existir_funcionario_para_fazer_login() {
		String nome = "lucas";
		String email = "lucas@gmail.com";
		assertTrue("O funncionario não existe", (funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))));
	}

	@Test
	public void nao_deve_aceitar_cnpj_null_ou_vazio() {
		String cnpj = "1";
		assertFalse("O cnpj não é valido", cnpj == null || cnpj.isEmpty() || cnpj.trim().equals(""));
	}

	@Test
	public void nao_deve_aceitar_letras() {
		String cnpj = "12345678901234";
		if (cnpj.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
			fail("O cnpj está errado");
		} else if (cnpj.matches("^[0-9]*$")) {
		} else {
			fail("O cnpj está errado");
		}
	}

	@Test
	public void cnpj__deve_conter_14_numeros() {
		String cnpj = "12345678901234";
		if (cnpj.length() != 14) {
			fail("O cnpj está errado");
		}
	}

	@Test
	public void nao_deve_aceitar_nome_null_ou_vazio() {
		String nome = "Lucas";
		if (nome == null || nome.isEmpty() || nome.trim().equals("")) {
			fail("O nome está vazio ou null");
		}
	}

	@Test
	public void nao_deve_aceitar_endereco_null() {
		assertNotNull(funcionario.get(0).getEndereco());
	}

	@After
	public void ordena_os_produtos() {
		produto.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
	}

	@AfterClass
	public static void mostrar_dados_empresa() {
		System.out.println(loja);
	}

}
