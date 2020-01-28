package br.com.contmatic.fixture.factory;

import java.util.Random;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureFactoryEndereco {

    public static Endereco cidade() {
        Fixture.of(Endereco.class).addTemplate("cidade", new Rule() {{
            add("cidade", firstName());  
          }});
        return Fixture.from(Endereco.class).gimme("cidade");
    }
    
    public static Endereco cidadeNull() {
        Fixture.of(Endereco.class).addTemplate("cidadeNull", new Rule() {{
            add("cidade", null);  
          }});
        return Fixture.from(Endereco.class).gimme("cidadeNull");
    }
    
    public static Endereco cidadeEmpty() {
        Fixture.of(Endereco.class).addTemplate("cidadeEmpty", new Rule() {{
            add("cidade", "");  
          }});
        return Fixture.from(Endereco.class).gimme("cidadeEmpty");
    }
    
    public static Endereco cidadeBlankSpace() {
        Fixture.of(Endereco.class).addTemplate("cidadeBlankSpace", new Rule() {{
            add("cidade", " ");  
          }});
        return Fixture.from(Endereco.class).gimme("cidadeBlankSpace");
    }
    
    /* --------------------------------------------------------------------------- */
    
    public static Endereco estado() {
        EstadosBrasil estadosBrasil;

        Fixture.of(Endereco.class).addTemplate("estado", new Rule() {{
            add("estado", "" );  
          }});
        return Fixture.from(Endereco.class).gimme("estado");
    }
    
    public static Endereco estadoNull() {
        Fixture.of(Endereco.class).addTemplate("estadoNull", new Rule() {{
            add("estado", null);  
          }});
        return Fixture.from(Endereco.class).gimme("estadoNull");
    }
    
    public static Endereco estadoEmpty() {
        Fixture.of(Endereco.class).addTemplate("estadoEmpty", new Rule() {{
            add("estado", "");  
          }});
        return Fixture.from(Endereco.class).gimme("estadoEmpty");
    }

}
