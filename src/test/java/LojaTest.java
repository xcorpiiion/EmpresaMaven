import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
	
	@Test
	public void contratarFuncionario() {
		if(funcionario == null) {
			assertEquals("O funcionario está nullo", funcionario);
		}
	}
	
	public void demitirFuncionario() {
		
	}
	
	public void cadastrarCliente() {
		
	}
	
	public void cadastrarProduto() {
		
	}
}
