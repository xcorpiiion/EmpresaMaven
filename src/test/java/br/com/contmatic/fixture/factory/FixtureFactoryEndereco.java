package br.com.contmatic.fixture.factory;

import java.util.Random;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;


public class FixtureFactoryEndereco {

    public static Endereco cidadeName() {
        Fixture.of(Endereco.class).addTemplate("cidadeName", new Rule() {
            {
                add("cidade", firstName());
            }
        });
        return Fixture.from(Endereco.class).gimme("cidadeName");
    }

    public static Endereco cidadeNull() {
        Fixture.of(Endereco.class).addTemplate("cidadeNull", new Rule() {
            {
                add("cidade", null);
            }
        });
        return Fixture.from(Endereco.class).gimme("cidadeNull");
    }

    public static Endereco cidadeEmpty() {
        Fixture.of(Endereco.class).addTemplate("cidadeEmpty", new Rule() {
            {
                add("cidade", "");
            }
        });
        return Fixture.from(Endereco.class).gimme("cidadeEmpty");
    }

    public static Endereco cidadeBlankSpace() {
        Fixture.of(Endereco.class).addTemplate("cidadeBlankSpace", new Rule() {
            {
                add("cidade", " ");
            }
        });
        return Fixture.from(Endereco.class).gimme("cidadeBlankSpace");
    }

    /* --------------------------------------------------------------------------- */

    public static Endereco estadoName() {
        int randomEstadoName = new Random().nextInt(EstadosBrasil.values().length);
        Fixture.of(Endereco.class).addTemplate("estadoName", new Rule() {
            {
                add("estado", EstadosBrasil.values()[randomEstadoName]);
            }
        });
        return Fixture.from(Endereco.class).gimme("estadoName");
    }

    public static Endereco estadoNull() {
        Fixture.of(Endereco.class).addTemplate("estadoNull", new Rule() {
            {
                add("estado", null);
            }
        });
        return Fixture.from(Endereco.class).gimme("estadoNull");
    }

    /* --------------------------------------------------------------------------- */

    public static Endereco numeroResidencia() {
        Fixture.of(Endereco.class).addTemplate("numeroResidencia", new Rule() {
            {
                add("numeroResidencia", random(Integer.class, range(1, 10000)));
            }
        });
        return Fixture.from(Endereco.class).gimme("numeroResidencia");
    }

    public static Endereco numeroResidenciaLessThanZero() {
        Fixture.of(Endereco.class).addTemplate("numeroResidenciaLessThanZero", new Rule() {
            {
                add("numeroResidencia", random(Integer.class, range(-10000, -1)));
            }
        });
        return Fixture.from(Endereco.class).gimme("numeroResidenciaLessThanZero");
    }

    /* --------------------------------------------------------------------------- */

    public static Endereco ruaName() {
        Fixture.of(Endereco.class).addTemplate("ruaName", new Rule() {
            {
                add("rua", lastName());
            }
        });
        return Fixture.from(Endereco.class).gimme("ruaName");
    }

    public static Endereco ruaNull() {
        Fixture.of(Endereco.class).addTemplate("ruaNull", new Rule() {
            {
                add("rua", null);
            }
        });
        return Fixture.from(Endereco.class).gimme("ruaNull");
    }

    public static Endereco ruaEmpty() {
        Fixture.of(Endereco.class).addTemplate("ruaEmpty", new Rule() {
            {
                add("rua", "");
            }
        });
        return Fixture.from(Endereco.class).gimme("ruaEmpty");
    }

    public static Endereco ruaBlankSpace() {
        Fixture.of(Endereco.class).addTemplate("ruaBlankSpace", new Rule() {
            {
                add("rua", " ");
            }
        });
        return Fixture.from(Endereco.class).gimme("ruaBlankSpace");
    }

    /* --------------------------------------------------------------------------- */

    public static Endereco cepStringNumber() {
        Random randomCep = new Random();
        StringBuilder auxRandom = new StringBuilder();
        auxRandom.append(randomCep.nextInt(90000000) + 10000000);
        Fixture.of(Endereco.class).addTemplate("cepStringNumber", new Rule() {
            {
                add("cep", auxRandom.toString());
            }
        });
        return Fixture.from(Endereco.class).gimme("cepStringNumber");
    }

    public static Endereco cepNull() {
        Fixture.of(Endereco.class).addTemplate("cepNull", new Rule() {
            {
                add("rua", null);
            }
        });
        return Fixture.from(Endereco.class).gimme("cepNull");
    }

    public static Endereco cepEmpty() {
        Fixture.of(Endereco.class).addTemplate("cepEmpty", new Rule() {
            {
                add("rua", "");
            }
        });
        return Fixture.from(Endereco.class).gimme("cepEmpty");
    }

    public static Endereco cepBlankSpace() {
        Fixture.of(Endereco.class).addTemplate("cepBlankSpace", new Rule() {
            {
                add("rua", " ");
            }
        });
        return Fixture.from(Endereco.class).gimme("cepBlankSpace");
    }

    /* --------------------------------------------------------------------------- */

    public static Endereco bairroName() {
        Fixture.of(Endereco.class).addTemplate("bairroName", new Rule() {
            {
                add("bairro", firstName());
            }
        });
        return Fixture.from(Endereco.class).gimme("bairroName");
    }

    public static Endereco bairroNull() {
        Fixture.of(Endereco.class).addTemplate("bairroNull", new Rule() {
            {
                add("bairro", null);
            }
        });
        return Fixture.from(Endereco.class).gimme("bairroNull");
    }

    public static Endereco bairroEmpty() {
        Fixture.of(Endereco.class).addTemplate("bairroEmpty", new Rule() {
            {
                add("bairro", "");
            }
        });
        return Fixture.from(Endereco.class).gimme("bairroEmpty");
    }

    public static Endereco bairroBlankSpace() {
        Fixture.of(Endereco.class).addTemplate("bairroBlankSpace", new Rule() {
            {
                add("bairro", " ");
            }
        });
        return Fixture.from(Endereco.class).gimme("bairroBlankSpace");
    }
    
}
