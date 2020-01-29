package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.contmatic.services.EmptyStringException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    private static List<Produto> produtos;

    private SimpleDateFormat nascimento;

    private Date data;

    private List<Cliente> clientes;

    private static Empresa loja;

    @BeforeClass
    public static void addDadosIniciais() {
        produtos = new ArrayList<>();
        produtos.add(new Produto("Tablet", new BigDecimal(250.00), 50));
        produtos.add(new Produto("Computador", new BigDecimal(3500.00), 70));
        produtos.add(new Produto("Smartphone", new BigDecimal(2500.00), 150));
        produtos.add(new Produto("Fone de Ouvido", new BigDecimal(50.00), 200));
        loja = new Empresa("Kratos games", "kratosgames@gmail.com", produtos, "01234567890123", new Endereco("Rua limões", "Santa Maria", "02177120", 345, "São paulo", EstadosBrasil.PIAUI));
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
    }

    @Before
    public void addDadosCliente() {
        clientes = new ArrayList<>();
        nascimento = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = nascimento.parse("19/10/1992");
            clientes.add(new Cliente("Matheus", "matheus@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", 35, "São paulo", EstadosBrasil.RIOGRANDEDOSUL)));
            data = nascimento.parse("20/11/1999");
            clientes.add(new Cliente("Vergil", "vergil@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", 35, "São paulo", EstadosBrasil.SERGIPE)));
            data = nascimento.parse("9/1/1992");
            clientes.add(new Cliente("Dante", "dante@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", 35, "São paulo", EstadosBrasil.TOCANTINS)));
            data = nascimento.parse("19/9/1996");
            clientes.add(new Cliente("Harry", "harry@gmail.com", data, new Endereco("Rua almeida", "Jardim santana", "02676000", 35, "São paulo", EstadosBrasil.RIOGRANDEDOSUL)));
        } catch (Exception e) {
            fail("Você digitou uma data invalida");
        }

        clientes.get(0).setCarrinhoProduto(new ArrayList<>());
        clientes.get(0).setProdutosComprados(new ArrayList<>());

        clientes.get(0).addItensCarrinho(clientes.get(0), loja, "Tablet", 2);
        clientes.get(0).setDinheiroCarteira(new BigDecimal(2500.00));
    }

    @Ignore
    public void alterarDados() throws Exception {
        clientes.get(0).setNome("lucas");
        clientes.get(0).setEmail("lucas@mail.com");
        clientes.get(0).getEndereco().setRua("Rua dos alfeneiros");
        clientes.get(0).getEndereco().setNumeroResidencia(45);
        clientes.get(0).getEndereco().setBairro("Jardim formiga");
        clientes.get(0).getEndereco().setCep("12345678");
        clientes.get(0).getEndereco().setCidade("Salvador");
        clientes.get(0).getEndereco().setEstado(EstadosBrasil.RIOGRANDEDOSUL);
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_nome_null() {
        clientes.get(0).setNome(null);
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_nome_vazio() {
        clientes.get(0).setNome("");
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_nome_vazio_2() {
        clientes.get(0).setNome(" ");
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_email_null() {
        clientes.get(0).setEmail(null);
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_email_vazio() {
        clientes.get(0).setEmail(" ");
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_email_vazio_2() {
        clientes.get(0).setEmail("");
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_endereco_null() {
        clientes.get(0).setEndereco(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_numero_estoque_maior_que_numero_estoque_produtos_expection() {
        clientes.get(0).addItensCarrinho(clientes.get(0), loja, "Tablet", 51);
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_loja_null() throws Exception {
        clientes.get(0).addItensCarrinho(clientes.get(0), null, "Tablet", 51);
    }

    @Test()
    public void deve_existir_produto_na_lista() {
        String nome = "Tablet";
        assertTrue("O produto não existe", clientes.get(0).produtoEstaNoCarrinho(nome, clientes.get(0)));
    }

    @Test(timeout = 100)
    public void dataNascimento_deve_ser_valida() {
        nascimento = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = nascimento.parse("01/01/1999");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clientes.get(0).setDataNascimento(data);
        assertThat(data, is(clientes.get(0).getDataNascimento()));
    }

    @Test(expected = ParseException.class)
    public void dataNascimento_deve_ser_valida_exception() throws ParseException {
        data = nascimento.parse("/01/1999");
        clientes.get(0).setDataNascimento(data);
    }

    @Test(expected = NullPointerException.class)
    public void dataNascimento_nao_deve_ser_null_exception() throws ParseException {
        clientes.get(0).setDataNascimento(null);
    }

    @Test()
    public void deve_add_produto_no_carrinho() {
        String nomeProduto = "Tablet";
        int qtdProdutoAddCarrinho = 1;
        clientes.get(0).addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
        assertTrue("O produto não exite no carrinho", clientes.get(0).produtoEstaNoCarrinho(nomeProduto, clientes.get(0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_pode_add_zero_produtos_no_carrinho() {
        String nomeProduto = "Tablet";
        int qtdProdutoAddCarrinho = 0;
        clientes.get(0).addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_produto_sem_nome() {
        String nomeProduto = "";
        int qtdProdutoAddCarrinho = 0;
        clientes.get(0).addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
    }

    @Test
    public void deve_add_dinheiro_carteira() {
        BigDecimal dinheiro = new BigDecimal(2500);
        clientes.get(0).setDinheiroCarteira(clientes.get(0).getDinheiroCarteira().add(dinheiro));
        System.out.println(clientes.get(0).getDinheiroCarteira());
        assertThat(clientes.get(0).getDinheiroCarteira(), is(new BigDecimal(5000)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_valor_maior_do_que_zero_para_add_valor_na_carteira() {
        BigDecimal dinheiro = new BigDecimal(-1);
        clientes.get(0).setDinheiroCarteira(dinheiro);

    }

    @Test
    public void deve_compra_produto() {
        String nomeProduto = "Tablet";
        int qtdProdutosCompra = 1;
        clientes.get(0).compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
        assertTrue(clientes.get(0).getProdutosComprados().contains(loja.getProduto().get(3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_nome_do_produto_para_compra() {
        String nomeProduto = "";
        int qtdProdutosCompra = 50;
        clientes.get(0).compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_dinheiro_suficiente_para_comprar() {
        String nomeProduto = "Tablet";
        int qtdProdutosCompra = 30;
        clientes.get(0).setDinheiroCarteira(new BigDecimal(250));
        clientes.get(0).addItensCarrinho(clientes.get(0), loja, nomeProduto, 20);
        clientes.get(0).compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_quantidade_de_itens_para_compra_menor_ou_igual_a_quantidade_estoque() {
        String nomeProduto = "Tablet";
        int qtdProdutosCompra = 500;
        clientes.get(0).compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
    }

    @Test()
    public void deve_ter_o_mesmo_email_para_serem_iguais() {
        clientes.get(1).setNome("a");
        clientes.get(1).setEmail(clientes.get(0).getEmail());
        assertFalse("Os cliente são iguais", clientes.get(0).equals(clientes.get(1)));
    }

    @Test
    public void deve_ter_o_mesmo_nome_para_serem_iguais() {
        clientes.get(1).setNome(clientes.get(0).getNome());
        clientes.get(1).setEmail("a");
        assertFalse("Os cliente são iguais", clientes.get(0).equals(clientes.get(1)));
    }

    @Test()
    public void deve_ter_hashCode_iguais_para_serem_clientes_iguais() {
        clientes.get(1).setNome(clientes.get(0).getNome());
        clientes.get(1).setEmail(clientes.get(0).getEmail());
        assertEquals("Os clientes são igauis", clientes.get(0).hashCode(), clientes.get(1).hashCode());
    }

    @Test()
    public void nao_deve_ter_equals_null_para_comparar_clientes() {
        assertFalse("Os clientes são igauis", clientes.get(0).equals(null));
    }

    @After
    public void deve_conter_toString() {
        System.out.println(clientes.get(0));
    }

    @AfterClass
    public static void mostrarCliente() {
        System.out.println("Cliente foi cadastrado com sucesso");
    }

}
