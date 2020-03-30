package br.com.contmatic.empresa;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.easyrandom.EasyRandomProduto;
import br.com.contmatic.easyrandom.TipoDadoParaTesteProduto;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * The Class ProdutoTest.
 */
public class ProdutoTest {

    /** The produto. */
    private static Produto produto;
    
    /**
     * Dados produtos.
     */
    @BeforeClass
    public static void dadosProdutos() {
        produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO).nextObject(Produto.class);
    }

    /**
     * Deve armazenar nome null e armazenar no validation annotation.
     */
    @Test
    public void deve_armazenar_nome_null_e_armazenar_no_validationAnnotation() {
        produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.NOME_NULL).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_ESTA_NULLO));
    }

    /**
     * Nao deve aceitar nome vazio.
     */
    @Test
    public void nao_deve_aceitar_nome_vazio() {
        produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.NOME_EMPTY).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_ESTA_VAZIO));
    }

    /**
     * Deve aceitar nome com espaco em branco.
     */
    @Test
    public void deve_aceitar_nome_com_espaco_em_branco() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.NOME_BLANK_SPACE).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_ESTA_VAZIO));

    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.NOME_INVALID_SIZE).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.NOME_E_MUITO_PEQUENO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.NOME__WITH_SPECIAL_CARACTER).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_NAO_E_VALIDO));
    }
    /**
     * Deve retornar mensagem caso preco seja null.
     */
    @Test
    public void deve_retornar_true_caso_preco_seja_null() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.PRECO_NULL).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_ESTA_NULLO));
    }

    /**
     * Deve retornar mensagem caso estoque seja menor que um.
     */
    @Test
    public void deve_retornar_true_caso_estoque_seja_menor_que_um() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.PRECO_INVALIDO).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
    }
    
    @Test
    public void deve_retornar_true_estoque_seja_null() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.ESTOQUE_NULL).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_estoque_seja_invalido() {
    	produto = EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.ESTOQUE_INVALIDO).nextObject(Produto.class);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(produto, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test()
    public void deve_retornar_false_caso_compare_com_um_valor_null() {
        EqualsVerifier.forClass(Produto.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS, Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED).verify();
    }

    /**
     * Deve ser iguais caso possua mesmo hashcode.
     */

    /**
     * Mostrar dados.
     */
    @AfterClass
    public static void mostrarDados() {
        System.out.println(produto);
    }

}
