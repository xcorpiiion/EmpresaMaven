package br.com.contmatic.fixture.factory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Endereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureFactoryCliente {

    public static Endereco cidadeName() {
        Fixture.of(Endereco.class).addTemplate("cidadeName", new Rule() {
            {
                add("cidade", firstName());
            }
        });
        return Fixture.from(Endereco.class).gimme("cidadeName");
    }
    
    public static Cliente nomeCliente() {
        Fixture.of(Cliente.class).addTemplate("nome", new Rule() {
            {
                add("nome", firstName());
                add("email", firstName() + "@gmail.com");
                add("dataNascimento", new Date());
                add("dinheiroCarteira", new BigDecimal(0.0));
                add("endereco", new Endereco());
                add("carrinhoProduto", new ArrayList<>());
                add("produtosComprados", new ArrayList<>());
            }
        });
        return Fixture.from(Cliente.class).gimme("nome");
    }
    
    
    
    public static Cliente nomeClienteNull() {
        Fixture.of(Cliente.class).addTemplate("nomeClienteNull", new Rule() {
            {
                add("nome", null);
            }
        });
        return Fixture.from(Cliente.class).gimme("nomeClienteNull");
    }
    
    public static Cliente nomeClienteEmpty() {
        Fixture.of(Cliente.class).addTemplate("nomeClienteEmpty", new Rule() {
            {
                add("nome", "");
            }
        });
        return Fixture.from(Cliente.class).gimme("nomeClienteEmpty");
    }
    
    public static Cliente nomeClienteBlankSpace() {
        Fixture.of(Cliente.class).addTemplate("nomeClienteBlankSpace", new Rule() {
            {
                add("nome", " ");
            }
        });
        return Fixture.from(Cliente.class).gimme("nomeClienteBlankSpace");
    }
    
    /*-----------------------------------------------------------------------------------------*/
    
    public static Cliente emailCliente() {
        Fixture.of(Cliente.class).addTemplate("emailCliente", new Rule() {
            {
                add("email", firstName() + "@gmail.com");
            }
        });
        return Fixture.from(Cliente.class).gimme("emailCliente");
    }
    
    public static Cliente emailClienteNotVallid() {
        Fixture.of(Cliente.class).addTemplate("emailCliente", new Rule() {
            {
                add("email", firstName());
            }
        });
        return Fixture.from(Cliente.class).gimme("emailCliente");
    }
    
    public static Cliente emailClienteNull() {
        Fixture.of(Cliente.class).addTemplate("emailClienteNull", new Rule() {
            {
                add("email", null);
            }
        });
        return Fixture.from(Cliente.class).gimme("emailClienteNull");
    }
    
    public static Cliente emailClienteEmpty() {
        Fixture.of(Cliente.class).addTemplate("emailClienteEmpty", new Rule() {
            {
                add("email", "");
            }
        });
        return Fixture.from(Cliente.class).gimme("emailClienteEmpty");
    }
    
    public static Cliente emailClienteBlankSpace() {
        Fixture.of(Cliente.class).addTemplate("emailClienteBlankSpace", new Rule() {
            {
                add("email", " ");
            }
        });
        return Fixture.from(Cliente.class).gimme("emailClienteBlankSpace");
    }

    /*-----------------------------------------------------------------------------------------*/
    
    public static Cliente dataNascimentoCliente() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Fixture.of(Cliente.class).addTemplate("dataNascimentoCliente", new Rule() {
            {
                Date dataNascimente = null;
                Random random = new Random();
                
                Integer day = random.nextInt(31) + 1;
                Integer month = random.nextInt(12) + 1;
                Integer year = random.nextInt(2020) + 1930;
                try {
                    dataNascimente = simpleDateFormat.parse(day.toString() + "/" + month.toString() + "/" + year.toString());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }     
                add("dataNascimento", dataNascimente);
            }
        });
        return Fixture.from(Cliente.class).gimme("dataNascimentoCliente");
    }
    
    public static Cliente dataNascimmentoClienteNull() {
        Fixture.of(Cliente.class).addTemplate("dataNascimmentoClienteNull", new Rule() {
            {
                add("dataNascimento", null);
            }
        });
        return Fixture.from(Cliente.class).gimme("dataNascimmentoClienteNull");
    }

}
