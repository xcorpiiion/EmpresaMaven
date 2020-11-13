package br.com.contmatic.empresa;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.*;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProdutoTest {

    private static Produto produto;

    @BeforeClass
    public static void dadosProdutos() {
        loadTemplates("br.com.contmatic.fixture.factory");
        produto = from(Produto.class).gimme(VALID);
    }

    @Test
    public void deve_armazenar_nome_null_e_armazenar_no_validationAnnotation() {
        produto = from(Produto.class).gimme(NOME_NULL);
        assertNull(produto.getNome());
        assertTrue(returnAnnotationMsgError(produto, NOME_PRODUTO_VAZIO));
    }

    @Test
    public void nao_deve_aceitar_nome_vazio() {
        produto = from(Produto.class).gimme(NOME_EMPTY);
        assertTrue(produto.getNome().trim().isEmpty());
    }

    @Test
    public void deve_aceitar_nome_com_espaco_em_branco() {
        produto = from(Produto.class).gimme(NOME_BLANK_SPACE);
        assertTrue(produto.getNome().trim().isEmpty());

    }

    @Test
    public void deve_retornar_mensagem_caso_preco_seja_null() {
        produto = from(Produto.class).gimme(PRECO_NULL);
        assertTrue(returnAnnotationMsgError(produto, PRECO_PRODUTO_VAZIO));
    }

    @Test
    public void deve_retornar_mensagem_caso_estoque_seja_menor_que_um() {
        produto = from(Produto.class).gimme(PRECO_LESS_1);
        assertTrue(returnAnnotationMsgError(produto, PRECO_PRODUTO_TAMANHO));
    }

    @Test
    public void deve_aceitar_estoque_que_nao_seja_negativo() {
        produto.setEstoque(5);
        assertThat(produto.getEstoque(), is(5));
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Produto.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    @AfterClass
    public static void mostrarDados() {
        System.out.println(produto);
    }

}