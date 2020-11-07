package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.DATA_NASCIMENTO_NULL;
import static br.com.contmatic.constantes.Constante.EMAIL_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.EMAIL_EMPTY;
import static br.com.contmatic.constantes.Constante.EMAIL_INVALID;
import static br.com.contmatic.constantes.Constante.EMAIL_LESS_10_CARACTERES;
import static br.com.contmatic.constantes.Constante.EMAIL_NULL;
import static br.com.contmatic.constantes.Constante.EMAIL_WITHOUT_ARROBA;
import static br.com.contmatic.constantes.Constante.EMAIL_WITH_BLANK_SPACE_IN_WORD;
import static br.com.contmatic.constantes.Constante.ENDERECO_NULL;
import static br.com.contmatic.constantes.Constante.NOME_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.NOME_EMPTY;
import static br.com.contmatic.constantes.Constante.NOME_GREATER_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_NULL;
import static br.com.contmatic.constantes.Constante.NOME_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.TELEFONE_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.contmatic.fixture.factory.FixtureFactoryEndereco.enderecoValido;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_GREATER_100_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_LESS_10_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_WITHOUT_ARROBA_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_WITH_BLANK_SPACE;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.VALID_EMAIL;
import static br.com.contmatic.services.utils.GeradorCpf.gerardorRandomCpf;
import static br.com.six2six.fixturefactory.Fixture.of;

import java.math.BigDecimal;
import java.util.HashSet;

import org.joda.time.DateTime;

import com.github.javafaker.Faker;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.empresa.Cliente;
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

        final String NOME = "nome";
        final String EMAIL = "email";
        final String DATA_NASCIMENTO = "dataNascimento";
        final String ENDERECO = "endereco";
        final String TELEFONES = "telefones";
        of(Cliente.class).addTemplate(VALID, new Rule() {
            {
                add(NOME, name());
                add(EMAIL, regex(VALID_EMAIL));
                add(DATA_NASCIMENTO, new DateTime());
                final String DINHEIRO_CARTEIRA = "dinheiroCarteira";
                add(DINHEIRO_CARTEIRA, BigDecimal.valueOf(new Faker().number().randomDouble(2, 1000, 2000)));
                add(ENDERECO, enderecoValido());
                final String CPF = "cpf";
                add(CPF, gerardorRandomCpf());
                add(TELEFONES, new HashSet<>());
            }
        });
        of(Cliente.class).addTemplate(NOME_NULL).inherits(VALID, new Rule() {
            {
                add(NOME, null);
            }
        });
        of(Cliente.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        of(Cliente.class).addTemplate(NOME_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(NOME, " ");
            }
        });
        of(Cliente.class).addTemplate(NOME_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("AA"));
            }
        });
        of(Cliente.class).addTemplate(NOME_GREATER_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, "ASDEGJDFHJKGHJDFHGJDHGHDFHGDHFGHDGHDFHGIODHFOGHDFOGDFHGDHFOGDFJ");
            }
        });
        of(Cliente.class).addTemplate(NOME_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, "@#!@");
            }
        });
        of(Cliente.class).addTemplate(EMAIL_NULL).inherits(VALID, new Rule() {
            {
                add(EMAIL, null);
            }
        });
        of(Cliente.class).addTemplate(EMAIL_EMPTY).inherits(VALID, new Rule() {
            {
                add(EMAIL, "");
            }
        });
        of(Cliente.class).addTemplate(EMAIL_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(EMAIL, " ");
            }
        });
        of(Cliente.class).addTemplate(EMAIL_INVALID).inherits(VALID, new Rule() {
            {
                add(EMAIL, firstName());
            }
        });
        of(Cliente.class).addTemplate(EMAIL_LESS_10_CARACTERES).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_LESS_10_CARACTER));
            }
        });
        of(Cliente.class).addTemplate(EMAIL_GREATER_100_CARACTER).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_GREATER_100_CARACTER));
            }
        });
        of(Cliente.class).addTemplate(EMAIL_WITH_BLANK_SPACE_IN_WORD).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITH_BLANK_SPACE));
            }
        });
        of(Cliente.class).addTemplate(GeradorEmail.EMAIL_WITH_NUMBER_AFTER_ARROBA).inherits(VALID, new Rule() {
            {
                add(EMAIL, "AAAaaaa@123S.COM");
            }
        });
        of(Cliente.class).addTemplate(EMAIL_WITHOUT_ARROBA).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        of(Cliente.class).addTemplate(Constante.EMAIL_WITHOUT_PONTO_COM).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITHOUT_PONTO_COM));
            }
        });
        of(Cliente.class).addTemplate(Constante.EMAIL_WITHOUT_COM).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITHOUT_COM));
            }
        });
        of(Cliente.class).addTemplate(Constante.EMAIL_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        of(Cliente.class).addTemplate(ENDERECO_NULL).inherits(VALID, new Rule() {
            {
                add(ENDERECO, null);
            }
        });
        of(Cliente.class).addTemplate(DATA_NASCIMENTO_NULL).inherits(VALID, new Rule() {
            {
                add(DATA_NASCIMENTO, null);
            }
        });
        of(Cliente.class).addTemplate(TELEFONE_NULL).inherits(VALID, new Rule() {
            {
                add(TELEFONES, null);
            }
        });
    }

}
