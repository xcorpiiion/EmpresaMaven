package br.com.contmatic.constantes.easyrandom;

import br.com.contmatic.empresa.Empresa;
import com.github.javafaker.Faker;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

public final class EasyRandomEmpresa {

    private EasyRandomEmpresa() {

    }

    public static Empresa validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa tipoDadoParaTeste) {
        Faker faker = new Faker();
        EasyRandomParameters parameters = new EasyRandomParameters();
        switch (tipoDadoParaTeste) {
            case VALIDO:
                dadosEmpresaValidos(parameters, faker, "todos");
                break;
            case CNPJ_BLANK_SPACE:
                dadosEmpresaValidos(parameters, faker, "cnpj");
                cnpj(parameters, StringUtils.SPACE);
                break;
            case CNPJ_EMPTY:
                dadosEmpresaValidos(parameters, faker, "cnpj");
                cnpj(parameters, StringUtils.EMPTY);
                break;
            case CNPJ_NULL:
                dadosEmpresaValidos(parameters, faker, "cnpj");
                cnpj(parameters, null);
                break;
            case CNPJ_WITH_SPECIAL_CARACTER:
                dadosEmpresaValidos(parameters, faker, "cnpj");
                cnpj(parameters, "%$#%");
                break;
            case EMAIL_WITH_SPECIAL_CARACTER:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, "%$�¨*daksjdhja$%@gmail.com");
                break;
            case EMAIL_BLANK_SPACE:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, StringUtils.SPACE);
                break;
            case EMAIL_EMPTY:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, StringUtils.EMPTY);
                break;
            case EMAIL_WITH_BLANK_SPACE:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, "aaaaa @gmail.com");
                break;
            case EMAIL_WITH_NUMBER_AFTER_ARROBA:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, "aaaaa@45gmail.com");
                break;
            case EMAIL_WITHOU_ARROBA:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, "");
                break;
            case EMAIL_WITHOUT_DOT:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, "aaaaa@gmailcom");
                break;
            case EMAIL_INVALID_SIZE:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, "aaa" + "gmail.com");
                break;
            case EMAIL_NULL:
                dadosEmpresaValidos(parameters, faker, "email");
                email(parameters, null);
                break;
            case NOME__WITH_SPECIAL_CARACTER:
                dadosEmpresaValidos(parameters, faker, "nome");
                nome(parameters, "#$@#$@#$@#$@");
                break;
            case NOME_BLANK_SPACE:
                dadosEmpresaValidos(parameters, faker, "nome");
                nome(parameters, StringUtils.SPACE);
                break;
            case NOME_EMPTY:
                dadosEmpresaValidos(parameters, faker, "nome");
                nome(parameters, StringUtils.EMPTY);
                break;
            case NOME_INVALID_SIZE:
                dadosEmpresaValidos(parameters, faker, "nome");
                nome(parameters, "a");
                break;
            case NOME_NULL:
                dadosEmpresaValidos(parameters, faker, "nome");
                nome(parameters, null);
                break;
        }
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(Empresa.class);
    }

    private static void dadosEmpresaValidos(EasyRandomParameters parameters, Faker faker, String nomeCampoUsado) {
        if (!nomeCampoUsado.equalsIgnoreCase("nome"))
            nome(parameters, faker.university().name());
        if (!nomeCampoUsado.equalsIgnoreCase("email"))
            email(parameters, faker.pokemon().name() + "@gmail.com");
        if (!nomeCampoUsado.equalsIgnoreCase("cnpj"))
            cnpj(parameters, new Generex("([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})").random());
    }

    private static void nome(EasyRandomParameters parameters, String nomeFuncionario) {
        Randomizer<String> nome = new Randomizer<String>() {
            @Override
            public String getRandomValue() {
                return nomeFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named("nome"), nome);
    }

    private static void email(EasyRandomParameters parameters, String emailFuncionario) {
        Randomizer<String> email = new Randomizer<String>() {
            @Override
            public String getRandomValue() {
                return emailFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named("email"), email);
    }

    private static void cnpj(EasyRandomParameters parameters, String cnpjFuncionario) {
        Randomizer<String> cnpj = new Randomizer<String>() {
            @Override
            public String getRandomValue() {
                return cnpjFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named("cnpj"), cnpj);
    }
}
