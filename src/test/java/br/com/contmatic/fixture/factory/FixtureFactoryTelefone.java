package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.DDD_NULL;
import static br.com.contmatic.constantes.Constante.PHONE_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.PHONE_EMPTY;
import static br.com.contmatic.constantes.Constante.PHONE_INVALID_SIZE;
import static br.com.contmatic.constantes.Constante.PHONE_NULL;
import static br.com.contmatic.constantes.Constante.TIPO_TELEFONE_NULL;

import java.util.Random;

import br.com.contmatic.telefone.DddBrasil;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TipoContrato;
import br.com.contmatic.telefone.TipoTelefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixtureFactoryTelefone.
 */
public class FixtureFactoryTelefone implements TemplateLoader {
    
    /**
     * Load.
     */
    @Override
    public void load() {
        final String PHONE = "phone";
        final String TIPO_TELEFONE = "tipoTelefone";
        final String DDD_TELEFONE = "dddTelefone";
        final String VALID = "valid";
        Fixture.of(Telefone.class).addTemplate(VALID, new Rule() {
            {
                add(PHONE, GeradorTelefone.geradorPhone());
                add(TIPO_TELEFONE, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
                add(DDD_TELEFONE, DddBrasil.values()[new Random().nextInt(DddBrasil.values().length)]);
            }
        });
        Fixture.of(Telefone.class).addTemplate(PHONE_NULL).inherits(VALID, new Rule() {
            {
                add(PHONE, null);
            }
        });
        Fixture.of(Telefone.class).addTemplate(PHONE_EMPTY).inherits(VALID, new Rule() {
            {
                add(PHONE, "");
            }
        });
        Fixture.of(Telefone.class).addTemplate(PHONE_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(PHONE, " ");
            }
        });
        Fixture.of(Telefone.class).addTemplate(PHONE_INVALID_SIZE).inherits(VALID, new Rule() {
            {
                add(PHONE, "1");
            }
        });
        Fixture.of(Telefone.class).addTemplate(TIPO_TELEFONE_NULL).inherits(VALID, new Rule() {
            {
                add(TIPO_TELEFONE, null);
            }
        });
        Fixture.of(Telefone.class).addTemplate(DDD_NULL).inherits(VALID, new Rule() {
            {
                add(DDD_TELEFONE, null);
            }
        });
    }
}
