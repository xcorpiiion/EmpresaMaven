import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FuncionarioTest {

	private double salario;
	
	@Before
	public void salarioInicial() {
		this.salario = 5000.00;
	}
	
	@Test
	public void aumentaSalario() {
		this.salario += 1000.00;
		assertEquals("O salario não mudou", 6000.00, this.salario, 0);
	}

	@Test
	public void descontoSalario() {
		this.salario -= 1000.00;
		assertEquals("O salario não mudou", 4000.00, this.salario, 0);
	}
}
