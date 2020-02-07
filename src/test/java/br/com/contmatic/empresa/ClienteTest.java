package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.constantes.controller.CarrinhoCliente;
import br.com.contmatic.constantes.controller.CompraProduto;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    private static List<Produto> produtos;

    private SimpleDateFormat nascimento;

    private Date data;

    private List<Cliente> clientes;

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
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, loja.getProduto().get(0).getNome(), 5);
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
    public void nao_deve_aceitar_nome_null_error() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeNull");
        clientes.get(0).setNome(clienteInvalid.getNome());
        ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.NOME_NAO_PODE_ESTA_VAZIO);
        clientes.get(0).setCpf("0");
        ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.NOME_NAO_PODE_ESTA_VAZIO);
        assertNull(clientes.get(0).getNome());
    }

    @Test
    public void nao_deve_aceitar_nome_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeEmpty");
        clientes.get(0).setNome(clienteInvalid.getNome());
        assertEquals(Constante.NOME_NAO_PODE_ESTA_VAZIO, ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.NOME_NAO_PODE_ESTA_VAZIO));
    }

    @Test
    public void nao_deve_aceitar_nome_com_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeBlankSpace");
        clientes.get(0).setNome(clienteInvalid.getNome());
        assertEquals(Constante.NOME_NAO_PODE_ESTA_VAZIO, ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.NOME_NAO_PODE_ESTA_VAZIO));
    }

    @Test
    public void nao_deve_aceitar_email_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailNull");
        clientes.get(0).setNome(clienteInvalid.getEmail());
        assertNotNull(clientes.get(0).getEmail());
    }

    @Test
    public void nao_deve_aceitar_email_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailEmpty");
        clientes.get(0).setEmail(clienteInvalid.getEmail());
        assertEquals(Constante.EMAIL_NAO_PODE_ESTA_VAZIO, ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.EMAIL_NAO_PODE_ESTA_VAZIO));
    }

    @Test
    public void nao_deve_aceitar_email_com_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailBlankSpace");
        clientes.get(0).setEmail(clienteInvalid.getEmail());
        System.out.println(clientes.get(0).getEmail());
        assertEquals(Constante.EMAIL_NAO_PODE_ESTA_VAZIO, ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.EMAIL_NAO_PODE_ESTA_VAZIO));
    }

    @Test
    public void deve_aceitar_apenas_email_validos() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailInvalid");
        clientes.get(0).setEmail(clienteInvalid.getEmail());
    }

    @Test
    public void nao_deve_aceitar_endereco_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("enderecoNull");
        clientes.get(0).setEndereco(clienteInvalid.getEndereco());
        assertNull(clientes.get(0).getEndereco());
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
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, loja.getProduto().get(0).getNome(), 2);
        assertTrue("O produto não exite no carrinho", clientes.get(0).getProdutosNoCarrinho().get(0).getNome().equalsIgnoreCase(loja.getProduto().get(0).getNome()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_pode_add_zero_produtos_no_carrinho() {
        String nomeProduto = loja.getProduto().get(0).getNome();
        int qtdProdutoAddCarrinho = 0;
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_produto_sem_nome() {
        String nomeProduto = "";
        int qtdProdutoAddCarrinho = 0;
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutoAddCarrinho);
    }

    @Test
    public void deve_add_dinheiro_carteira() {
        BigDecimal dinheiro = new BigDecimal(2500);
        BigDecimal dinheiroAnterior = clientes.get(0).getDinheiroCarteira();
        clientes.get(0).setDinheiroCarteira(clientes.get(0).getDinheiroCarteira().add(dinheiro));
        assertTrue(clientes.get(0).getDinheiroCarteira().compareTo(dinheiroAnterior) > 0);

    }
    
    @Test
    public void deve_comprar() {
        String nomeProduto = loja.getProduto().get(0).getNome();
        int qtdProdutosCompra = 5;
        clientes.get(0).setDinheiroCarteira(new BigDecimal(350));
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutosCompra);
        CompraProduto.compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_dinheiro_suficiente_para_comprar() {
        String nomeProduto = loja.getProduto().get(0).getNome();
        int qtdProdutosCompra = 0;
        clientes.get(0).setDinheiroCarteira(new BigDecimal(1));
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutosCompra);
        CompraProduto.compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_quantidade_de_itens_para_compra_menor_ou_igual_a_quantidade_estoque() {
        String nomeProduto = loja.getProduto().get(0).getNome();
        int qtdProdutosCompra = 100;
        clientes.get(0).setDinheiroCarteira(new BigDecimal(250));
        CarrinhoCliente.addItensCarrinho(clientes.get(0), loja, nomeProduto, qtdProdutosCompra);
        CompraProduto.compraProduto(clientes.get(0), nomeProduto, qtdProdutosCompra);
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
        // System.out.println(clientes.get(0));
    }

    @AfterClass
    public static void mostrarCliente() {
        // System.out.println("Cliente foi cadastrado com sucesso");
    }

}
