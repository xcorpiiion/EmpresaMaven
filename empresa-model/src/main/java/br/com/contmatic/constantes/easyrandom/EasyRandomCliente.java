package br.com.contmatic.constantes.easyrandom;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.joda.time.DateTime;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.endereco.Endereco;

public final class EasyRandomCliente {
	
	private static final String DATA_NASCIMENTO = "dataNascimento";
	
	private static final String EMAIL = "email";
	
	private static final String ENDERECO = "endereco";
	
	private static final String DINHEIRO_CARTEIRA = "dinheiroCarteira";
	
    private EasyRandomCliente() {

    }

    public static Cliente validadorEasyRandomCliente(TipoDadoParaTesteCliente tipoDadoParaTeste) {
        Faker faker = new Faker();
        EasyRandomParameters parameters = new EasyRandomParameters();
        switch (tipoDadoParaTeste) {
            case VALIDO:
                dadosClienteValidos(parameters, faker, "todos");
                break;
            case CPF_BLANK_SPACE:
                dadosClienteValidos(parameters, faker, "cpf");
                cpf(parameters, StringUtils.SPACE);
                break;
            case CPF_EMPTY:
                dadosClienteValidos(parameters, faker, "cpf");
                cpf(parameters, StringUtils.EMPTY);
                break;
            case CPF_NULL:
                dadosClienteValidos(parameters, faker, "cpf");
                cpf(parameters, null);
                break;
            case CPF_WITH_SPECIAL_CARACTER:
                dadosClienteValidos(parameters, faker, "cpf");
                cpf(parameters, "%$#%");
                break;
            case DATA_NASCIMENTO_NULL:
                dadosClienteValidos(parameters, faker, DATA_NASCIMENTO);
                dataNascimento(parameters, null);
                break;
            case EMAIL_WITH_SPECIAL_CARACTER:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, "%$�¨*daksjdhja$%@gmail.com");
                break;
            case EMAIL_BLANK_SPACE:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, StringUtils.SPACE);
                break;
            case EMAIL_EMPTY:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, StringUtils.EMPTY);
                break;
            case EMAIL_INVALID_SIZE:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, "aaa" + "gmail.com");
                break;
            case EMAIL_NULL:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, null);
                break;
            case EMAIL_WITH_BLANK_SPACE:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, "aaaaa @gmail.com");
                break;
            case EMAIL_WITH_NUMBER_AFTER_ARROBA:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, "aaaaa@45gmail.com");
                break;
            case EMAIL_WITHOU_ARROBA:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, "");
                break;
            case EMAIL_WITHOUT_DOT:
                dadosClienteValidos(parameters, faker, EMAIL);
                email(parameters, "aaaaa@gmailcom");
                break;
            case ENDERECO_NULL:
                dadosClienteValidos(parameters, faker, ENDERECO);
                endereco(parameters, null);
                break;
            case NOME__WITH_SPECIAL_CARACTER:
                dadosClienteValidos(parameters, faker, "nome");
                nome(parameters, "%#%$$¨");
                break;
            case NOME_BLANK_SPACE:
                dadosClienteValidos(parameters, faker, "nome");
                nome(parameters, StringUtils.SPACE);
                break;
            case NOME_EMPTY:
                dadosClienteValidos(parameters, faker, "nome");
                nome(parameters, StringUtils.EMPTY);
                break;
            case NOME_INVALID_SIZE:
                dadosClienteValidos(parameters, faker, "nome");
                nome(parameters, "a");
                break;
            case NOME_NULL:
                dadosClienteValidos(parameters, faker, "nome");
                nome(parameters, null);
                break;
            case DINHEIRO_CARTEIRA_NULL:
                dadosClienteValidos(parameters, faker, DINHEIRO_CARTEIRA);
                dinheiroCarteira(parameters, null);
                break;
            case DINHEIRO_CARTEIRA_PRECIA_SER_VALOR_MAIOR:
                dadosClienteValidos(parameters, faker, DINHEIRO_CARTEIRA);
                dinheiroCarteira(parameters, new BigDecimal(new Random().nextInt(5000 - (100 - 1)) * 1));
                break;
        }
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(Cliente.class);
    }

    private static void dadosClienteValidos(EasyRandomParameters parameters, Faker faker, String nomeCampoUsado) {
        if (!nomeCampoUsado.equalsIgnoreCase("nome"))
            nome(parameters, faker.pokemon().name());
        if (!nomeCampoUsado.equalsIgnoreCase(EMAIL))
            email(parameters, faker.pokemon().name() + "@gmail.com");
        if (!nomeCampoUsado.equalsIgnoreCase("cpf"))
            cpf(parameters, "45071341883");
        if (!nomeCampoUsado.equalsIgnoreCase(DATA_NASCIMENTO))
            dataNascimento(parameters, new DateTime().toDate());
        if (!nomeCampoUsado.equalsIgnoreCase(ENDERECO))
            endereco(parameters, EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
        if (!nomeCampoUsado.equalsIgnoreCase(DINHEIRO_CARTEIRA))
            dinheiroCarteira(parameters, new BigDecimal(new Random().nextInt() + 500));
    }

    private static void nome(EasyRandomParameters parameters, String nomeFuncionario) {
        parameters.randomize(String.class, () -> nomeFuncionario);
    }

    private static void email(EasyRandomParameters parameters, String emailFuncionario) {
    	parameters.randomize(String.class, () -> emailFuncionario);
    }

    private static void cpf(EasyRandomParameters parameters, String cpfFuncionario) {
    	parameters.randomize(String.class, () -> cpfFuncionario);
    }

    private static void dataNascimento(EasyRandomParameters parameters, Date dataNascimentoFuncionario) {
    	parameters.randomize(Date.class, () -> dataNascimentoFuncionario);
    }

    private static void endereco(EasyRandomParameters parameters, Endereco enderecoFuncionario) {
    	parameters.randomize(Endereco.class, () -> enderecoFuncionario);
    }

    private static void dinheiroCarteira(EasyRandomParameters parameters, BigDecimal dinheiroCarteiraCliente) {
    	parameters.randomize(BigDecimal.class, () -> dinheiroCarteiraCliente);
    }
}
