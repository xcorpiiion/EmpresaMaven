package br.com.contmatic.fixture.factory;

import java.util.Random;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;


/**
 * The Class FixtureFactoryEndereco.
 */
public class FixtureFactoryEndereco implements TemplateLoader{

    /**
     * Endereco valido.
     *
     * @return the endereco
     */
    public static Endereco enderecoValido() {
        StringBuilder cepRandom = new StringBuilder();
        cepRandom.append(new Random().nextInt(90000000) + 10000000);
        Fixture.of(Endereco.class).addTemplate("valid", new Rule() {
            {
                add("rua", name());
                add("bairro", name());
                add("cep", cepRandom.toString());
                add("numeroResidencia", new Random().nextInt(100000) + 1);
                add("cidade", firstName());
                add("estado", EstadosBrasil.values()[new Random().nextInt(EstadosBrasil.values().length)]);
                
            }
        });
        return Fixture.from(Endereco.class).gimme("valid");
    }
    
    /**
     * Load.
     */
    @Override
    public void load() {
        FixtureFactoryEndereco.enderecoValido();
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
        Fixture.of(Endereco.class).addTemplate("cidadeNull").inherits("valid", new Rule() {
            {
                add("cidade", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("cidadeEmpty").inherits("valid", new Rule() {
            {
                add("cidade", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cidadeBlankSpace").inherits("valid", new Rule() {
            {
                add("cidade", " ");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cidadeContainsNumber").inherits("valid", new Rule() {
            {
                add("cidade", random(Integer.class, range(1, 100)).toString());
            }
        });
        Fixture.of(Endereco.class).addTemplate("estadoNull").inherits("valid", new Rule() {
            {
                add("estado", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("numeroResidenciaLessThanZero").inherits("valid", new Rule() {
            {
                add("numeroResidencia", random(Integer.class, range(-10000, -1)));
            }
        });
        Fixture.of(Endereco.class).addTemplate("ruaNull").inherits("valid", new Rule() {
            {
                add("rua", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("ruaEmpty").inherits("valid", new Rule() {
            {
                add("rua", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("ruaBlankSpace").inherits("valid", new Rule() {
            {
                add("rua", " ");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepNull").inherits("valid", new Rule() {
            {
                add("cep", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepEmpty").inherits("valid", new Rule() {
            {
                add("cep", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepBlankSpace").inherits("valid", new Rule() {
            {
                add("cep", " ");
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepLengthDifference8").inherits("valid", new Rule() {
            {
                add("cep", cepRandom.append(new Random().nextInt(1) + 1000).toString());
            }
        });
        Fixture.of(Endereco.class).addTemplate("cepContainsWord", new Rule() {
            {
                add("cep", cepRandom.append(new Random().nextInt(1) + 1000).append(firstName()).toString());
            }
        });
        Fixture.of(Endereco.class).addTemplate("bairroNull").inherits("valid", new Rule() {
            {
                add("bairro", null);
            }
        });
        Fixture.of(Endereco.class).addTemplate("bairroEmpty").inherits("valid", new Rule() {
            {
                add("bairro", "");
            }
        });
        Fixture.of(Endereco.class).addTemplate("bairroBlankSpace").inherits("valid", new Rule() {
            {
                add("bairro", " ");
            }
        });
    }  
}
