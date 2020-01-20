import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produtos;
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
	public void add_dados_funcionario() {
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
	
	@Before
	public void add_dados_produto() {
		try {
			produto.add(new Produtos("Tablet", 2500.00, 50));
			produto.add(new Produtos("Computador", 3500.00, 70));
			produto.add(new Produtos("Smartphone", 2500.00, 150));
			produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
		} catch (Exception e) {
			fail("Algum valor está null no produto");
		}
	}

	@Test
	public void nao_deve_conter_funcionario_null() {
		assertNotNull("Funcionario está null", funcionario);
	}

	@Test
	public void nao_deve_ter_dados_ja_cadastratos() {
		String nome = "Jesus";
		String email = "jesus@gmail.com";
		assertFalse("O funcionario já foi contratado", this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)));

	}

	@Test()
	public void dataNascimento_deve_ser_valida() {
		
		try {
			nascimento.parse("03/04/2000");
		} catch (ParseException e) {
			fail("Você informou uma data invalida");
		}
	}
	
	@Test
	public void nao_deve_conter_dados_invalidos() {
		String nome = "lucas";
		String email = "lucas@gmail.com";
		try {
			funcionario.add(new Funcionario(nome, email, 2500.00, Cargo.RH, nascimento, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você informou o endereco errado");
		}
	}
	
	@Test
	public void deve_existir_funcionario() {
		String nome = "Lucas";
		String email = "lucas@gmail.com";
		assertTrue("Não existe o funcionario com os dados informados", this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)));

	}
	
	@Test
	public void deve_ser_do_rh_para_demitir_contratar_funcionario() {
		assertTrue("Apenas o RH podem contratar ou demitir os funcionarios",
				this.funcionario.get(0).getCargo() == Cargo.RH);
	}
	
	@Test
	public void nao_deve_aceitar_motivos_que_nao_exista() {
		motivoDemissao = MotivoDemissao.Justa_Causa;
			assertTrue("O motivo não existe", motivoDemissao != MotivoDemissao.Justa_Causa 
					|| motivoDemissao != MotivoDemissao.Pediu_As_Contas);
		
	}
	
	@Test
	public void nao_deve_aceitar_produto_null() {
		assertNotNull(produto);
	}
	
	@Test
	public void deve_ser_repositor_para_cadastrar_produtos() {
		assertTrue("Apenas repositores podem cadastra os produtos",
				this.funcionario.get(2).getCargo() == Cargo.Repositor);
	}

	@Test
	public void deve_esta_no_estoque() {
		String nome = "tablet";
		double preco = 2500.0;
		assertTrue("O produto não existe no estoque", produto.stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco)));
	}
	
	
}
