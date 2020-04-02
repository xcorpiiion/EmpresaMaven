package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.easyrandom.EasyRandomCliente;
import br.com.contmatic.easyrandom.EasyRandomProduto;
import br.com.contmatic.easyrandom.EasyRandomTelefone;
import br.com.contmatic.easyrandom.TipoDadoParaTesteCliente;
import br.com.contmatic.easyrandom.TipoDadoParaTesteProduto;
import br.com.contmatic.easyrandom.TipoDadoParaTesteTelefone;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * The Class ClienteTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    /** The produtos. */
    private static List<Produto> produtos;

    /** The clientes. */
    private static Cliente cliente;

    private static Set<Telefone> telefone;

    /**
     * Add dados iniciais.
     */
    @BeforeClass
    public static void addDadosIniciais() {
        produtos = new ArrayList<>();
        produtos.add(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO));
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.VALIDO);
        telefone = new HashSet<>();
        telefone.add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
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
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.NOME_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_VAZIO));
    }

    /**
     * Nao deve aceitar nome com espaco em branco.
     */
    @Test
    public void deve_retornar_true_caso_nome_tenha_apenas_espacos_em_branco() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.NOME_BLANK_SPACE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.NOME_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_NULLO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.NOME_INVALID_SIZE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.NOME__WITH_SPECIAL_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar email null.
     */
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.EMAIL_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_NULLO));
    }

    /**
     * Nao deve aceitar email com espaco em branco.
     */
    @Test
    public void deve_retornar_true_caso_email_contenha_espaco_em_branco() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.EMAIL_BLANK_SPACE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.EMAIL_INVALID_SIZE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

//    @Test
//    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
//        cliente = Fixture.from(Cliente.class).gimme("emailWithBlankSpaceInWord");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//
//    @Test
//    public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
//        cliente = Fixture.from(Cliente.class).gimme("emailWithNumberAfterArroba");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//
//    @Test
//    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
//        cliente = Fixture.from(Cliente.class).gimme("emailWithoutArroba");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//
//    @Test
//    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
//        cliente = Fixture.from(Cliente.class).gimme("emailWithoutPontoCom");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//
//    @Test
//    public void deve_retornar_true_caso_email_esteja_sem_com() {
//        cliente = Fixture.from(Cliente.class).gimme("emailWithoutCom");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
//    }

    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.EMAIL_WITH_SPECIAL_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_endereco_seja_vazio() {
    	cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.EMAIL_EMPTY);
    	assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void deve_retornar_true_caso_endereco_seja_null() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.ENDERECO_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_NULLO));
    }
    
    /**
     * Data nascimento nao deve ser null exception.
     *
     * @throws ParseException the parse exception
     */
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.DATA_NASCIMENTO_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_NULLO));
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
    public void deve_retornar_true_caso_dinheiro_carteira_seja_null() {
    	cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.DINHEIRO_CARTEIRA_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }
   
    @Test
    public void deve_retornar_true_caso_dinheiro_carteira_seja_menor_do_que_zero() {
    	cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.DINHEIRO_CARTEIRA_PRECIA_SER_VALOR_MAIOR);
    	assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }

    @Test
    public void deve_retornar_true_caso_cpf_esteja_com_espaco_em_branco() {
        cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.CPF_BLANK_SPACE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }
  
    @Test
    public void deve_retornar_true_caso_cpf_esteja_vazio() {
    	cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.CPF_EMPTY);
    	assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_NAO_E_VALIDO));
    }
   
    @Test
    public void deve_retornar_true_caso_cpf_esteja_null() {
    	cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.CPF_NULL);
    	assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(cliente, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_cpf_esteja_com_caracteres_especiais() {
    	cliente = EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.CPF_WITH_SPECIAL_CARACTER);
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
        EqualsVerifier.forClass(Cliente.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
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
    public void deve_retornar_true_caso_contenha_a_palavra_telefones_no_toString() {
        assertTrue(cliente.toString().contains("telefones"));
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_carrinhoProdutos_no_toString() {
        assertTrue(cliente.toString().contains("carrinhoProdutos"));
    }

}
