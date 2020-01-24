package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;

public class LoginTest {

	private SimpleDateFormat nascimento;

	private Date data;

	private static List<Produto> produtos;
	
	private List<Funcionario> funcionarios;
	
	private Cliente cliente;
	
	private static Empresa loja;
	
	private Login login;
	
	@BeforeClass
	public static void cadastrar_empresa() {
		produtos = new ArrayList<>();
		List<Produto> prod = new ArrayList<>();
		prod.add(new Produto("sla", new BigDecimal(250.00), 5));
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		loja.setFuncionario(new ArrayList<>());
		loja.setCliente(new ArrayList<>());
	}
	
	@Before
	public void iniciarLogin() {
		login = new Login(loja);
	}
	
	@Before
	public void add_dados_funcionario() {
		nascimento = new SimpleDateFormat("dd/MM/yyyy");
		try {
			funcionarios = new ArrayList<>();
			data = nascimento.parse("03/07/1992");
			funcionarios.add(new Funcionario("Lucas", "lucas@gmail.com", new BigDecimal(2500.00), Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			data = nascimento.parse("09/04/1990");
			funcionarios.add(new Funcionario("João", "joao@gmail.com", new BigDecimal(2000.00), Cargo.REPOSITOR, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			data = nascimento.parse("03/02/1985");
			funcionarios
					.add(new Funcionario("Weevil", "weevil@gmail.com", new BigDecimal(1500.00), Cargo.REPOSITOR, data, TipoContrato.CLT,
							new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			data = nascimento.parse("26/01/1989");
			funcionarios.add(new Funcionario("Dante", "dante@gmail.com", new BigDecimal(1200.00), Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (ParseException e) {
			fail("Você informou uma data invalida");
		}

		try {
			funcionarios.get(0).contratarFuncionario("Dante", "dante@gmail.com", Cargo.RH, new BigDecimal(1200.00), data,
					funcionarios.get(0), loja, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void add_dados_cliente() {
		nascimento = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data = nascimento.parse("19/10/1992");
			cliente = new Cliente("Matheus", "matheus@gmail.com", data,
					new Endereco("Rua almeida", "Jardim santana", "02675000", "35-A", "São paulo", "São Paulo"));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
	}

	@Test()
	public void deve_ser_funcionario_para_fazer_login() {
		assertTrue("Não foi funcionario que fez o login", login.verificaLogin("dante", "dante@gmail.com", 2, loja));
	}

	@Test()
	public void deve_ser_funcionario_para_confirmar_login() {
		assertEquals("Não foi funcionario que fez o login", loja.getFuncionario().get(0),
				login.funcionarioThatDoLogin("dante", "dante@gmail.com", loja));
	}

	@Test(expected = RuntimeException.class)
	public void deve_ter_email_e_login_validos_para_fazer_login_funcionario() {
		assertEquals("Não foi funcionario que fez o login", funcionarios.get(0),
				login.funcionarioThatDoLogin(funcionarios.get(0).getNome(), "dante@gmail.com", loja));
	}

	@Test()
	public void deve_ser_cliente_para_fazer_login() {
		assertTrue("Não foi cliente que fez o login", login.verificaLogin("a", "a@gmail.com", 1, loja));
	}

	@Test()
	public void deve_ser_cliente_para_confirmar_login() {
		cliente.cadastrarCliente("a", "a@gmail.com", data, loja,
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
		assertEquals("Não foi cliente que fez o login", loja.getCliente().get(0),
				login.clienteThatDoLogin("a", "a@gmail.com", loja));
	}

	@Test(expected = RuntimeException.class)
	public void deve_ter_email_e_login_validos_para_fazer_login_cliente() {
		assertEquals("Não foi cliente que fez o login", funcionarios.get(0),
				login.clienteThatDoLogin("lucas", "matheus@gmail.com", loja));
	}

	@Test()
	public void deve_ser_cliente_ou_funcionario_para_fazer_login() {
		assertTrue("Não foi cliente que fez o login", login.verificaLogin("a", "a@gmail.com", 3, loja));
	}
	
	@After
	public void mostrarDados() {
		System.out.println(login);
	}

}
