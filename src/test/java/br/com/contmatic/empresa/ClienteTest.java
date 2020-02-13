package br.com.contmatic.empresa;

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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Constante;
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
    private List<Cliente> clientes;

    /** The loja. */
    private static Empresa loja;

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
        clientes = new ArrayList<>();
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valid"));
        clientes.get(0).setTelefones(telefone);
    }

    /**
     * Nao deve aceitar nome vazio.
     */
    @Test
    public void nao_deve_aceitar_nome_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeEmpty");
        clientes.get(0).setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar nome com espaco em branco.
     */
    @Test
    public void nao_deve_aceitar_nome_com_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("nomeBlankSpace");
        clientes.get(0).setNome(clienteInvalid.getNome());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar email null.
     */
    @Test
    public void nao_deve_aceitar_email_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailNull");
        clientes.get(0).setNome(clienteInvalid.getEmail());
        assertNotNull(clientes.get(0).getEmail());
    }

    /**
     * Nao deve aceitar email vazio.
     */
    @Test
    public void nao_deve_aceitar_email_vazio() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailEmpty");
        clientes.get(0).setEmail(clienteInvalid.getEmail());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar email com espaco em branco.
     */
    @Test
    public void nao_deve_aceitar_email_com_espaco_em_branco() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("emailBlankSpace");
        clientes.get(0).setEmail(clienteInvalid.getEmail());
        System.out.println(clientes.get(0).getEmail());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void nao_deve_aceitar_endereco_null() {
        Cliente clienteInvalid = Fixture.from(Cliente.class).gimme("enderecoNull");
        clientes.get(0).setEndereco(clienteInvalid.getEndereco());
        assertNull(clientes.get(0).getEndereco());
    }

    /**
     * Data nascimento nao deve ser null exception.
     *
     * @throws ParseException the parse exception
     */
    @Test
    public void dataNascimento_nao_deve_ser_null_exception(){
        Cliente clienteValid = Fixture.from(Cliente.class).gimme("dataNascimentoNull");
        assertNotNull(clienteValid);
    }
    
    /**
     * Deve add dinheiro carteira.
     */
    @Test
    public void deve_add_dinheiro_carteira() {
        BigDecimal dinheiro = new BigDecimal(2500);
        BigDecimal dinheiroAnterior = clientes.get(0).getDinheiroCarteira();
        clientes.get(0).setDinheiroCarteira(clientes.get(0).getDinheiroCarteira().add(dinheiro));
        assertTrue(clientes.get(0).getDinheiroCarteira().compareTo(dinheiroAnterior) > 0);
    }

    @Test
    public void deve_retornar_true_caso_cpf_nao_seja_valido() {
        StringBuilder cpf = new StringBuilder();
        cpf.append(new Random().nextInt(888888888) + 111111111);
        clientes.get(0).setCpf(cpf.toString());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(clientes.get(0), Constante.VALOR_NAO_E_VALIDO));
    }
    
    /**
     * Deve retornar true no equals para serem iguais.
     */
    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        clientes.get(1).setCpf(clientes.get(0).getCpf());
        assertTrue("Os cliente são iguais", clientes.get(0).equals(clientes.get(1)));
    }

    /**
     * Deve ter hash code iguais para serem clientes iguais.
     */
    @Test()
    public void deve_ter_hashCode_iguais_para_serem_clientes_iguais() {
        clientes.add(Fixture.from(Cliente.class).gimme("valid"));
        clientes.get(1).setCpf(clientes.get(0).getCpf());
        assertTrue(clientes.get(0).hashCode() == clientes.get(1).hashCode());
    }

    /**
     * Nao deve ter equals null para comparar clientes.
     */
    @Test()
    public void nao_deve_ter_equals_null_para_comparar_clientes() {
        assertFalse("Os clientes são igauis", clientes.get(0).equals(null));
    }

    /**
     * Deve conter to string.
     */
    @After
    public void deve_conter_toString() {
         System.out.println(clientes.get(0));
    }
}
