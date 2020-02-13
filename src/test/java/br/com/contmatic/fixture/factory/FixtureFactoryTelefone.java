package br.com.contmatic.fixture.factory;

import java.util.Random;

import br.com.contmatic.empresa.Telefone;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.enums.TipoTelefone;
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
        
        Fixture.of(Telefone.class).addTemplate("valid", new Rule() {
            {
                add("phone", GeradorTelefone.geradorPhone());
                add("tipoTelefone", TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
            }
        });
        Fixture.of(Telefone.class).addTemplate("phoneNull").inherits("valid", new Rule() {
            {
                add("phone", null);
            }
        });
        Fixture.of(Telefone.class).addTemplate("phoneEmpty").inherits("valid", new Rule() {
            {
                add("phone", "");
            }
        });
        Fixture.of(Telefone.class).addTemplate("phoneBlankSpace").inherits("valid", new Rule() {
            {
                add("phone", " ");
            }
        });
        Fixture.of(Telefone.class).addTemplate("phoneInvalidSize").inherits("valid", new Rule() {
            {
                add("phone", "1");
            }
        });
        Fixture.of(Telefone.class).addTemplate("phoneDDDInvalid").inherits("valid", new Rule() {
            {
                add("phone", "10958004508");
            }
        });
        Fixture.of(Telefone.class).addTemplate("tipoTelefoneNull").inherits("valid", new Rule() {
            {
                add("tipoTelefone", null);
            }
        });
    }
}
