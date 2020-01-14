import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import entities.Cliente;
import entities.Funcionario;
import entities.Produtos;
import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

public class LojaTest {
	private String nome, email;
	private MotivoDemissao motivoDemissao;
	private TipoContrato tipoContrato;
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private List<Cliente> cliente = new ArrayList<Cliente>();

	@Before
	public void addDadosFuncionario() {
		funcionario.add(new Funcionario("Lucas", "lucas@gmail.com", 2500.00, Cargo.Vendedor));
		funcionario.add(new Funcionario("João", "joao@gmail.com", 2000.00, Cargo.Vendedor));
		funcionario.add(new Funcionario("Weevil", "weevil@gmail.com", 1500.00, Cargo.Repositor));
		funcionario.add(new Funcionario("Dante", "dante@gmail.com", 1200.00, Cargo.Atendente));
	}
	
	@Before
	public void addDadosCliente() {
		cliente.add(new Cliente("Matheus", "matheus@gmail.com", 2500.00));
		cliente.add(new Cliente("Vergil", "vergil@gmail.com", 1500.00));
		cliente.add(new Cliente("Dante", "dante@gmail.com", 900.00));
		cliente.add(new Cliente("Harry", "harry@gmail.com", 1300.00));
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
		
		String nome = "lalau";
		String email = "lalau@gmail.com";
		
		// Verifica se o funcionario já existe
		boolean wasContratado = this.funcionario.stream().anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && 
				func.getEmail().equalsIgnoreCase(email));
		assertTrue("O funcionario já foi contratado", !wasContratado);
		
		// Caso o tenha passado na verificação, o funcionario será contratado
		funcionario.add(new Funcionario(nome, email, 2500.00, Cargo.Vendedor));
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
		boolean wasDemitido = this.funcionario.stream().anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && 
				func.getEmail().equalsIgnoreCase(email));;
		assertTrue("Não existe o funcionario com os dados informados", wasDemitido);
		
		this.motivoDemissao = MotivoDemissao.Justa_Causa;
		switch(this.motivoDemissao) {
		case Justa_Causa:
			wasDemitido = this.funcionario.removeIf((func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		case Pediu_As_Contas:
			wasDemitido = this.funcionario.removeIf((func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
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
		boolean wasCadastrado = this.cliente.stream().anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && 
				c.getEmail().equalsIgnoreCase(email));
		assertTrue("O cliente já está cadastrado", !wasCadastrado);

		// Caso o tenha passado na verificação, o funcionario será contratado
		cliente.add(new Cliente(nome, email, 2500.00));
		System.out.println("O cliente foi cadastrado");
	}

	@Test
	public void cadastrarProduto() {
		if (this.produto == null) {
			assertEquals("O produto está nullo", this.produto);
		}
		
		String nome = "lalau";
		String email = "lalau@gmail.com";
		
		
		// Verifica se o funcionario é um repositor
		boolean isRepositor = this.funcionario.get(2).getCargo() == Cargo.Repositor;
		assertTrue("Apenas repositores podem cadastra os produtos", isRepositor);

		// Caso o tenha passado na verificação, o funcionario será contratado
//		this.produto.add(new Produtos(nome, preco, estoque));
		System.out.println("O produto foi cadastrado");
	}
}
