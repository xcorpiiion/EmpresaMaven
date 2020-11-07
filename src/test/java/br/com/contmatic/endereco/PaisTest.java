package br.com.contmatic.endereco;

import org.junit.BeforeClass;
import org.junit.Test;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.CODIGO_PAIS_VAZIO;
import static br.com.contmatic.endereco.Pais.CODIGO_BRASIL;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.*;

public class PaisTest {
    
    private static Pais pais;

    @BeforeClass
    public static void dadosProdutos() {
        loadTemplates("br.com.contmatic.fixture.factory");
        pais = from(Pais.class).gimme(VALID);
    }

    @Test
    public void nao_deve_aceitar_codigo_null() {
        pais = from(Pais.class).gimme(PAIS_NULL);
        assertTrue(returnAnnotationMsgError(pais, CODIGO_PAIS_VAZIO));
    }

    @Test
    public void codigo_deve_ser_igual_codigo_do_brasil() {
        pais.setCodigo(CODIGO_BRASIL);
        assertSame(CODIGO_BRASIL, pais.getCodigo());
    }

    @Test
    public void codigo_deve_ser_do_exterior() {
        pais.setCodigo(1234L);
        assertTrue(pais.isExterior());
    }

    @Test
    public void codigo_nao_deve_ser_menor_do_que_1() {
        pais = from(Pais.class).gimme(PAIS_MENOR_DO_QUE_ZERO);
        assertFalse(pais.getCodigo().compareTo(0L) > 0);
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Pais.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

}
