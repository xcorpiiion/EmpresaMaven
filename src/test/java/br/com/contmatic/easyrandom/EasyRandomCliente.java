package br.com.contmatic.easyrandom;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;
import org.joda.time.DateTime;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.fixture.factory.GeradorCpf;
import br.com.contmatic.fixture.factory.GeradorNome;

public final class EasyRandomCliente {

	private EasyRandomCliente() {

	}

	public static Cliente validadorEasyRandomCliente(TipoDadoParaTesteCliente tipoDadoParaTeste) {
		Faker faker = new Faker();
		EasyRandomParameters parameters = new EasyRandomParameters();
		switch (tipoDadoParaTeste) {
		case VALIDO:
			dadosFuncionarioValidos(parameters, faker, "todos");
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
			dadosFuncionarioValidos(parameters, faker, "dataNascimento");
			dataNascimento(parameters, null);
			break;
		case EMAIL_WITH_SPECIAL_CARACTER:
			dadosFuncionarioValidos(parameters, faker, "email");
			email(parameters, "%$�¨*daksjdhja$%@gmail.com");
			break;
		case EMAIL_BLANK_SPACE:
			dadosFuncionarioValidos(parameters, faker, "email");
			email(parameters, StringUtils.SPACE);
			break;
		case EMAIL_EMPTY:
			dadosFuncionarioValidos(parameters, faker, "email");
			email(parameters, StringUtils.EMPTY);
			break;
		case EMAIL_INVALID_SIZE:
			dadosFuncionarioValidos(parameters, faker, "email");
			email(parameters, "aaa" + "gmail.com");
			break;
		case EMAIL_NULL:
			dadosFuncionarioValidos(parameters, faker, "email");
			email(parameters, null);
			break;
		case ENDERECO_NULL:
			dadosFuncionarioValidos(parameters, faker, "endereco");
			endereco(parameters, null);
			break;
		case NOME__WITH_SPECIAL_CARACTER:
			dadosFuncionarioValidos(parameters, faker, "nome");
			nome(parameters, GeradorNome.NOME_WITH_SPECIAL_CARACTER);
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
		case DINHEIRO_CARTEIRA_NULL:
			dadosFuncionarioValidos(parameters, faker, "dinheiroCarteira");
			dinheiroCarteira(parameters, null);
			break;
		case DINHEIRO_CARTEIRA_PRECIA_SER_VALOR_MAIOR:
			dadosFuncionarioValidos(parameters, faker, "dinheiroCarteira");
			dinheiroCarteira(parameters, new BigDecimal(new Random().nextDouble() * -1));
			break;
		}
		EasyRandom generator = new EasyRandom(parameters);
		return generator.nextObject(Cliente.class);
	}

	private static void dadosFuncionarioValidos(EasyRandomParameters parameters, Faker faker, String nomeCampoUsado) {
		if (!nomeCampoUsado.equalsIgnoreCase("nome"))
			nome(parameters, faker.pokemon().name());
		if (!nomeCampoUsado.equalsIgnoreCase("email"))
			email(parameters, faker.pokemon().name() + "@gmail.com");
		if (!nomeCampoUsado.equalsIgnoreCase("cpf"))
			cpf(parameters, GeradorCpf.gerardorRandomCpf());
		if (!nomeCampoUsado.equalsIgnoreCase("dataNascimento"))
			dataNascimento(parameters, new DateTime());
		if (!nomeCampoUsado.equalsIgnoreCase("endereco"))
			endereco(parameters, EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
		if (!nomeCampoUsado.equalsIgnoreCase("dinheiroCarteira"))
			dinheiroCarteira(parameters, new BigDecimal(new Random().nextDouble() + 1 * 5000));
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

	private static void cpf(EasyRandomParameters parameters, String cpfFuncionario) {
		Randomizer<String> cpf = new Randomizer<String>() {
			@Override
			public String getRandomValue() {
				return cpfFuncionario;
			}
		};
		parameters.randomize(FieldPredicates.named("cpf"), cpf);
	}

	private static void dataNascimento(EasyRandomParameters parameters, DateTime dataNascimentoFuncionario) {
		Randomizer<DateTime> dataNascimento = new Randomizer<DateTime>() {
			@Override
			public DateTime getRandomValue() {
				return dataNascimentoFuncionario;
			}
		};
		parameters.randomize(FieldPredicates.named("dataNascimento"), dataNascimento);
	}

	private static void endereco(EasyRandomParameters parameters, Endereco enderecoFuncionario) {
		Randomizer<Endereco> endereco = new Randomizer<Endereco>() {
			@Override
			public Endereco getRandomValue() {
				return enderecoFuncionario;
			}
		};
		parameters.randomize(FieldPredicates.named("endereco"), endereco);
	}

	private static void dinheiroCarteira(EasyRandomParameters parameters, BigDecimal dinheiroCarteiraCliente) {
		Randomizer<BigDecimal> dinheiroCarteira = new Randomizer<BigDecimal>() {
			@Override
			public BigDecimal getRandomValue() {
				return dinheiroCarteiraCliente;
			}
		};
		parameters.randomize(FieldPredicates.named("dinheiroCarteira"), dinheiroCarteira);
	}
}
