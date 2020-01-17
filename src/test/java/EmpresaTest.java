
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.empresa.Empresa;
import br.com.empresa.Endereco;
import br.com.empresa.Produtos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {
	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");
	private static List<Produtos> produto = new ArrayList<Produtos>();
	private String nome = "";
	private static Empresa loja = null;

	@BeforeClass
	public static void cadastrarEmpresa() {
		List<Produtos> prod = new ArrayList<>();
		try {
			prod.add(new Produtos("sla", 250.00, 5));
			loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
					new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Você informou o endereço errado");
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
			// TODO Auto-generated catch block
			fail("Algum valor está null no produto");
		}

	}
	
	@Test()
	public void verificarProdutoExiste() {
		if (produto == null) {
			assertThat("O produto esta null", is(produto));
		}

		// Verifica toda a lista e vai retorna true se existir um nome e email na lista
		System.out.print("Informe o nome do produto: ");
		Scanner scanner = new Scanner(System.in);
		nome = scanner.nextLine();
		assertTrue("O produto não exite", produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome)));
		scanner.close();
	}

	@After
	public void mostrarDadosNaTela() {
		System.out.println("Lista de produtos da loja");
		produto.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
		produto.forEach(System.out::println);
	}

	@AfterClass
	public static void mostrarDadosEmpresa() {
		System.out.println(loja);
	}

}
