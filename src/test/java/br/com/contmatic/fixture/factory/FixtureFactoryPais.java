package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.PAIS_MENOR_DO_QUE_ZERO;
import static br.com.contmatic.constantes.Constante.PAIS_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.six2six.fixturefactory.Fixture.of;
import static java.lang.Long.valueOf;

import com.github.javafaker.Faker;

import br.com.contmatic.endereco.Pais;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixtureFactoryCliente.
 */
public class FixtureFactoryPais implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        final String CODIGO = "codigo";
        of(Pais.class).addTemplate(VALID, new Rule() {
            {
                add(CODIGO, valueOf(new Faker().number().numberBetween(1000, 10000)));
            }
        });
        of(Pais.class).addTemplate(PAIS_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO, null);
            }
        });
        of(Pais.class).addTemplate(PAIS_MENOR_DO_QUE_ZERO).inherits(VALID, new Rule() {
            {
                add(CODIGO, -1l);
            }
        });
    }

}
