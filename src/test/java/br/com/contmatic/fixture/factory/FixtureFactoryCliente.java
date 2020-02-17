package br.com.contmatic.fixture.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.joda.time.DateTime;

import br.com.contmatic.empresa.Cliente;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixtureFactoryCliente.
 */
public class FixtureFactoryCliente implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        
        Fixture.of(Cliente.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("email", regex(GeradorEmail.VALID_EMAIL));
                add("dataNascimento", new DateTime());
                add("dinheiroCarteira", new BigDecimal(4000.00 + (new Random().nextDouble() * (5000 - 8500))));
                add("carrinhoProdutos", new ArrayList<>());
                add("produtosComprados", new ArrayList<>());
                add("endereco", FixtureFactoryEndereco.enderecoValido());
                add("cpf", GeradorCpf.gerardorRandomCpf());
                add("telefones", new HashSet<>());
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeLess3Caracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_LESS_3_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeGreaterCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_GREATER_50_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_WITH_SPECIAL_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailNull").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailEmpty").inherits("valid", new Rule() {
            {
                add("email", "");
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailBlankSpace").inherits("valid", new Rule() {
            {
                add("email", " ");
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailInvalid").inherits("valid", new Rule() {
            {
                add("email", firstName());
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailLess10Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_LESS_10_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailGreater100Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_GREATER_100_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailWithBlankSpaceInWord").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_BLANK_SPACE));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailWithNumberAfterArroba").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_NUMBER_AFTER_ARROBA));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailWithoutArroba").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailWithoutPontoCom").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_PONTO_COM));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailWithoutCom").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_COM));
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        Fixture.of(Cliente.class).addTemplate("enderecoNull").inherits("valid", new Rule() {
            {
                add("endereco", null);
            }
        });
        Fixture.of(Cliente.class).addTemplate("dataNascimentoNull").inherits("valid", new Rule() {
            {
                add("dataNascimento", null);
            }
        });
        Fixture.of(Cliente.class).addTemplate("telefoneNull").inherits("valid", new Rule() {
            {
                add("telefones", null);
            }
        });
    }

}
