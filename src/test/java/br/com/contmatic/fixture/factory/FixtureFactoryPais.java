package br.com.contmatic.fixture.factory;

import br.com.contmatic.empresa.Cliente;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;

import static br.com.contmatic.constantes.Constante.PAIS_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.six2six.fixturefactory.Fixture.of;

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
        of(Cliente.class).addTemplate(VALID, new Rule() {
            {
                add(CODIGO, new Faker().number().numberBetween(1000, 10000));
            }
        });
        of(Cliente.class).addTemplate(PAIS_NULL).inherits(VALID, new Rule() {
            {
                add(CODIGO, null);
            }
        });
    }

}
