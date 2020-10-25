package br.com.contmatic.fixture.factory;

import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.HashSet;

import static br.com.contmatic.fixture.factory.GeradorEmail.*;
import static br.com.contmatic.fixture.factory.GeradorNome.*;
import static br.com.six2six.fixturefactory.Fixture.of;

/**
 * The Class FixtureFactoryEmpresa.
 */
public class FixtureFactoryEmpresa implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        of(Empresa.class).addTemplate("valid", new Rule() {
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
        of(Empresa.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        of(Empresa.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        of(Empresa.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        of(Empresa.class).addTemplate("nomeLess3Caracter").inherits("valid", new Rule() {
            {
                add("nome", regex(NOME_LESS_3_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("nomeGreaterCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(NOME_GREATER_50_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("nomeWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(NOME_WITH_SPECIAL_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("emailNull").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        of(Empresa.class).addTemplate("emailEmpty").inherits("valid", new Rule() {
            {
                add("email", "");
            }
        });
        of(Empresa.class).addTemplate("emailBlankSpace").inherits("valid", new Rule() {
            {
                add("email", " ");
            }
        });
        of(Empresa.class).addTemplate("emailInvalid").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        of(Empresa.class).addTemplate("emailLess10Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_LESS_10_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("emailGreater100Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_GREATER_100_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("emailWithBlankSpaceInWord").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITH_BLANK_SPACE));
            }
        });
        of(Empresa.class).addTemplate("emailWithNumberAfterArroba").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITH_NUMBER_AFTER_ARROBA));
            }
        });
        of(Empresa.class).addTemplate("emailWithoutArroba").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("emailWithoutPontoCom").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITHOUT_PONTO_COM));
            }
        });
        of(Empresa.class).addTemplate("emailWithoutCom").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITHOUT_COM));
            }
        });
        of(Empresa.class).addTemplate("emailWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        of(Empresa.class).addTemplate("cnpjNull").inherits("valid", new Rule() {
            {
                add("cnpj", "");
            }
        });
        of(Empresa.class).addTemplate("cnpjBlankSpace").inherits("valid", new Rule() {
            {
                add("cnpj", " ");
            }
        });
        of(Empresa.class).addTemplate("cnpjContainsWord").inherits("valid", new Rule() {
            {
                add("cnpj", "dsd454sd5s");
            }
        });
        of(Empresa.class).addTemplate("cnpjWrongSize").inherits("valid", new Rule() {
            {
                add("cnpj", "123456879");
            }
        });
        of(Empresa.class).addTemplate("enderecoNull").inherits("valid", new Rule() {
            {
                add("endereco", null);
            }
        });
        of(Empresa.class).addTemplate("produtoNull").inherits("valid", new Rule() {
            {
                add("produtos", null);
            }
        });
        of(Empresa.class).addTemplate("funcionarioNull").inherits("valid", new Rule() {
            {
                add("funcionarios", null);
            }
        });
        of(Empresa.class).addTemplate("clienteNull").inherits("valid", new Rule() {
            {
                add("clientes", null);
            }
        });

    }

}
