import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

	@Test(timeout = 10)
	public void contratarFuncionario() {
		if (funcionario == null) {
			assertEquals("O funcionario está nullo", funcionario);
		}

		funcionario.add(new Funcionario("manoel", "jose@gmail.com", 2500.00, Cargo.Vendedor));

		// Verifica se o funcionario já existe
		int aux = 0;
		for (Funcionario func : this.funcionario) {
			aux++;
			for (int i = aux; i < this.funcionario.size(); i++) {
				if (func.hashCode() == this.funcionario.get(i).hashCode()) {
					if (func.equals(this.funcionario.get(i))) {
						this.funcionario.remove(i);
						assertTrue("Esse funcionario já foi contratado", false);
					}
				}
			}
		}

		// Caso o tenha passado na verificação, o funcionario será contratado
		System.out.println("O funcionario foi contratado");
	}

	@Test(timeout = 50)
	public void demitirFuncionario() {
		if (this.funcionario == null) {
			assertEquals("O funcionario está nullo", funcionario);
		}

		String nome = "Lucas";
		String email = "lucas@gmail.com";
		this.motivoDemissao = MotivoDemissao.Justa_Causa;
		boolean wasDemitido = false;
		
		switch(this.motivoDemissao) {
		case Justa_Causa:
			wasDemitido = this.funcionario.removeIf((func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		case Pediu_As_Contas:
			wasDemitido = this.funcionario.removeIf((func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		}
		
		if(!wasDemitido) {
			assertFalse("Não existe o funcionario com os dados informados", true);
		}
		
		System.out.println("O funcionario foi demitido");

	}

	@Ignore
	public void cadastrarCliente() {
		if (funcionario == null) {
			assertEquals("O funcionario está nullo", funcionario);
		}

		funcionario.add(new Funcionario("manoel", "jose@gmail.com", 2500.00, Cargo.Vendedor));

		// Verifica se o funcionario já existe
		int aux = 0;
		for (Funcionario func : this.funcionario) {
			aux++;
			for (int i = aux; i < this.funcionario.size(); i++) {
				if (func.hashCode() == this.funcionario.get(i).hashCode()) {
					if (func.equals(this.funcionario.get(i))) {
						this.funcionario.remove(i);
						assertTrue("Esse funcionario já foi contratado", false);
					}
				}
			}
		}

		// Caso o tenha passado na verificação, o funcionario será contratado
		System.out.println("O funcionario foi contratado");
	}

	@Ignore
	public void cadastrarProduto() {

	}
}
