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
import br.com.contmatic.services.StringSizeException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

	private SimpleDateFormat nascimento;

	private Date data;

	private static List<Produto> produtos;

	private List<Funcionario> funcionarios;

	private static Empresa loja, loja2;

	@BeforeClass
	public static void cadastrar_empresa() {
		produtos = new ArrayList<>();
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
	}

	@Before
	public void add_dados_funcionario() {
		funcionarios = new ArrayList<>();
		try {
			nascimento = new SimpleDateFormat("dd/MM/yyyy");
			data = nascimento.parse("03/07/1992");
			funcionarios.add(new Funcionario("Lucas", "lucas@gmail.com", new BigDecimal(2500.00), Cargo.RH, data,
					TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("09/04/1990");
			funcionarios.add(new Funcionario("João", "joao@gmail.com", new BigDecimal(2500.00), Cargo.REPOSITOR, data,
					TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("03/02/1985");
			funcionarios.add(new Funcionario("Weevil", "weevil@gmail.com", new BigDecimal(2500.00), Cargo.REPOSITOR,
					data, TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
			nascimento.parse("26/01/1989");
			funcionarios.add(new Funcionario("Dante", "dante@gmail.com", new BigDecimal(2500.00), Cargo.RH, data,
					TipoContrato.CLT,
					new Endereco("Rua casa verde", "Casa Verde", "02678100", "40", "São paulo", "São Paulo")));
		} catch (Exception e) {
			fail("Você informou uma data invalida");
		}

	}

	@Before
	public void add_dados_produto() {
		produtos.add(new Produto("Tablet", new BigDecimal(2500.00), 50));
		produtos.add(new Produto("Smartphone", new BigDecimal(2500.00), 150));
		produtos.add(new Produto("Fone de Ouvido", new BigDecimal(50.00), 200));
		produtos.add(new Produto("Computador", new BigDecimal(3500.00), 70));

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
		assertNotNull("O produto esta null", produtos);
	}
	
	@Test
	public void deve_settar_o_funcionario() {
		loja.setFuncionario(funcionarios);
	}
	
	@Test
	public void deve_settar_o_cliente() {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente("Matheus", "matheus@gmail.com", data,
					new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", "São Paulo")));
		loja.setCliente(clientes);
	}

	@Test()
	public void nao_deve_aceitar_produto_que_nao_exista_na_loja() {
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
	public void nao_deve_aceitar_cnpj_vazio() {
		String cnpj = "";
		loja.setCnpj(cnpj);
	}

	@Test(expected = EmptyStringException.class)
	public void nao_deve_aceitar_cnpj_com_espaco_em_branco() {
		String cnpj = " ";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_com_letras() {
		String cnpj = "a";
		loja.setCnpj(cnpj);
	}

	@Test(expected = RuntimeException.class)
	public void nao_deve_aceitar_cnpj_com_letras_e_numeros_juntos() {
		String cnpj = "1234567890123a";
		loja.setCnpj(cnpj);
	}

	@Test(expected = StringSizeException.class)
	public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
		String cnpj = "1234567890123";
		loja.setCnpj(cnpj);
	}

	@Test
	public void nao_deve_aceitar_endereco_null() {
		assertNotNull(funcionarios.get(0).getEndereco());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_null_exception() {
		loja = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123", null);
	}

	@Test()
	public void deve_ter_o_mesmo_cnpj_para_serem_iguais() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.equals(loja2));
	}

	@Test()
	public void nao_deve_aceitar_null_para_comparar_lojas() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.equals(null));
	}

	@Test()
	public void nao_deve_aceitar_cnpj_null_para_compara_lojas() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "11234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertFalse("As empresas são iguais", loja.getCnpj().equals(null));
	}

	@Test()
	public void deve_ter_o_mesmo_hashCode_para_serem_iguais() {
		loja2 = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123",
				new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", "São Paulo"));
		assertEquals("As lojas são iguais", loja.hashCode(), loja2.hashCode());
	}

	@AfterClass
	public static void mostrar_dados_empresa() {
		System.out.println(loja);
	}

}
