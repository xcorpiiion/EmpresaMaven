package br.com.contmatic.endereco;

import org.junit.BeforeClass;
import org.junit.Test;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.constantes.Mensagem.DESCRICAO_MUNICIPIO_CARACTERE_INVALIDO;
import static br.com.contmatic.validator.ValidadorAnnotionsMsgErro.returnAnnotationMsgError;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.*;

public class MunicipioTest {

    private static Municipio municipio;

    @BeforeClass
    public static void dadosProdutos() {
        loadTemplates("br.com.contmatic.fixture.factory");
        municipio = from(Municipio.class).gimme(VALID);
    }

    @Test
    public void nao_deve_aceitar_codigo_municipal_null() {
        municipio = from(Municipio.class).gimme(CODIGO_MUNICIPAL_NULL);
        assertNull(municipio.getCodigoMunicipal());
    }

    @Test
    public void nao_deve_aceitar_codigo_municipal_menor_do_que_1() {
        municipio = from(Municipio.class).gimme(CODIGO_MUNICIPAL_MENOR_QUE_1);
        assertFalse(municipio.getCodigoMunicipal().compareTo(1) < 0);
    }

    @Test
    public void nao_deve_aceitar_codigo_federal_null() {
        municipio = from(Municipio.class).gimme(CODIGO_FEDERAL_NULL);
        assertNull(municipio.getCodigoFederal());
    }

    @Test
    public void nao_deve_aceitar_codigo_federal_menor_do_que_1() {
        municipio = from(Municipio.class).gimme(CODIGO_FEDERAL_MENOR_QUE_1);
        assertFalse(municipio.getCodigoFederal().compareTo(1) < 0);
    }

    @Test
    public void nao_deve_aceitar_codigo_ibge_null() {
        municipio = from(Municipio.class).gimme(CODIGO_IBGE_NULL);
        assertNull(municipio.getCodigoIbge());
    }

    @Test
    public void nao_deve_aceitar_codigo_ibge_menor_do_que_1() {
        municipio = from(Municipio.class).gimme(CODIGO_IBGE_MENOR_QUE_1);
        assertFalse(municipio.getCodigoIbge() < 1);
    }

    @Test
    public void nao_deve_aceitar_descricao_null() {
        municipio = from(Municipio.class).gimme(DESCRICAO_NULL);
        assertNull(municipio.getDescricao());
    }

    @Test
    public void nao_deve_aceitar_descricao_vazio() {
        municipio = from(Municipio.class).gimme(DESCRICAO_EMPTY);
        assertFalse(!municipio.getDescricao().equals(EMPTY));
    }

    @Test
    public void nao_deve_aceitar_descricao_blank_space() {
        municipio = from(Municipio.class).gimme(DESCRICAO_BLANK_SPACE);
        assertFalse(!municipio.getDescricao().equals(SPACE));
    }

    @Test
    public void nao_deve_aceitar_descricao_special_caractere() {
        municipio = from(Municipio.class).gimme(DESCRICAO_CARACTER_ESPECIAL);
        assertTrue(returnAnnotationMsgError(municipio, DESCRICAO_MUNICIPIO_CARACTERE_INVALIDO));
    }

    @Test
    public void nao_deve_aceitar_descricao_com_menos_5_caracteres() {
        municipio = from(Municipio.class).gimme(DESCRICAO_CARACTER_ESPECIAL);
        assertFalse(municipio.getDescricao().length() < 5);
    }

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Municipio.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

}