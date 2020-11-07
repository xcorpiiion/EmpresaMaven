package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.CODIGO_FEDERAL_MENOR_QUE_1;
import static br.com.contmatic.constantes.Constante.CODIGO_FEDERAL_NULL;
import static br.com.contmatic.constantes.Constante.CODIGO_IBGE_MENOR_QUE_1;
import static br.com.contmatic.constantes.Constante.CODIGO_IBGE_NULL;
import static br.com.contmatic.constantes.Constante.CODIGO_MUNICIPAL_MENOR_QUE_1;
import static br.com.contmatic.constantes.Constante.CODIGO_MUNICIPAL_NULL;
import static br.com.contmatic.constantes.Constante.DESCRICAO_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.DESCRICAO_CARACTER_ESPECIAL;
import static br.com.contmatic.constantes.Constante.DESCRICAO_EMPTY;
import static br.com.contmatic.constantes.Constante.DESCRICAO_NULL;
import static br.com.contmatic.constantes.Constante.DESCRICAO_TAMANHO_INVALIDO;
import static br.com.contmatic.constantes.Constante.UF_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.six2six.fixturefactory.Fixture.of;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Random;

import com.github.javafaker.Faker;

import br.com.contmatic.endereco.Municipio;
import br.com.contmatic.enums.EnumUF;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixtureFactoryCliente.
 */
public class FixtureFactoryMunicipio implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        final String CODIGO_MUNICIPAL = "codigoMunicipal";
        final String CODIGO_FEDERAL = "codigoFederal";
        final String CODIGO_IBGE = "codigoIbge";
        final String DESCRICAO = "descricao";
        final String UF = "uf";
        of(Municipio.class).addTemplate(VALID, new Rule() {
            {
                add(CODIGO_MUNICIPAL, new Faker().number().numberBetween(1000, 10000));
                add(CODIGO_FEDERAL, new Faker().number().numberBetween(1000, 10000));
                add(CODIGO_IBGE, new Faker().number().numberBetween(1000, 10000));
                add(DESCRICAO, valueOf(new Faker().address().streetSuffix()));
                add(UF, EnumUF.values()[new Random().nextInt(EnumUF.values().length)]);
            }
        });
        of(Municipio.class).addTemplate(CODIGO_MUNICIPAL_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO_MUNICIPAL, null);
            }
        });
        of(Municipio.class).addTemplate(CODIGO_MUNICIPAL_MENOR_QUE_1).inherits(VALID, new Rule() {
            {
                add(CODIGO_MUNICIPAL, new Faker().number().numberBetween(1, 1000));
            }
        });
        of(Municipio.class).addTemplate(CODIGO_FEDERAL_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO_FEDERAL, null);
            }
        });
        of(Municipio.class).addTemplate(CODIGO_FEDERAL_MENOR_QUE_1).inherits(VALID, new Rule() {
            {
                add(CODIGO_FEDERAL, new Faker().number().numberBetween(1, 1000));
            }
        });
        of(Municipio.class).addTemplate(CODIGO_IBGE_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO_IBGE, null);
            }
        });
        of(Municipio.class).addTemplate(CODIGO_IBGE_MENOR_QUE_1).inherits(VALID, new Rule() {
            {
                add(CODIGO_IBGE, new Faker().number().numberBetween(1, 1000));
            }
        });
        of(Municipio.class).addTemplate(UF_NULL).inherits(VALID, new Rule() {
            {
                add(UF, null);
            }
        });
        of(Municipio.class).addTemplate(DESCRICAO_NULL).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, null);
            }
        });
        of(Municipio.class).addTemplate(DESCRICAO_EMPTY).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, EMPTY);
            }
        });
        of(Municipio.class).addTemplate(DESCRICAO_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, SPACE);
            }
        });
        of(Municipio.class).addTemplate(DESCRICAO_TAMANHO_INVALIDO).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, "FSD");
            }
        });
        of(Municipio.class).addTemplate(DESCRICAO_CARACTER_ESPECIAL).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, "$%#%%¨$¨$");
            }
        });
    }

}
