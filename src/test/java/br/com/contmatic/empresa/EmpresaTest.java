package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.services.EmptyStringException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

	private SimpleDateFormat nascimento;

	private Date data;

	private static List<Produto> produto;

	private List<Funcionario> funcionario;

	private static Empresa loja, loja2;

	@BeforeClass
	public static void cadastrar_empresa() {
		produto = new ArrayList<>();
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produto, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
	}

	@Before
	public void add_dados_funcionario() {
		funcionario = new ArrayList<>();
		try {
			nascimento = new SimpleDateFormat("dd/MM/yyyy");
			data = nascimento.parse("03/07/1992");
			funcionario.add(new Funcionario("Lucas", "lucas@gmail.com", new BigDecimal(2500.00), Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("09/04/1990");
			funcionario.add(new Funcionario("João", "joao@gmail.com", new BigDecimal(2500.00), Cargo.REPOSITOR, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("03/02/1985");
			funcionario
					.add(new Funcionario("Weevil", "weevil@gmail.com", new BigDecimal(2500.00), Cargo.REPOSITOR, data, TipoContrato.CLT,
							new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("26/01/1989");
			funcionario.add(new Funcionario("Dante", "dante@gmail.com", new BigDecimal(2500.00), Cargo.RH, data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}

		try {
			funcionario.get(0).contratarFuncionario("Dante", "dante@gmail.com", Cargo.RH, new BigDecimal(1200.00), data,
					funcionario.get(0), loja, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void add_dados_produto() {
		produto.add(new Produto("Tablet", new BigDecimal(2500.00), 50));
		produto.add(new Produto("Smartphone", new BigDecimal(2500.00), 150));
		produto.add(new Produto("Fone de Ouvido", new BigDecimal(50.00), 200));
		produto.add(new Produto("Computador", new BigDecimal(3500.00), 70));
		try {
			funcionario.get(1).cadastrarProduto("Tablet", new BigDecimal(250.00), 5, funcionario.get(1), loja);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Before
	public void deve_mostrar_endereco() {
		System.out.println(loja.getEndereco());
	}

	@Before
	public void ordena_os_produtos() {
		loja.mostrarProdutos();
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

	@Test
	public void nao_deve_aceitar_cnpj_null_com_letras_com_espaco_menos_14_numeros() {
		String cnpj = "12345678901234";
		loja.setCnpj(cnpj);
		assertEquals(loja.getCnpj(), cnpj);
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cnpj_null() {
		loja.setCnpj(null);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_com_vazio() {
		String cnpj = "";
		loja.setCnpj(cnpj);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_com_espaco() {
		String cnpj = " ";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_com_letras() {
		String cnpj = "a";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_com__menos_14_numeros_e_com_letras() {
		String cnpj = "1234567890123a";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_com_letras_e_numeros_juntos() {
		String cnpj = "aaaaaaaaaaaaaa";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
		String cnpj = "1234567890123";
		loja.setCnpj(cnpj);
	}

	@Test
	public void nao_deve_aceitar_endereco_null() {
		assertNotNull(funcionario.get(0).getEndereco());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_null_exception() {
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
		assertFalse("As empresas são iguais", loja.equals(loja2));
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
	public void nao_deve_ter_empresas_iguais_7() {
		assertEquals("As lojas são iguais", loja.hashCode(), loja.hashCode());
	}

	@AfterClass
	public static void mostrar_dados_empresa() {
		System.out.println(loja);
	}

}
