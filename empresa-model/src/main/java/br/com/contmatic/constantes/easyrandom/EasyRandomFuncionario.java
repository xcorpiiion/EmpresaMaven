package br.com.contmatic.constantes.easyrandom;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cargo;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.TipoContrato;
import br.com.contmatic.endereco.Endereco;

public final class EasyRandomFuncionario {
	
	private static final String CARGO = "cargo";
	
	private static final String DATA_NASCIMENTO = "dataNascimento";
	
	private static final String EMAIL = "email";
	
	private static final String ENDERECO = "endereco";
	
    private EasyRandomFuncionario() {

    }

    public static Funcionario validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario tipoDadoParaTeste) {
        Faker faker = new Faker();
        EasyRandomParameters parameters = new EasyRandomParameters();
        switch (tipoDadoParaTeste) {
            case VALIDO:
                dadosFuncionarioValidos(parameters, faker, "todos");
                break;
            case CARGO_NULL:
                dadosFuncionarioValidos(parameters, faker, CARGO);
                cargo(parameters, null);
                break;
            case CPF_BLANK_SPACE:
                dadosFuncionarioValidos(parameters, faker, "cpf");
                cpf(parameters, StringUtils.SPACE);
                break;

            case CPF_EMPTY:
                dadosFuncionarioValidos(parameters, faker, "cpf");
                cpf(parameters, StringUtils.EMPTY);
                break;
            case CPF_NULL:
                dadosFuncionarioValidos(parameters, faker, "cpf");
                cpf(parameters, null);
                break;
            case CPF_WITH_SPECIAL_CARACTER:
                dadosFuncionarioValidos(parameters, faker, "cpf");
                cpf(parameters, "%$#%");
                break;
            case DATA_NASCIMENTO_NULL:
                dadosFuncionarioValidos(parameters, faker, DATA_NASCIMENTO);
                dataNascimento(parameters, null);
                break;
            case EMAIL_WITH_SPECIAL_CARACTER:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, "%$�$%");
                break;
            case EMAIL_BLANK_SPACE:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, StringUtils.SPACE);
                break;
            case EMAIL_EMPTY:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, StringUtils.EMPTY);
                break;
            case EMAIL_INVALID_SIZE:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, "aaa" + "gmail.com");
                break;
            case EMAIL_NULL:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, null);
                break;
            case EMAIL_WITH_BLANK_SPACE:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, "aaaaa @gmail.com");
                break;
            case EMAIL_WITHOU_ARROBA:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, "");
                break;
            case EMAIL_WITHOUT_DOT:
                dadosFuncionarioValidos(parameters, faker, EMAIL);
                email(parameters, "aaaaa@gmailcom");
                break;
            case ENDERECO_NULL:
                dadosFuncionarioValidos(parameters, faker, ENDERECO);
                endereco(parameters, null);
                break;
            case NOME_BLANK_SPACE:
                dadosFuncionarioValidos(parameters, faker, "nome");
                nome(parameters, StringUtils.SPACE);
                break;
            case NOME_EMPTY:
                dadosFuncionarioValidos(parameters, faker, "nome");
                nome(parameters, StringUtils.EMPTY);
                break;
            case NOME_INVALID_SIZE:
                dadosFuncionarioValidos(parameters, faker, "nome");
                nome(parameters, "a");
                break;
            case NOME_NULL:
                dadosFuncionarioValidos(parameters, faker, "nome");
                nome(parameters, null);
                break;
            case SALARIO_NULL:
                dadosFuncionarioValidos(parameters, faker, "salario");
                salario(parameters, null);
                break;
            case SALARIO_PRECIA_SER_VALOR_MAIOR:
                dadosFuncionarioValidos(parameters, faker, "salario");
                salario(parameters, new BigDecimal(new Random().nextInt(500 - (100 - 1)) * 1));
                break;
            case TIPO_CONTRATO_NULL:
                dadosFuncionarioValidos(parameters, faker, "tipoContrato");
                tipoContrato(parameters, null);
                break;
        }
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(Funcionario.class);
    }

    private static void dadosFuncionarioValidos(EasyRandomParameters parameters, Faker faker, String nomeCampoUsado) {
        if (!nomeCampoUsado.equalsIgnoreCase("nome"))
            nome(parameters, faker.pokemon().name());
        if (!nomeCampoUsado.equalsIgnoreCase(EMAIL))
            email(parameters, faker.pokemon().name() + "@gmail.com");
        if (!nomeCampoUsado.equalsIgnoreCase("cpf"))
            cpf(parameters, "45071341883");
        if (!nomeCampoUsado.equalsIgnoreCase(DATA_NASCIMENTO))
            dataNascimento(parameters, new Date());
        if (!nomeCampoUsado.equalsIgnoreCase(CARGO))
            cargo(parameters, Cargo.values()[new Random().nextInt(Cargo.values().length)]);
        if (!nomeCampoUsado.equalsIgnoreCase(ENDERECO))
            endereco(parameters, EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
        if (!nomeCampoUsado.equalsIgnoreCase("tipoContrato"))
            tipoContrato(parameters, TipoContrato.values()[new Random().nextInt(TipoContrato.values().length)]);
        if (!nomeCampoUsado.equalsIgnoreCase("salario")) {
            int val = new Random().nextInt(5000 - (100 - 1)) + 100;
            salario(parameters, new BigDecimal(val));
        }
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
        parameters.randomize(FieldPredicates.named(EMAIL), email);
    }

    private static void cpf(EasyRandomParameters parameters, String cpfFuncionario) {
        Randomizer<String> cpf = new Randomizer<String>() {
            @Override
            public String getRandomValue() {
                return cpfFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named("cpf"), cpf);
    }

    private static void dataNascimento(EasyRandomParameters parameters, Date dataNascimentoFuncionario) {
        Randomizer<Date> dataNascimento = new Randomizer<Date>() {
            @Override
            public Date getRandomValue() {
                return dataNascimentoFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named(DATA_NASCIMENTO), dataNascimento);
    }

    private static void cargo(EasyRandomParameters parameters, Cargo cargoFuncionario) {
        Randomizer<Cargo> cargo = new Randomizer<Cargo>() {
            @Override
            public Cargo getRandomValue() {
                return cargoFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named(CARGO), cargo);
    }

    private static void endereco(EasyRandomParameters parameters, Endereco enderecoFuncionario) {
        Randomizer<Endereco> endereco = new Randomizer<Endereco>() {
            @Override
            public Endereco getRandomValue() {
                return enderecoFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named(ENDERECO), endereco);
    }

    private static void tipoContrato(EasyRandomParameters parameters, TipoContrato tipoContratoFuncionario) {
        Randomizer<TipoContrato> tipoContrato = new Randomizer<TipoContrato>() {
            @Override
            public TipoContrato getRandomValue() {
                return tipoContratoFuncionario;
            }
        };
        parameters.randomize(FieldPredicates.named("tipoContrato"), tipoContrato);
    }

    private static void salario(EasyRandomParameters parameters, BigDecimal precoProduto) {
        Randomizer<BigDecimal> salario = new Randomizer<BigDecimal>() {
            @Override
            public BigDecimal getRandomValue() {
                return precoProduto;
            }
        };
        parameters.randomize(FieldPredicates.named("salario"), salario);
    }
}
