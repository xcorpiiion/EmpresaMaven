package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.contmatic.enums.TipoContrato;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

	private SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");

	private Date data;

	private static List<Produto> produtos;

	private List<Funcionario> funcionarios;

	private static Empresa loja;

	@BeforeClass
	public static void addDadosIniciais() {
		produtos = new ArrayList<>();
		produtos.add(new Produto("Tablet", new BigDecimal(2500.00), 50));
		produtos.add(new Produto("Smartphone", new BigDecimal(2500.00), 150));
		produtos.add(new Produto("Fone de Ouvido", new BigDecimal(50.00), 200));
		produtos.add(new Produto("Computador", new BigDecimal(3500.00), 70));
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.SAOPAULO));
		loja.setCliente(new ArrayList<>());
		loja.setFuncionario(new ArrayList<>());
	}

	@Before
	public void add_dados_funcionario() {
		funcionarios = new ArrayList<>();
		try {
			data = nascimento.parse("03/07/1992");
			funcionarios.add(new Funcionario("Lucas", "lucas@gmail.com", new BigDecimal(2500.00), Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", 40, "São paulo", EstadosBrasil.SAOPAULO)));
			nascimento.parse("09/04/1990");
			funcionarios.add(new Funcionario("João", "joao@gmail.com", new BigDecimal(2000.00), Cargo.REPOSITOR, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", 40, "São paulo", EstadosBrasil.RIODEJANEIRO)));
			nascimento.parse("03/02/1985");
			funcionarios
					.add(new Funcionario("Weevil", "weevil@gmail.com", new BigDecimal(1500.00), Cargo.REPOSITOR, data, TipoContrato.CLT,
							new Endereco("Rua casa verde", "Casa Verde", "02678100", 40, "São paulo", EstadosBrasil.RIODEJANEIRO)));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ter_salario_maior_do_que_zero() {
		funcionarios.add(new Funcionario("Dante", "dante@gmail.com", new BigDecimal(-1), Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", 40, "São paulo", EstadosBrasil.RIOGRANDEDOSUL)));
	}
	
	@Test
	public void deve_ser_do_rh_para_contratar() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("james", "james@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.PIAUI));
		assertFalse(loja.getFuncionario().stream().anyMatch(func -> func.getEmail().equalsIgnoreCase("james")
				&& func.getEmail().equalsIgnoreCase("james@gmail.com")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_do_rh_para_contratar_expection() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(1).contratarFuncionario("james", "james@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), data,
				funcionarios.get(1), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.DISTRITOFEDERAL));
	}

	@Test(expected = NullPointerException.class)
	public void deve_ser_do_rh_para_contratar_e_nao_deve_ter_loja_null_exception() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("a", "a@gmail.com", null, new BigDecimal(2500.00), data, funcionarios.get(0),
				loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.ALAGOAS));
	}

	@Test(expected = NullPointerException.class)
	public void deve_ser_do_rh_para_contratar_e_nao_pode_ter_salario_null_exception() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("a", "a@gmail.com", Cargo.REPOSITOR, null, data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.CEARA));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_do_rh_para_contratar_e_nao_pode_ter_salario_negativo_exception() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("james", "james@gmail.com", Cargo.REPOSITOR, new BigDecimal(-10.00), data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.PERNAMBUCO));
	}

	@Test(expected = NullPointerException.class)
	public void deve_ser_do_rh_para_contratar_e_nao_pode_ter_tipoContrato_null_exception() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("a", "a@gmail.com", Cargo.REPOSITOR, new BigDecimal(500.00), data,
				funcionarios.get(0), loja, null,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.PARA));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_existir_na_loja_funcionario_para_contratar() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("lucas", "lucas@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo",EstadosBrasil.MINASGERAIS));
		funcionarios.get(0).contratarFuncionario("lucas", "lucas@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.ALAGOAS));
	}

	@Test
	public void deve_ser_do_rh_para_demitir() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("lucas", "lucas@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.PERNAMBUCO));
		funcionarios.get(0).demitirFuncionario("lucas", "lucas@gmail.com", funcionarios.get(0), loja);
		assertFalse(loja.getFuncionario().stream().anyMatch(func -> func.getEmail().equalsIgnoreCase("lucas")
				&& func.getEmail().equalsIgnoreCase("lucas@gmail.com")));
	}

	@Test
	public void deve_ser_do_rh_para_demitir_e_deve_possuir_mesmo_dados_de_funcionario_cadastrado() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).contratarFuncionario("lucas", "lucas@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), data,
				funcionarios.get(0), loja, TipoContrato.PJ,
				new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.PARAIBA));
		funcionarios.get(0).demitirFuncionario("lucas", "lucas@gmail.com", funcionarios.get(0), loja);
		assertFalse(loja.getFuncionario().stream().anyMatch(func -> func.getEmail().equalsIgnoreCase("james")
				&& func.getEmail().equalsIgnoreCase("james@gmail.com")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_do_rh_para_demitir_3() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(1).demitirFuncionario("lucas", "lucas@gmail.com", funcionarios.get(1), loja);
	}

	@Test(expected = NullPointerException.class)
	public void deve_ser_do_rh_e_nao_pode_ser_null_para_demitir() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).demitirFuncionario("lucas", "lucas@gmail.com", null, loja);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_existir_funcionario_para_demitir() {
		try {
			data = nascimento.parse("03/11/1992");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		funcionarios.get(0).demitirFuncionario("a", "a@gmail.com", funcionarios.get(0), loja);
	}

	@Test
	public void deve_ser_repositor_para_atualizar_nome_produto() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 1, "Tablet", "a", loja);
		assertTrue(loja.produtoExiste("a"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_repositor_para_atualizar_nome_produto_exception() {
		funcionarios.get(0).alterarDadosProduto(funcionarios.get(0), 1, "Tablet", "a", loja);
	}

	@Test()
	public void deve_ser_repositor_para_atualizar_nome_produto_e_condicao_deve_ser_1() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 2, "Tablet", "a", loja);
		assertTrue(loja.produtoExiste("a"));
	}

	@Test
	public void deve_ser_repositor_para_atualizar_preco_produto() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 2, "Tablet", new BigDecimal(2500.00), loja);
		assertTrue(loja.produtoExiste("a"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_repositor_para_atualizar_preco_produto_exception() {
		funcionarios.get(0).alterarDadosProduto(funcionarios.get(0), 2, "Tablet", new BigDecimal(15.00), loja);
	}

	@Test()
	public void deve_ser_repositor_para_atualizar_preco_produto_e_condicao_deve_ser_2() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 2, "a", new BigDecimal(15.00), loja);
		assertThat(loja.getProduto().get(0).getNome(), is("a"));
	}

	@Test
	public void deve_ser_repositor_para_atualizar_estoque_produto() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 3, "Tablet", 5, loja);
		assertTrue(loja.produtoExiste("Tablet"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_repositor_para_atualizar_estoque_produto_exception() {
		funcionarios.get(0).alterarDadosProduto(funcionarios.get(0), 3, "Tablet", 5, loja);
	}

	@Test()
	public void deve_ser_repositor_para_atualizar_estoque_produto_e_condicao_deve_ser_3() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 4, "Tablet", 5, loja);
		assertFalse(loja.produtoExiste("livro"));
	}

	@Test
	public void deve_ser_repositor_para_atualizar_tudo_produto() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 4, "a", new BigDecimal(250.00), 5, "livro", loja);
		assertTrue(loja.produtoExiste("livro"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_repositor_para_atualizar_tudo_produto_exception() {
		funcionarios.get(0).alterarDadosProduto(funcionarios.get(0), 4, "Tablet", new BigDecimal(250.00), 5, "livro", loja);
	}

	@Test()
	public void deve_ser_repositor_para_atualizar_tudo_produto_e_condicao_deve_ser_4() {
		funcionarios.get(1).alterarDadosProduto(funcionarios.get(1), 5, "Tablet", new BigDecimal(250.00), 5, "livro", loja);
		assertFalse(loja.produtoExiste("a"));
	}

	@Test
	public void deve_ser_repositor_para_cadastrar_produto() {
		funcionarios.get(1).cadastrarProduto("Livro", new BigDecimal(250.00), 50, funcionarios.get(1), loja);
		assertTrue(loja.produtoExiste("Livro"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_ser_repositor_para_cadastrar_produto_1() {
		funcionarios.get(0).cadastrarProduto("Livro", new BigDecimal(250.00), 50, funcionarios.get(0), loja);
	}

	@Test
	public void deve_ser_repositor_e_nao_deve_ter_produto_na_lista() {
		funcionarios.get(1).cadastrarProduto("Tablet", new BigDecimal(250.00), 50, funcionarios.get(1), loja);
		assertTrue(loja.produtoExiste("Tablet"));
	}

	@Test
	public void nao_deve_existir_funcionarios_iguais() {
		assertTrue("Os funcionarios são iguais", funcionarios.get(0).equals(funcionarios.get(0)));
	}

	@Test
	public void nao_deve_existir_funcionarios_iguais_2() {
		assertFalse("Os funcionarios são iguais", funcionarios.get(0).equals(funcionarios.get(1)));
	}

	@Test
	public void nao_deve_existir_funcionarios_iguais_3() {
		assertEquals("Os funcionarios são iguais", funcionarios.get(0).hashCode(), funcionarios.get(0).hashCode());
	}

	@After
	public void mostrarEndereco() {
		System.out.println(funcionarios.get(0).getEndereco());
	}

	@After
	public void mostrarDados() {
		System.out.println(funcionarios.get(0));
	}

}
