package br.com.contmatic.easyrandom;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Produto;

public final class EasyRandomProduto {

	private EasyRandomProduto() {

	}

	public static Produto validadorEasyRandomProduto(TipoDadoParaTesteProduto tipoDadoParaTeste) {
		Faker faker = new Faker();
		EasyRandomParameters parameters = new EasyRandomParameters();
		switch (tipoDadoParaTeste) {
		case VALIDO:
			dadosEnderecoValidos(parameters, faker);
			break;
		case ESTOQUE_INVALIDO:
			estoque(parameters, faker.number().numberBetween(-10, 0));
			break;
		case ESTOQUE_NULL:
			estoque(parameters, null);
			break;
		case NOME_BLANK_SPACE:
			nome(parameters, StringUtils.SPACE);
			break;
		case NOME_EMPTY:
			nome(parameters, StringUtils.EMPTY);
			break;
		case NOME_INVALID_SIZE:
			nome(parameters, "aa");
			break;
		case NOME_NULL:
			nome(parameters, null);
			break;
		case NOME__WITH_SPECIAL_CARACTER:
			nome(parameters, "*&*(�(�*");
			break;
		case PRECO_INVALIDO:
			preco(parameters, new BigDecimal(new Random().nextDouble() * -1));
			break;
		case PRECO_NULL:
			preco(parameters, null);
			break;
		}
		EasyRandom generator = new EasyRandom(parameters);
		return generator.nextObject(Produto.class);
	}

	private static void dadosEnderecoValidos(EasyRandomParameters parameters, Faker faker) {
		nome(parameters, faker.pokemon().name());
		preco(parameters, new BigDecimal(new Random().nextDouble() * 5000));
		estoque(parameters, faker.number().numberBetween(1, 1000));
	}

	private static void nome(EasyRandomParameters parameters, String nomeProduto) {
		Randomizer<String> nome = new Randomizer<String>() {
			@Override
			public String getRandomValue() {
				return nomeProduto;
			}
		};
		parameters.randomize(FieldPredicates.named("nome"), nome);
	}

	private static void preco(EasyRandomParameters parameters, BigDecimal precoProduto) {
		Randomizer<BigDecimal> preco = new Randomizer<BigDecimal>() {
			@Override
			public BigDecimal getRandomValue() {
				return precoProduto;
			}
		};
		parameters.randomize(FieldPredicates.named("preco"), preco);
	}

	private static void estoque(EasyRandomParameters parameters, Integer estoqueProduto) {
		Randomizer<Integer> estoque = new Randomizer<Integer>() {
			@Override
			public Integer getRandomValue() {
				return estoqueProduto;
			}
		};
		parameters.randomize(FieldPredicates.named("estoque"), estoque);
	}
}
