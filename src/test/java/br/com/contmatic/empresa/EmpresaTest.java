package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produtos;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.services.EmptyStringException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

	SimpleDateFormat nascimento = new SimpleDateFormat("dd/MM/yyyy");

	private Date data;

	private static List<Produtos> produto = new ArrayList<Produtos>();

	private Cliente cliente;

	private List<Funcionario> funcionario = new ArrayList<Funcionario>();

	private static Empresa loja, loja2;

	@BeforeClass
	public static void cadastrar_empresa() {
		List<Produtos> prod = new ArrayList<>();
		try {
			prod.add(new Produtos("sla", 250.00, 5));
			loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
					new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
			
		} catch (Exception e) {
			fail("Você informou o endereço errado");
		}
	}

	@Before
	public void add_dados_funcionario() {
		try {
			data = nascimento.parse("03/07/1992");
			funcionario.add(new Funcionario("Lucas", "lucas@gmail.com", 2500.00, Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("09/04/1990");
			funcionario.add(new Funcionario("João", "joao@gmail.com", 2000.00, Cargo.REPOSITOR, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("03/02/1985");
			funcionario
					.add(new Funcionario("Weevil", "weevil@gmail.com", 1500.00, Cargo.REPOSITOR, data, TipoContrato.CLT,
							new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("26/01/1989");
			funcionario.add(new Funcionario("Dante", "dante@gmail.com", 1200.00, Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}

		try {
			funcionario.get(0).contratarFuncionario("Dante", "dante@gmail.com", Cargo.RH, 1200.00, data, funcionario.get(0), loja,
					TipoContrato.CLT, new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", 
							"São paulo", "São Paulo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Before
	public void add_dados_cliente() {
		try {
			data = nascimento.parse("19/10/1992");
			cliente = new Cliente("Matheus", "matheus@gmail.com", 2500.00, data,
					new Endereco("Rua almeida", "Jardim santana", "02675000", "35-A", "São paulo", "São Paulo"));
		} catch (Exception e) {
			fail("Você digitou uma data invalida");
		}
		
	}

	@Before
	public void add_dados_produto() {
		produto.add(new Produtos("Tablet", 2500.00, 50));
		produto.add(new Produtos("Smartphone", 2500.00, 150));
		produto.add(new Produtos("Fone de Ouvido", 50.00, 200));
		produto.add(new Produtos("Computador", 3500.00, 70));
		try {
			funcionario.get(1).cadastrarProduto("Tablet", 250.00, 5, funcionario.get(1), loja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test()
	public void nao_deve_aceitar_produto_null() {
		assertNotNull("O produto esta null", produto);
	}

	@Test()
	public void nao_deve_aceitar_produto_vazio() {
		String nomeProduto = "tablet";
		assertTrue("O produto não existe", loja.produtoExiste(nomeProduto));
	}

	@Test()
	public void deve_ser_funcionario_para_fazer_login() {
		assertTrue("Não foi funcionario que fez o login",
				loja.verificaLogin("dante", "dante@gmail.com", 2));
	}

	@Test()
	public void deve_ser_funcionario_para_confirmar_login() {
		assertEquals("Não foi funcionario que fez o login", loja.getFuncionario().get(0),
				loja.funcionarioThatDoLogin("dante", "dante@gmail.com"));
	}

	@Test(expected = RuntimeException.class)
	public void deve_ser_funcionario_para_confirmar_login_2() {
		assertEquals("Não foi funcionario que fez o login", funcionario.get(0),
				loja.funcionarioThatDoLogin(funcionario.get(0).getNome(), "dante@gmail.com"));
	}
	
	@Test()
	public void deve_ser_cliente_para_fazer_login() {
		assertTrue("Não foi cliente que fez o login", loja.verificaLogin("a", "a@gmail.com", 1));
	}

	@Test()
	public void deve_ser_cliente_para_confirmar_login() {
		cliente.cadastrarCliente("a", "a@gmail.com", 250.00, data, loja, 
				new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo"));
		assertEquals("Não foi cliente que fez o login", loja.getCliente().get(0),
				loja.clienteThatDoLogin("a", "a@gmail.com"));
	}

	@Test(expected = RuntimeException.class)
	public void deve_ser_cliente_para_confirmar_login_2() {
		assertEquals("Não foi cliente que fez o login", funcionario.get(0),
				loja.clienteThatDoLogin("lucas", "matheus@gmail.com"));
	}

	@Test()
	public void deve_ser_cliente_ou_funcionario_para_fazer_login() {
		assertFalse("Não foi cliente que fez o login", loja.verificaLogin("a", "a@gmail.com", 3));
	}
	
	@Test
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros() {
		String cnpj = "12345678901234";
		loja.setCnpj(cnpj);
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_2() {
		loja.setCnpj(null);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_3() {
		String cnpj = "";
		loja.setCnpj(cnpj);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_4() {
		String cnpj = " ";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_5() {
		String cnpj = "a";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_6() {
		String cnpj = "1234567890123a";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_7() {
		String cnpj = "aaaaaaaaaaaaaa";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros_8() {
		String cnpj = "1234567890123";
		loja.setCnpj(cnpj);
	}

	@Test
	public void nao_deve_aceitar_endereco_null() {
		assertNotNull(funcionario.get(0).getEndereco());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_null_2() {
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123", null);
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.equals(loja2));
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais_2() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.equals(loja));
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais_3() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.equals(null));
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais_4() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.equals(loja2));
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais_5() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.getCnpj().equals(null));
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais_6() {
		Integer i = 0;
		assertFalse("As empresas são iguais", loja.equals(i));
	}
	
	@Test()
	public void nao_deve_ter_empresas_iguais_7() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertEquals("As lojas são iguais", loja.hashCode(), loja2.hashCode());
	}
	
	@Test
	public void deve_mostrar_endereco() {
		System.out.println(loja.getEndereco());
	}

	@After
	public void ordena_os_produtos() {
		loja.mostrarProdutos();
	}

	@AfterClass
	public static void mostrar_dados_empresa() {
		System.out.println(loja);
	}

}
