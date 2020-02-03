package br.com.contmatic.fixture.factory;

import java.util.Random;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;


public class FixtureFactoryEndereco implements TemplateLoader{

    @Override
    public void load() {
        StringBuilder cepRandom = new StringBuilder();
        cepRandom.append(new Random().nextInt(90000000) + 10000000);
        int randomEstadoName = new Random().nextInt(EstadosBrasil.values().length);
        Fixture.of(Endereco.class).addTemplate("valid", new Rule() {
            {
                add("rua", name());
                add("bairro", name());
                add("cep", cepRandom.toString());
                add("numeroResidencia", random(Integer.class, range(1, 10000)));
                add("cidade", firstName());
                add("estado", EstadosBrasil.values()[randomEstadoName]);
                
            }
        }); 
        Fixture.of(Endereco.class).addTemplate("cidadeNull", new Rule() {
            {
                add("cidade", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("cidadeEmpty", new Rule() {
            {
                add("cidade", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cidadeBlankSpace", new Rule() {
            {
                add("cidade", " ");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cidadeContainsNumber", new Rule() {
            {
                add("cidade", random(Integer.class, range(1, 100)).toString());
            }
        });
        Fixture.of(Endereco.class).addTemplate("estadoNull", new Rule() {
            {
                add("estado", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("numeroResidenciaLessThanZero", new Rule() {
            {
                add("numeroResidencia", random(Integer.class, range(-10000, -1)));
            }
        });
        Fixture.of(Endereco.class).addTemplate("ruaNull", new Rule() {
            {
                add("rua", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("ruaEmpty", new Rule() {
            {
                add("rua", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("ruaBlankSpace", new Rule() {
            {
                add("rua", " ");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepNull", new Rule() {
            {
                add("cep", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepEmpty", new Rule() {
            {
                add("cep", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepBlankSpace", new Rule() {
            {
                add("cep", " ");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepLengthDifference8", new Rule() {
            {
                add("cep", cepRandom.append(new Random().nextInt(1) + 1000).toString());
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepContainsWord", new Rule() {
            {
                add("cep", cepRandom.append(new Random().nextInt(1) + 1000).append(firstName()).toString());
            }
        });
        Fixture.of(Endereco.class).addTemplate("bairroNull", new Rule() {
            {
                add("bairro", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("bairroEmpty", new Rule() {
            {
                add("bairro", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("bairroBlankSpace", new Rule() {
            {
                add("bairro", " ");
            }
        });
    }  
}
