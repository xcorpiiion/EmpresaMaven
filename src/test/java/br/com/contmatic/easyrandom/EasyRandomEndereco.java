package br.com.contmatic.easyrandom;

import java.util.Random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

import com.github.javafaker.Faker;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EstadosBrasil;

public final class EasyRandomEndereco {

	private EasyRandomEndereco() {

	}

	public static Endereco validadorEasyRandomEndereco(TipoDadoParaTesteEndereco tipoDadoParaTeste) {
		Faker faker = new Faker();
		EasyRandomParameters parameters = new EasyRandomParameters();
		switch (tipoDadoParaTeste) {
		case VALIDO:
			dadosEnderecoValidos(parameters, faker);
			break;
		case BAIRRO_CONTAINS_NUMBER:
			bairro(parameters, faker.address().streetName() + 1);
			break;
		case BAIRRO_EMPTY:
			bairro(parameters, "");
			break;
		case BAIRRO_GREATER_50_CARACTER:
			bairro(parameters, faker.address().streetName());
			break;
		case BAIRRO_LESS_3_CARACTER:
			bairro(parameters, "");
			break;
		case BAIRRO_NULL:
			bairro(parameters, null);
			break;
		case BAIRRO_WITH_SPECIAL_CARACTER:
			bairro(parameters, "*/");
			break;
		case CEP_CONTAINS_WORD:
			cep(parameters, faker.number().digits(8) + "a");
			break;
		case CEP_EMPTY:
			cep(parameters, "");
			break;
		case CEP_LENGTH_DIFFERENCE_8:
			cep(parameters, faker.number().digits(7));
			break;
		case CEP_NULL:
			cep(parameters, null);
			break;
		case CIDADE_CONTAINS_NUMBER:
			cidade(parameters, faker.address().cityName() + "1");
			break;
		case CIDADE_EMPTY:
			cidade(parameters, " ");
			break;
		case CIDADE_GREATER_50_CARACTER:
			cidade(parameters, faker.address().cityName());
			break;
		case CIDADE_LESS_3_CARACTER:
			cidade(parameters, "aa");
			break;
		case CIDADE_NULL:
			cidade(parameters, null);
			break;
		case CIDADE_WITH_SPECIAL_CARACTER:
			cidade(parameters, faker.address().cityName() + "*/");
			break;
		case ESTADO_NULL:
			cidade(parameters, faker.address().cityName());
			break;
		case NUMERO_RESIDENCIA_LESS_THAN_ZERO:
			numeroResidencia(parameters, faker.number().numberBetween(-10000, -1));
			break;
		case RUA_EMPTY:
			rua(parameters, " ");
			break;
		case RUA_GREATER_50_CARACTER:
			rua(parameters, faker.address().streetAddress());
			break;
		case RUA_LESS_3_CARACTER:
			rua(parameters, "asd");
			break;
		case RUA_NULL:
			rua(parameters, null);
			break;
		case RUA_WITH_SPECIAL_CARACTER:
			rua(parameters, "%$%");
			break;
		}
		EasyRandom generator = new EasyRandom(parameters);
		return generator.nextObject(Endereco.class);
	}

	private static void dadosEnderecoValidos(EasyRandomParameters parameters, Faker faker) {
		cep(parameters, faker.number().digits(8));
		bairro(parameters, faker.address().streetName());
		rua(parameters, faker.address().streetAddress());
		numeroResidencia(parameters, faker.number().numberBetween(1, 10000));
		cidade(parameters, faker.address().cityName());
		estado(parameters, EstadosBrasil.values()[new Random().nextInt(EstadosBrasil.values().length)]);
	}

	private static void rua(EasyRandomParameters parameters, String nomeRua) {
		Randomizer<String> rua = new Randomizer<String>() {
			@Override
			public String getRandomValue() {
				return nomeRua;
			}
		};
		parameters.randomize(FieldPredicates.named("rua"), rua);
	}

	private static void bairro(EasyRandomParameters parameters, String nomeBairro) {
		Randomizer<String> bairro = new Randomizer<String>() {
			@Override
			public String getRandomValue() {
				return nomeBairro;
			}
		};
		parameters.randomize(FieldPredicates.named("bairro"), bairro);
	}

	private static void cep(EasyRandomParameters parameters, String numeroCep) {
		Randomizer<String> cep = new Randomizer<String>() {
			@Override
			public String getRandomValue() {
				return numeroCep;
			}
		};
		parameters.randomize(FieldPredicates.named("cep"), cep);
	}
	
	private static void numeroResidencia(EasyRandomParameters parameters, Integer numeroResidencia) {
		Randomizer<Integer> numeroDaResidencia = new Randomizer<Integer>() {
			@Override
			public Integer getRandomValue() {
				return numeroResidencia;
			}
		};
		parameters.randomize(FieldPredicates.named("numeroResidencia"), numeroDaResidencia);
	}
	
	private static void cidade(EasyRandomParameters parameters, String nomeCidade) {
		Randomizer<String> cidade = new Randomizer<String>() {
			@Override
			public String getRandomValue() {
				return nomeCidade;
			}
		};
		parameters.randomize(FieldPredicates.named("cidade"), cidade);
	}
	
	private static void estado(EasyRandomParameters parameters, EstadosBrasil estadosBrasil) {
		parameters.randomize(EstadosBrasil.class,
				() -> estadosBrasil);
	}
}
