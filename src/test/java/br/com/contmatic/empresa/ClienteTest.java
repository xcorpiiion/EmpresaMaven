package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

import br.com.contmatic.enums.EstadosBrasil;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    private static List<Produto> produtos;

    private SimpleDateFormat nascimento;

    private Date data;

    private List<Cliente> clientes;

    private static ValidadorAnnotionsMsgErro<Cliente> validadorAnnotionsMsgErro;
    
    private static Empresa loja;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setProduto(produtos);
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
    }

    @Before
    public void addDadosCliente() {
        clientes = new ArrayList<>();
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        clientes.get(0).addItensCarrinho(clientes.get(0), loja, loja.getProduto().get(0).getNome(), 2);
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

    @Test
    public void nao_deve_aceitar_nome_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeNull");
        clientes.get(0).setNome(clienteInvalid.getNome());
        validadorAnnotionsMsgErro = new ValidadorAnnotionsMsgErro<Cliente>();
        assertEquals("Nome não pode esta vazio", validadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0)));
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_nome_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeEmpty");
        clientes.get(0).setNome(clienteInvalid.getNome());
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_nome_com_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("blanckSpace");
        clientes.get(0).setNome(clienteInvalid.getNome());
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_email_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailNull");
        clientes.get(0).setNome(clienteInvalid.getEmail());
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_email_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailEmpty");
        clientes.get(0).setNome(clienteInvalid.getEmail());
    }

    @Test(expected = EmptyStringException.class)
    public void nao_deve_aceitar_email_com_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailBlankSpace");
        clientes.get(0).setNome(clienteInvalid.getEmail());
    }

    @Test(expected = StringFormatException.class)
    public void deve_aceitar_apenas_email_validos() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailInvalid");
        System.out.println();
        clientes.get(0).setEmail(clienteInvalid.getEmail());
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_endereco_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("enderecoNull");
        clientes.get(0).setEndereco(clienteInvalid.getEndereco());
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

    @Test(timeout = 200)
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
        nascimento = new SimpleDateFormat("dd/MM/yyyy");
        data = nascimento.parse("/01/1999");
        clientes.get(0).setDataNascimento(data);
    }

    @Test
    public void dataNascimento_nao_deve_ser_null_exception() throws ParseException {
        Cliente clienteValid = Fixture.from(Cliente.class).gimme("dataNascimentoNull");
        assertNotNull(clienteValid);
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
        System.out.println(clientes.get(0).getDinheiroCarteira());
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
        //System.out.println(clientes.get(0));
    }

    @AfterClass
    public static void mostrarCliente() {
       // System.out.println("Cliente foi cadastrado com sucesso");
    }

}
