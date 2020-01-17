import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.empresa.Endereco;
import br.com.empresa.Funcionario;
import br.com.empresa.Produtos;
import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private MotivoDemissao motivoDemissao;

	@Before
	public void addDadosFuncionario() {
		try {
			nascimento.parse("03/07/1992");
			funcionario.add(new Funcionario("Lucas", "lucas@gmail.com", 2500.00, Cargo.RH, nascimento, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("09/04/1990");
			funcionario.add(
					new Funcionario("João", "joao@gmail.com", 2000.00, Cargo.Repositor, nascimento, TipoContrato.CLT,
							new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("03/02/1985");
			funcionario.add(new Funcionario("Weevil", "weevil@gmail.com", 1500.00, Cargo.Repositor, nascimento,
					TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("26/01/1989");
			funcionario.add(new Funcionario("Dante", "dante@gmail.com", 1200.00, Cargo.RH, nascimento, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}
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
		try {
			funcionario.add(new Funcionario(nome, email, 2500.00, Cargo.RH, nascimento, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Você informou o endereco errado");
		}
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
			try {
				produto.add(new Produtos(nome, preco, estoque));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("Algum valor no produto está null");
			}
			System.out.println("Produto foi cadastrado");
		}
	}
}
