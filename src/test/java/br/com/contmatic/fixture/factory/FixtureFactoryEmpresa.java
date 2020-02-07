package br.com.contmatic.fixture.factory;

import java.util.ArrayList;
import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FixtureFactoryEmpresa implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Empresa.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("email", "teste@gmail.com");
                add("cnpj", cnpj());
                add("endereco", FixtureFactoryEndereco.enderecoValido());
                add("produtos", new ArrayList<>());
                add("funcionarios", new ArrayList<>());
                add("clientes", new ArrayList<>());
            }
        });
        Fixture.of(Empresa.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        Fixture.of(Empresa.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        Fixture.of(Empresa.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailNull").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailEmpty").inherits("valid", new Rule() {
            {
                add("email", "");
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailBlankSpace").inherits("valid", new Rule() {
            {
                add("email", " ");
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailInvalid").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        Fixture.of(Empresa.class).addTemplate("cnpjNull").inherits("valid", new Rule() {
            {
                add("cnpj", "");
            }
        });
        Fixture.of(Empresa.class).addTemplate("cnpjBlankSpace").inherits("valid", new Rule() {
            {
                add("cnpj", " ");
            }
        });
        Fixture.of(Empresa.class).addTemplate("cnpjContainsWord").inherits("valid", new Rule() {
            {
                add("cnpj", "dsd454sd5s");
            }
        });
        Fixture.of(Empresa.class).addTemplate("cnpjWrongSize").inherits("valid", new Rule() {
            {
                add("cnpj", "123456879");
            }
        });
        Fixture.of(Empresa.class).addTemplate("enderecoNull").inherits("valid", new Rule() {
            {
                add("endereco", null);
            }
        });
        Fixture.of(Empresa.class).addTemplate("produtoNull").inherits("valid", new Rule() {
            {
                add("produtos", null);
            }
        });
        Fixture.of(Empresa.class).addTemplate("funcionarioNull").inherits("valid", new Rule() {
            {
                add("funcionarios", null);
            }
        });
        Fixture.of(Empresa.class).addTemplate("clienteNull").inherits("valid", new Rule() {
            {
                add("clientes", null);
            }
        });

    }

}
