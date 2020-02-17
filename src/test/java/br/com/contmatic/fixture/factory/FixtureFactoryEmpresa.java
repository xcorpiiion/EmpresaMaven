package br.com.contmatic.fixture.factory;

import java.util.ArrayList;
import java.util.HashSet;

import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixtureFactoryEmpresa.
 */
public class FixtureFactoryEmpresa implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        Fixture.of(Empresa.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("email", regex("[a-z]{10}@gmail.com|@hotmail.com"));
                add("cnpj", cnpj());
                add("enderecos", new HashSet<>());
                add("telefones", new HashSet<>());
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
        Fixture.of(Empresa.class).addTemplate("nomeLess3Caracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_LESS_3_CARACTER));
            }
        });
        Fixture.of(Empresa.class).addTemplate("nomeGreaterCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_GREATER_50_CARACTER));
            }
        });
        Fixture.of(Empresa.class).addTemplate("nomeWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_WITH_SPECIAL_CARACTER));
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
        Fixture.of(Empresa.class).addTemplate("emailLess10Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_LESS_10_CARACTER));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailGreater100Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_GREATER_100_CARACTER));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailWithBlankSpaceInWord").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_BLANK_SPACE));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailWithNumberAfterArroba").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_NUMBER_AFTER_ARROBA));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailWithoutArroba").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailWithoutPontoCom").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_PONTO_COM));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailWithoutCom").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_COM));
            }
        });
        Fixture.of(Empresa.class).addTemplate("emailWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_SPECIAL_CARACTER));
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
