import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import entities.Cliente;
import entities.Funcionario;
import entities.Empresa;
import entities.Produtos;
import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

public class LojaTest {
	private String nome, email;
	private MotivoDemissao motivoDemissao;
	private TipoContrato tipoContrato;
	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private List<Cliente> cliente = new ArrayList<Cliente>();

	@BeforeClass
	public static void cadastrarEmpresa() {
		List<Produtos> prod = new ArrayList<>();
		prod.add(new Produtos("sla", 250.00, 5));
		Empresa loja = new Empresa("teste", "teste@gmail.com", prod, "0123456789123", "", "", "");
	}
	
	@Before
	public void addDadosFuncionario() {
		try {
			nascimento.parse("03/07/1992");
			funcionario.add(new Funcionario("Lucas", "lucas@gmail.com", 2500.00, Cargo.RH, nascimento));
			nascimento.parse("09/04/1990");
			funcionario.add(new Funcionario("João", "joao@gmail.com", 2000.00, Cargo.Repositor, nascimento));
			nascimento.parse("03/02/1985");
			funcionario.add(new Funcionario("Weevil", "weevil@gmail.com", 1500.00, Cargo.Repositor, nascimento));
			nascimento.parse("26/01/1989");
			funcionario.add(new Funcionario("Dante", "dante@gmail.com", 1200.00, Cargo.RH, nascimento));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}
	}

	@Before
	public void addDadosCliente() {
		try {
			nascimento.parse("19/10/1992");
			cliente.add(new Cliente("Matheus", "matheus@gmail.com", 2500.00, nascimento));
			nascimento.parse("20/11/1999");
			cliente.add(new Cliente("Vergil", "vergil@gmail.com", 1500.00, nascimento));
			nascimento.parse("9/1/1992");
			cliente.add(new Cliente("Dante", "dante@gmail.com", 900.00, nascimento));
			nascimento.parse("19/9/1996");
			cliente.add(new Cliente("Harry", "harry@gmail.com", 1300.00, nascimento));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
	}

	@Before
	public void addDadosProduto() {
		produto.add(new Produtos("Tablet", 2500.00, 50));
		produto.add(new Produtos("Computador", 3500.00, 70));
		produto.add(new Produtos("Smartphone", 2500.00, 150));
		produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
	}

	@Test(timeout = 10)
	public void contratarFuncionario() {
		if (funcionario == null) {
			assertEquals("O funcionario está nullo", funcionario);
		}

		String nome = "Jesus";
		String email = "jesus@gmail.com";
		System.out.println(this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)));
		// Verifica se o funcionario já existe
		assertFalse("O funcionario já foi contratado", this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)));

		// Caso o tenha passado na verificação, o funcionario será contratado
		try {
			nascimento.parse("03/07/1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("Você informou uma data invalida");
		}
		funcionario.add(new Funcionario(nome, email, 2500.00, Cargo.RH, nascimento));
		System.out.println("O funcionario foi contratado");
	}

	@Test(timeout = 50)
	public void demitirFuncionario() {
		if (this.funcionario == null) {
			assertEquals("O funcionario está nullo", funcionario);
		}

		String nome = "Lucas";
		String email = "lucas@gmail.com";

		// Verifica se o funcionario existe
		assertTrue("Não existe o funcionario com os dados informados", this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)));

		this.motivoDemissao = MotivoDemissao.Justa_Causa;
		switch (this.motivoDemissao) {
		case Justa_Causa:
			this.funcionario.removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		case Pediu_As_Contas:
			this.funcionario.removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		}

		// Caso o codigo passe no teste, ele vai demitir o funcionario
		System.out.println("O funcionario foi demitido");

	}

	@Test(timeout = 50)
	public void cadastrarCliente() {
		if (this.cliente == null) {
			assertEquals("O cliente está nullo", this.cliente);
		}

		String nome = "lalau";
		String email = "lalau@gmail.com";

		// Verifica se o cliente já existe
		boolean wasCadastrado = this.cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email));
		assertTrue("O cliente já está cadastrado", !wasCadastrado);

		// Caso o tenha passado na verificação, o funcionario será contratado
		try {
			nascimento.parse("03/04/2000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("Você informou uma data invalida");
		}
		cliente.add(new Cliente(nome, email, 2500.00, nascimento));
		System.out.println("O cliente foi cadastrado");
	}

	@Test
	public void cadastrarProduto() {
		if (this.produto == null) {
			assertEquals("O produto está nullo", this.produto);
		}

		String nome = "Tablet";
		Double preco = 2500.00;
		int estoque = 30;

		// Verifica se o funcionario é um repositor
		assertTrue("Apenas repositores podem cadastra os produtos",
				this.funcionario.get(2).getCargo() == Cargo.Repositor);

		// Verifica se o produto está no estoque
		boolean hasEstoque = this.produto.stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco));

		if (hasEstoque) {
			this.produto.stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco))
					.forEach(prod -> prod.setEstoque(prod.getEstoque() + estoque));
			System.out.println("Produto foi add ao estoque");
		} else {
			produto.add(new Produtos(nome, preco, estoque));
			System.out.println("Produto foi cadastrado");
		}
	}

	@After
	public void mostrarDadosNaTela() {
		System.out.println(produto);
	}



}
