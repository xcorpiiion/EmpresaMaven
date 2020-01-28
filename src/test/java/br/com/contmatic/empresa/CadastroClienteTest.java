package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.junit.Test;
import br.com.contmatic.enums.EstadosBrasil;

public class CadastroClienteTest {

    private SimpleDateFormat nascimento;

    private static CadastroCliente cadastroCliente;

    private Date data;

    private List<Cliente> clientes;

    private static Empresa loja;

    private static List<Produto> produtos;

    @BeforeClass
    public static void addDadosIniciais() {
        produtos = new ArrayList<>();
        produtos.add(new Produto("Tablet", new BigDecimal(250.00), 50));
        loja = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123", new Endereco("Rua limões", "Santa Maria", "02177120", "345", "São paulo", EstadosBrasil.CEARA));
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
        cadastroCliente = new CadastroCliente(loja);
    }

    @Before
    public void addDadosCliente() {
        clientes = new ArrayList<>();
        nascimento = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = nascimento.parse("19/10/1992");
            clientes.add(new Cliente("Matheus", "matheus@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.MATOGROSSODOSUL)));
            data = nascimento.parse("20/11/1999");
            clientes.add(new Cliente("Vergil", "vergil@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.GOIAS)));
            data = nascimento.parse("9/1/1992");
            clientes.add(new Cliente("Dante", "dante@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.SAOPAULO)));
            data = nascimento.parse("19/9/1996");
            clientes.add(new Cliente("Harry", "harry@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.RIODEJANEIRO)));
        } catch (Exception e) {
            fail("Você digitou uma data invalida");
        }

        clientes.get(0).setCarrinhoProduto(new ArrayList<>());
        clientes.get(0).setProdutosComprados(new ArrayList<>());

        clientes.get(0).addItensCarrinho(clientes.get(0), loja, "Tablet", 2);
        clientes.get(0).setDinheiroCarteira(new BigDecimal(2500.00));
    }

    @Test()
    public void deve_existir_cliente() {
        String nome = "matheus";
        String email = "matheus@gmail.com";
        assertFalse("Cliente não existe", loja.clienteExiste(loja, nome, email));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_existir_cliente_com_mesmo_dados_para_cadastrar() {
        String nome = "lucas";
        String email = "lucas@gmail.com";
        try {
            data = nascimento.parse("19/10/1992");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cadastroCliente.cadastrarCliente(nome, email, data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.SAOPAULO));
        assertTrue("O cliente existe", loja.clienteExiste(loja, nome, email));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_existir_cliente_com_mesmo_dados_para_cadastrar_expection() {
        String nome = "lucas";
        String email = "lucas@gmail.com";
        try {
            data = nascimento.parse("19/10/1992");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cadastroCliente.cadastrarCliente(nome, email, data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.RIODEJANEIRO));
        cadastroCliente.cadastrarCliente(nome, email, data, new Endereco("Rua almeida", "Jardim santana", "02676000", "35-A", "São paulo", EstadosBrasil.ACRE));
        assertTrue("O cliente existe", loja.clienteExiste(loja, nome, email));
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_settar_empresa_null() {
        cadastroCliente.setLoja(null);
    }

    @Test
    public void deve_gerar_hashCodes_iguais_para_diferentes_objetos_com_o_mesmo_atributo_loja() {
        CadastroCliente cadastroCliente1 = new CadastroCliente(loja);
        CadastroCliente cadastroCliente2 = new CadastroCliente(loja);
        assertEquals(cadastroCliente1.hashCode(), cadastroCliente2.hashCode());
    }

    @Test
    public void deve_igualar_diferentes_objetos_se_possuirem_o_mesmo_atributo_loja() {
        CadastroCliente cadastroCliente1 = new CadastroCliente(loja);
        CadastroCliente cadastroCliente2 = new CadastroCliente(loja);
        System.out.println(cadastroCliente1.equals(cadastroCliente2));
        assertTrue(cadastroCliente1.equals(cadastroCliente2));
    }

    @Test
    public void deve_igualar_mesmos_objetos() {
        CadastroCliente cadastroCliente1 = new CadastroCliente(loja);
        assertTrue(cadastroCliente1.equals(cadastroCliente1));
    }

    @After
    public void mostrarLoja() {
        System.out.println(cadastroCliente);
    }

}
