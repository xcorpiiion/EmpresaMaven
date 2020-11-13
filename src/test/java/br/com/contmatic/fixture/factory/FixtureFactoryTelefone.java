package br.com.contmatic.fixture.factory;

import br.com.contmatic.telefone.DddBrasil;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TipoTelefone;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;

import java.util.Random;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.six2six.fixturefactory.Fixture.of;
import static java.lang.String.valueOf;

public class FixtureFactoryTelefone implements TemplateLoader {

    private final String PHONE = "phone";

    private final String TIPO_TELEFONE = "tipoTelefone";

    private final String DDD_TELEFONE = "dddTelefone";

    @Override
    public void load() {

        of(Telefone.class).addTemplate(VALID, new Rule() {
            {
                {
                    add(PHONE, valueOf(new Faker().number().numberBetween(111111111, 988888888)));
                    add(TIPO_TELEFONE, TipoTelefone.values()[new Random().nextInt(TipoTelefone.values().length)]);
                    add(DDD_TELEFONE, DddBrasil.values()[new Random().nextInt(DddBrasil.values().length)]);
                }
            }
        });
        of(Telefone.class).addTemplate(PHONE_NULL).inherits(VALID, new Rule() {
            {
                add(PHONE, null);
            }
        });
        of(Telefone.class).addTemplate(PHONE_EMPTY).inherits(VALID, new Rule() {
            {
                add(PHONE, "");
            }
        });
        of(Telefone.class).addTemplate(PHONE_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(PHONE, " ");
            }
        });
        of(Telefone.class).addTemplate(PHONE_INVALID_SIZE).inherits(VALID, new Rule() {
            {
                add(PHONE, "1");
            }
        });
        of(Telefone.class).addTemplate(TIPO_TELEFONE_NULL).inherits(VALID, new Rule() {
            {
                add(TIPO_TELEFONE, null);
            }
        });
        of(Telefone.class).addTemplate(DDD_NULL).inherits(VALID, new Rule() {
            {
                add(DDD_TELEFONE, null);
            }
        });

    }

}
