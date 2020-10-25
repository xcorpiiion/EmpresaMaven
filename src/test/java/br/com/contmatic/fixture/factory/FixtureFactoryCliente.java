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

import static br.com.contmatic.fixture.factory.FixtureFactoryEndereco.enderecoValido;
import static br.com.contmatic.fixture.factory.GeradorCpf.gerardorRandomCpf;
import static br.com.contmatic.fixture.factory.GeradorEmail.*;
import static br.com.contmatic.fixture.factory.GeradorNome.*;
import static br.com.six2six.fixturefactory.Fixture.of;

/**
 * The Class FixtureFactoryCliente.
 */
public class FixtureFactoryCliente implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        
        of(Cliente.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("email", regex(VALID_EMAIL));
                add("dataNascimento", new DateTime());
                add("dinheiroCarteira", new BigDecimal(4000.00 + (new Random().nextDouble() * (5000 - 8500))));
                add("carrinhoProdutos", new ArrayList<>());
                add("produtosComprados", new ArrayList<>());
                add("endereco", enderecoValido());
                add("cpf", gerardorRandomCpf());
                add("telefones", new HashSet<>());
            }
        });
        of(Cliente.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        of(Cliente.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        of(Cliente.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        of(Cliente.class).addTemplate("nomeLess3Caracter").inherits("valid", new Rule() {
            {
                add("nome", regex(NOME_LESS_3_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("nomeGreaterCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(NOME_GREATER_50_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("nomeWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(NOME_WITH_SPECIAL_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("emailNull").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        of(Cliente.class).addTemplate("emailEmpty").inherits("valid", new Rule() {
            {
                add("email", "");
            }
        });
        of(Cliente.class).addTemplate("emailBlankSpace").inherits("valid", new Rule() {
            {
                add("email", " ");
            }
        });
        of(Cliente.class).addTemplate("emailInvalid").inherits("valid", new Rule() {
            {
                add("email", firstName());
            }
        });
        of(Cliente.class).addTemplate("emailLess10Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_LESS_10_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("emailGreater100Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_GREATER_100_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("emailWithBlankSpaceInWord").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITH_BLANK_SPACE));
            }
        });
        of(Cliente.class).addTemplate("emailWithNumberAfterArroba").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITH_NUMBER_AFTER_ARROBA));
            }
        });
        of(Cliente.class).addTemplate("emailWithoutArroba").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("emailWithoutPontoCom").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITHOUT_PONTO_COM));
            }
        });
        of(Cliente.class).addTemplate("emailWithoutCom").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITHOUT_COM));
            }
        });
        of(Cliente.class).addTemplate("emailWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("email", regex(EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        of(Cliente.class).addTemplate("enderecoNull").inherits("valid", new Rule() {
            {
                add("endereco", null);
            }
        });
        of(Cliente.class).addTemplate("dataNascimentoNull").inherits("valid", new Rule() {
            {
                add("dataNascimento", null);
            }
        });
        of(Cliente.class).addTemplate("telefoneNull").inherits("valid", new Rule() {
            {
                add("telefones", null);
            }
        });
    }

}
