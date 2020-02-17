package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class ClienteTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    /** The produtos. */
    private static List<Produto> produtos;

    /** The clientes. */
    private Cliente cliente;

    private Cliente cliente2;

    /** The loja. */
    private static Empresa loja;

    private Set<Telefone> telefone;

    /**
     * Add dados iniciais.
     */
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

    /**
     * Add dados cliente.
     */
    @Before
    public void addDadosCliente() {
        cliente = (Fixture.from(Cliente.class).gimme("valid"));
        telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valid"));
        cliente.setTelefones(telefone);
    }

    @Test
    public void deve_add_telefone_na_lista_telefones() {
        cliente.setTelefones(telefone);
        assertTrue(cliente.getTelefones().size() > 0);
    }

    /**
     * Nao deve aceitar nome vazio.
     */
    @Test
    public void deve_retornar_true_caso_nome_esteja_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeEmpty");
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar nome com espaco em branco.
     */
    @Test
    public void deve_retornar_true_caso_nome_tenha_apenas_espacos_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeBlankSpace");
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_VAZIO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeNull");
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_NULLO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeLess3Caracter");
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeGreaterCaracter");
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeWithSpecialCaracter");
        cliente.setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar email null.
     */
    @Test
    public void nao_deve_aceitar_email_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailNull");
        cliente.setNome(clienteInvalid.getEmail());
        assertNotNull(cliente.getEmail());
    }

    /**
     * Nao deve aceitar email com espaco em branco.
     */
    @Test
    public void deve_retornar_true_caso_email_contenha_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailBlankSpace");
        cliente.setEmail(clienteInvalid.getEmail());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        cliente = Fixture.from(Cliente.class).gimme("emailLess10Caracteres");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_mais_100_caracteres() {
        cliente = Fixture.from(Cliente.class).gimme("emailGreater100Caracteres");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
        cliente = Fixture.from(Cliente.class).gimme("emailWithBlankSpaceInWord");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
        cliente = Fixture.from(Cliente.class).gimme("emailWithNumberAfterArroba");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
        cliente = Fixture.from(Cliente.class).gimme("emailWithoutArroba");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
        cliente = Fixture.from(Cliente.class).gimme("emailWithoutPontoCom");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_sem_com() {
        cliente = Fixture.from(Cliente.class).gimme("emailWithoutCom");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        cliente = Fixture.from(Cliente.class).gimme("emailWithSpecialCaracter");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void nao_deve_aceitar_endereco_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("enderecoNull");
        cliente.setEndereco(clienteInvalid.getEndereco());
        assertNull(cliente.getEndereco());
    }

    /**
     * Data nascimento nao deve ser null exception.
     *
     * @throws ParseException the parse exception
     */
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        Cliente clienteValid = Fixture.from(Cliente.class).gimme("dataNascimentoNull");
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clienteValid, Mensagem.VALOR_ESTA_NULLO));
    }

    @Test
    public void deve_alterar_dataNascimento() {
        cliente.setDataNascimento(new DateTime());
        assertEquals(new DateTime(), cliente.getDataNascimento());
    }

    /**
     * Deve add dinheiro carteira.
     */
    @Test
    public void deve_add_dinheiro_carteira() {
        BigDecimal dinheiro = new BigDecimal(2500);
        BigDecimal dinheiroAnterior = cliente.getDinheiroCarteira();
        cliente.setDinheiroCarteira(cliente.getDinheiroCarteira().add(dinheiro));
        assertTrue(cliente.getDinheiroCarteira().compareTo(dinheiroAnterior) > 0);
    }

    @Test
    public void deve_retornar_true_caso_cpf_nao_seja_valido() {
        StringBuilder cpf = new StringBuilder();
        cpf.append(new Random().nextInt(888888888) + 111111111);
        cliente.setCpf(cpf.toString());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Deve retornar true no equals para serem iguais.
     */

    @Test
    public void deve_add_produto_no_carrinho() {
        cliente.setCarrinhoProdutos(produtos);
        assertTrue(cliente.getCarrinhoProdutos().size() > 0);
    }

    @Test
    public void deve_add_produto_na_lista_de_produtos_comprados() {
        cliente.setProdutosComprados(produtos);
        assertTrue(cliente.getProdutosComprados().size() > 0);
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        cliente2 = (Fixture.from(Cliente.class).gimme("valid"));
        cliente2.setCpf(cliente.getCpf());
        assertTrue("Os cliente são iguais", cliente.equals(cliente2));
    }

    @Test()
    public void deve_retornar_true_quando_compara_com_mesmo_objeto() {
        assertTrue(cliente.equals(cliente));
    }

    @Test()
    public void deve_retornar_false_quando_compara_com_classe_diferente() {
        assertFalse(cliente.equals(new Object()));
    }

    /**
     * Deve ter hash code iguais para serem clientes iguais.
     */
    @Test()
    public void deve_ter_hashCode_iguais_para_serem_clientes_iguais() {
        cliente2 = (Fixture.from(Cliente.class).gimme("valid"));
        cliente2.setCpf(cliente.getCpf());
        assertTrue(cliente.hashCode() == cliente2.hashCode());
    }

    /**
     * Nao deve ter equals null para comparar clientes.
     */
    @Test()
    public void nao_deve_ter_equals_null_para_comparar_clientes() {
        assertFalse("Os clientes são igauis", cliente.equals(null));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_nome_no_toString() {
        assertTrue(cliente.toString().contains("nome"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_email_no_toString() {
        assertTrue(cliente.toString().contains("email"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_dataNascimento_no_toString() {
        assertTrue(cliente.toString().contains("dataNascimento"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_dinheiroCarteira_no_toString() {
        assertTrue(cliente.toString().contains("dinheiroCarteira"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_endereco_no_toString() {
        assertTrue(cliente.toString().contains("endereco"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_telefones_no_toString() {
        assertTrue(cliente.toString().contains("telefones"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_carrinhoProdutos_no_toString() {
        assertTrue(cliente.toString().contains("carrinhoProdutos"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_produtosComprados_no_toString() {
        assertTrue(cliente.toString().contains("produtosComprados"));
    }

}
