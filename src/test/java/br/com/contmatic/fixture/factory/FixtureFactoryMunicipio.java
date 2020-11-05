package br.com.contmatic.fixture.factory;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.enums.EnumUF;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;

import java.util.Random;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.six2six.fixturefactory.Fixture.of;
import static java.lang.String.valueOf;

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
        of(Cliente.class).addTemplate(VALID, new Rule() {
            {
                add(CODIGO_MUNICIPAL, new Faker().number().numberBetween(1000, 10000));
                add(CODIGO_FEDERAL, new Faker().number().numberBetween(1000, 10000));
                add(CODIGO_IBGE, new Faker().number().numberBetween(1000, 10000));
                add(DESCRICAO, valueOf(new Faker().address().streetSuffix()));
                add(UF, EnumUF.values()[new Random().nextInt(EnumUF.values().length)]);
            }
        });
        of(Cliente.class).addTemplate(CODIGO_MUNICIPAL_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO_MUNICIPAL, null);
            }
        });
        of(Cliente.class).addTemplate(CODIGO_FEDERAL_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO_FEDERAL, null);
            }
        });
        of(Cliente.class).addTemplate(CODIGO_IBGE_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO_IBGE, null);
            }
        });
        of(Cliente.class).addTemplate(UF_NULL).inherits(VALID, new Rule() {
            {
                add(UF, null);
            }
        });
        of(Cliente.class).addTemplate(DESCRICAO_NULL).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, null);
            }
        });
        of(Cliente.class).addTemplate(DESCRICAO_EMPTY).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, null);
            }
        });
        of(Cliente.class).addTemplate(DESCRICAO_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(DESCRICAO, null);
            }
        });
    }

}
