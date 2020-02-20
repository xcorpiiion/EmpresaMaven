package br.com.contmatic.fixture.factory;

import java.util.Random;

import br.com.contmatic.empresa.TipoContrato;
import br.com.contmatic.telefone.DddBrasil;
import br.com.contmatic.telefone.Telefone;
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
        
        Fixture.of(Telefone.class).addTemplate("valid", new Rule() {
            {
                add("phone", GeradorTelefone.geradorPhone());
                add("tipoTelefone", TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
                add("dddTelefone", DddBrasil.values()[new Random().nextInt(DddBrasil.values().length)]);
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
        Fixture.of(Telefone.class).addTemplate("tipoTelefoneNull").inherits("valid", new Rule() {
            {
                add("tipoTelefone", null);
            }
        });
        Fixture.of(Telefone.class).addTemplate("dddNull").inherits("valid", new Rule() {
            {
                add("dddTelefone", null);
            }
        });
    }
}
