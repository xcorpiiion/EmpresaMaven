package br.com.contmatic.fixture.factory;

import br.com.contmatic.empresa.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FixtureFactoryTelefone implements TemplateLoader {
    
    @Override
    public void load() {
        
        Fixture.of(Telefone.class).addTemplate("valid", new Rule() {
            {
                add("phone", GeradorTelefone.geradorPhone());
                add("cellphone", GeradorTelefone.geradorCellPhone());
            }
        });
        Fixture.of(Telefone.class).addTemplate("phoneNull").inherits("valid", new Rule() {
            {
                add("phone", null);
            }
        });
    }
}
