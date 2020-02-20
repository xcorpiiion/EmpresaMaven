package br.com.contmatic.fixture.factory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;

import org.joda.time.DateTime;

import br.com.contmatic.empresa.Cargo;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.TipoContrato;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixtureFactoryFuncionario.
 */
public class FixtureFactoryFuncionario implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        Fixture.of(Funcionario.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("email", regex("[a-z]{10}@gmail.com|@hotmail.com"));
                add("dataNascimento", new DateTime());
                add("cargo", Cargo.values()[new Random().nextInt(Cargo.values().length)]);
                add("salario", new BigDecimal(1000.00 + (new Random().nextDouble() * (5000 - 8500))));
                add("endereco", FixtureFactoryEndereco.enderecoValido());
                add("tipoContrato", TipoContrato.values()[new Random().nextInt(TipoContrato.values().length)]);
                add("cpf", GeradorCpf.gerardorRandomCpf());
                add("telefones", new HashSet<>());
            }
        });
        Fixture.of(Funcionario.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        Fixture.of(Funcionario.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        Fixture.of(Funcionario.class).addTemplate("nomeLess3Caracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_LESS_3_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("nomeGreaterCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_GREATER_50_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("nomeWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("nome", regex(GeradorNome.NOME_WITH_SPECIAL_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailNull").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailEmpty").inherits("valid", new Rule() {
            {
                add("email", "");
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailBlankSpace").inherits("valid", new Rule() {
            {
                add("email", " ");
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailInvalid").inherits("valid", new Rule() {
            {
                add("email", firstName());
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailLess10Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_LESS_10_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailGreater100Caracteres").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_GREATER_100_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailWithBlankSpaceInWord").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_BLANK_SPACE));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailWithNumberAfterArroba").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_NUMBER_AFTER_ARROBA));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailWithoutArroba").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailWithoutPontoCom").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_PONTO_COM));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailWithoutCom").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITHOUT_COM));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("emailWithSpecialCaracter").inherits("valid", new Rule() {
            {
                add("email", regex(GeradorEmail.EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("enderecoNull").inherits("valid", new Rule() {
            {
                add("endereco", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("salarioNull").inherits("valid", new Rule() {
            {
                add("salario", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("salarioLess1").inherits("valid", new Rule() {
            {
                add("salario", new BigDecimal(-1.00));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("cargoNull").inherits("valid", new Rule() {
            {
                add("cargo", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("tipoContratoNull").inherits("valid", new Rule() {
            {
                add("tipoContrato", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("cpfNull").inherits("valid", new Rule() {
            {
                add("cpf", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("cpfEmpty").inherits("valid", new Rule() {
            {
                add("cpf", null);
            }
        });
        
        Fixture.of(Funcionario.class).addTemplate("cpfBlankSpace").inherits("valid", new Rule() {
            {
                add("cpf", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("invalidCpf").inherits("valid", new Rule() {
            {
                add("cpf", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("telefoneNull").inherits("valid", new Rule() {
            {
                add("telefones", null);
            }
        });
        Fixture.of(Funcionario.class).addTemplate("dataNascimentoNull").inherits("valid", new Rule() {
            {
                add("dataNascimento", null);
            }
        });
    }
}
