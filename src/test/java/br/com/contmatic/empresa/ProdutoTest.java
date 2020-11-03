package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.NOME_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.NOME_EMPTY;
import static br.com.contmatic.constantes.Constante.NOME_GREATER_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_NULL;
import static br.com.contmatic.constantes.Constante.PRECO_LESS_1;
import static br.com.contmatic.constantes.Constante.PRECO_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.contmatic.constantes.Mensagem.NOME_PRODUTO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.NOME_PRODUTO_VAZIO;
import static br.com.contmatic.constantes.Mensagem.PRECO_PRODUTO_TAMANHO;
import static br.com.contmatic.constantes.Mensagem.PRECO_PRODUTO_VAZIO;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class ProdutoTest.
 */
public class ProdutoTest {

    /** The produto. */
    private static Produto produto;
    
    private static Produto produto2;

    /**
     * Dados produtos.
     */
    @BeforeClass
    public static void dadosProdutos() {
        loadTemplates("br.com.contmatic.fixture.factory");
        produto = from(Produto.class).gimme(VALID);
    }

    /**
     * Deve armazenar nome null e armazenar no validation annotation.
     */
    @Test
    public void deve_armazenar_nome_null_e_armazenar_no_validationAnnotation() {
        produto = from(Produto.class).gimme(NOME_NULL);
        assertNull(produto.getNome());
        assertTrue(returnAnnotationMsgError(produto, NOME_PRODUTO_VAZIO));
    }

    /**
     * Nao deve aceitar nome vazio.
     */
    @Test
    public void nao_deve_aceitar_nome_vazio() {
        produto = from(Produto.class).gimme(NOME_EMPTY);
        assertTrue(produto.getNome().trim().isEmpty());
    }

    /**
     * Deve aceitar nome com espaco em branco.
     */
    @Test
    public void deve_aceitar_nome_com_espaco_em_branco() {
        produto = from(Produto.class).gimme(NOME_BLANK_SPACE);
        assertTrue(produto.getNome().trim().isEmpty());

    }

    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
        produto = from(Produto.class).gimme(NOME_LESS_3_CARACTER);
        assertTrue(returnAnnotationMsgError(produto, NOME_PRODUTO_TAMANHO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
        produto = from(Produto.class).gimme(NOME_GREATER_CARACTER);
        assertTrue(returnAnnotationMsgError(produto, NOME_PRODUTO_TAMANHO));
    }

    /**
     * Deve retornar mensagem caso preco seja null.
     */
    @Test
    public void deve_retornar_mensagem_caso_preco_seja_null() {
        produto = from(Produto.class).gimme(PRECO_NULL);
        assertTrue(returnAnnotationMsgError(produto, PRECO_PRODUTO_VAZIO));
    }

    /**
     * Deve retornar mensagem caso estoque seja menor que um.
     */
    @Test
    public void deve_retornar_mensagem_caso_estoque_seja_menor_que_um() {
        produto = from(Produto.class).gimme(PRECO_LESS_1);
        assertTrue(returnAnnotationMsgError(produto, PRECO_PRODUTO_TAMANHO));
    }

    /**
     * Deve aceitar estoque que nao seja negativo.
     */
    @Test
    public void deve_aceitar_estoque_que_nao_seja_negativo() {
        produto.setEstoque(5);
        assertThat(produto.getEstoque(), is(5));
    }

    /**
     * Nao deve aceitar produtos iguais.
     */
    @Test()
    public void nao_deve_aceitar_produtos_iguais() {
        produto2 = from(Produto.class).gimme(VALID);
        produto2.setNome(produto.getNome());
        produto2.setPreco(produto.getPreco());
        assertEquals("Os produtos são iguais", produto, produto2);
    }
    
    @Test()
    public void deve_retornar_false_caso_compare_com_um_valor_null() {
        assertNotEquals("Os produtos são iguais", null, produto);
    }
    
    @Test()
    public void deve_retornar_true_caso_compare_com_mesmo_objeto() {
        assertEquals("Os produtos são iguais", produto, produto);
    }
    
    @Test()
    public void deve_retornar_false_caso_compare_com_classe_diferente() {
        assertNotEquals("Os produtos são iguais", produto, new Object());
    }

    /**
     * Deve ser iguais caso possua mesmo hashcode.
     */
    @Test()
    public void deve_ser_iguais_caso_possua_mesmo_hashcode() {
        produto2 = from(Produto.class).gimme(VALID);
        produto2.setNome(produto.getNome());
        produto2.setPreco(produto.getPreco());
        assertEquals("Os produtos são iguais", produto.hashCode(), produto.hashCode());
    }

    /**
     * Mostrar dados.
     */
    @AfterClass
    public static void mostrarDados() {
        System.out.println(produto);
    }

}
