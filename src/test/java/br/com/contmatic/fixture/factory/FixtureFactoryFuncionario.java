package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.CARGO_NULL;
import static br.com.contmatic.constantes.Constante.CPF_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.CPF_EMPTY;
import static br.com.contmatic.constantes.Constante.CPF_INVALID;
import static br.com.contmatic.constantes.Constante.CPF_NULL;
import static br.com.contmatic.constantes.Constante.DATA_NASCIMENTO_NULL;
import static br.com.contmatic.constantes.Constante.EMAIL_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.EMAIL_EMPTY;
import static br.com.contmatic.constantes.Constante.EMAIL_GREATER_100_CARACTERES;
import static br.com.contmatic.constantes.Constante.EMAIL_INVALID;
import static br.com.contmatic.constantes.Constante.EMAIL_LESS_10_CARACTERES;
import static br.com.contmatic.constantes.Constante.EMAIL_NULL;
import static br.com.contmatic.constantes.Constante.EMAIL_WITHOUT_ARROBA;
import static br.com.contmatic.constantes.Constante.EMAIL_WITH_BLANK_SPACE_IN_WORD;
import static br.com.contmatic.constantes.Constante.EMAIL_WITH_NUMBER_AFTER_ARROBA;
import static br.com.contmatic.constantes.Constante.ENDERECO_NULL;
import static br.com.contmatic.constantes.Constante.NOME_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.NOME_EMPTY;
import static br.com.contmatic.constantes.Constante.NOME_GREATER_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_NULL;
import static br.com.contmatic.constantes.Constante.NOME_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.SALARIO_LESS_1;
import static br.com.contmatic.constantes.Constante.SALARIO_NULL;
import static br.com.contmatic.constantes.Constante.TELEFONE_NULL;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_GREATER_100_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_LESS_10_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_WITHOUT_ARROBA_CARACTER;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_WITH_BLANK_SPACE;
import static br.com.contmatic.fixture.factory.GeradorEmail.EMAIL_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.services.utils.GeradorCpf.gerardorRandomCpf;
import static br.com.six2six.fixturefactory.Fixture.of;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;

import org.joda.time.DateTime;

import com.github.javafaker.Faker;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.EnumCargo;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FixtureFactoryFuncionario implements TemplateLoader {

    private final String NOME = "nome";
    private final String EMAIL = "email";
    private final String DATA_NASCIMENTO = "dataNascimento";
    private final String CARGO = "enumCargo";
    private final String SALARIO = "salario";
    private final String ENDERECO = "endereco";
    private final String CPF = "cpf";
    private final String TELEFONES = "telefones";

    @Override
    public void load() {
        of(Funcionario.class).addTemplate(VALID, new Rule() {
            {
                add(NOME, name());
                add(EMAIL, new Faker().internet().emailAddress());
                add(DATA_NASCIMENTO, new DateTime());
                add(CARGO, EnumCargo.values()[new Random().nextInt(EnumCargo.values().length)]);
                add(SALARIO, new BigDecimal(new Faker().number().randomDouble(2, 1000, 5000)));
                add(ENDERECO, FixtureFactoryEndereco.enderecoValido());
                add(CPF, gerardorRandomCpf());
                add(TELEFONES, new HashSet<>());
            }
        });
        of(Funcionario.class).addTemplate(NOME_NULL).inherits(VALID, new Rule() {
            {
                add(NOME, null);
            }
        });
        of(Funcionario.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        of(Funcionario.class).addTemplate(NOME_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(NOME, " ");
            }
        });
        of(Funcionario.class).addTemplate(NOME_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("AA"));
            }
        });
        of(Funcionario.class).addTemplate(NOME_GREATER_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, "ASDEGJDFHJKGHJDFHGJDHGHDFHGDHFGHDGHDFHGIODHFOGHDFOGDFHGDHFOGDFJ");
            }
        });
        of(Funcionario.class).addTemplate(NOME_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex(NOME_WITH_SPECIAL_CARACTER));
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_NULL).inherits(VALID, new Rule() {
            {
                add(EMAIL, null);
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_EMPTY).inherits(VALID, new Rule() {
            {
                add(EMAIL, "");
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(EMAIL, " ");
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_INVALID).inherits(VALID, new Rule() {
            {
                add(EMAIL, firstName());
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_LESS_10_CARACTERES).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_LESS_10_CARACTER));
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_GREATER_100_CARACTERES).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_GREATER_100_CARACTER));
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_WITH_BLANK_SPACE_IN_WORD).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITH_BLANK_SPACE));
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_WITH_NUMBER_AFTER_ARROBA).inherits(VALID, new Rule() {
            {
                add(EMAIL, "AAAaaaa@123S.COM");
            }
        });
        of(Funcionario.class).addTemplate(EMAIL_WITHOUT_ARROBA).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        of(Funcionario.class).addTemplate(Constante.EMAIL_WITHOUT_PONTO_COM).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITHOUT_PONTO_COM));
            }
        });
        of(Funcionario.class).addTemplate(Constante.EMAIL_WITHOUT_COM).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITHOUT_COM));
            }
        });
        of(Funcionario.class).addTemplate(Constante.EMAIL_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        of(Funcionario.class).addTemplate(ENDERECO_NULL).inherits(VALID, new Rule() {
            {
                add(ENDERECO, null);
            }
        });
        of(Funcionario.class).addTemplate(SALARIO_NULL).inherits(VALID, new Rule() {
            {
                add(SALARIO, null);
            }
        });
        of(Funcionario.class).addTemplate(SALARIO_LESS_1).inherits(VALID, new Rule() {
            {
                add(SALARIO, new BigDecimal(-1.00));
            }
        });
        of(Funcionario.class).addTemplate(CARGO_NULL).inherits(VALID, new Rule() {
            {
                add(CARGO, null);
            }
        });
        of(Funcionario.class).addTemplate(CPF_NULL).inherits(VALID, new Rule() {
            {
                add(CPF, null);
            }
        });
        of(Funcionario.class).addTemplate(CPF_EMPTY).inherits(VALID, new Rule() {
            {
                add(CPF, null);
            }
        });

        of(Funcionario.class).addTemplate(CPF_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(CPF, null);
            }
        });
        of(Funcionario.class).addTemplate(CPF_INVALID).inherits(VALID, new Rule() {
            {
                add(CPF, null);
            }
        });
        of(Funcionario.class).addTemplate(TELEFONE_NULL).inherits(VALID, new Rule() {
            {
                add(TELEFONES, null);
            }
        });
        of(Funcionario.class).addTemplate(DATA_NASCIMENTO_NULL).inherits(VALID, new Rule() {
            {
                add(DATA_NASCIMENTO, null);
            }
        });
    }
}
