package br.com.contmatic.easyrandom;

import br.com.contmatic.utils.Constante;
import br.com.contmatic.easyrandom.enums.TipoDadoParaTesteTelefone;
import br.com.contmatic.empresa.TipoContrato;
import br.com.contmatic.telefone.DddBrasil;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TipoTelefone;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.Random;

public final class EasyRandomTelefone {

	private EasyRandomTelefone() {

	}

	public static Telefone validadorEasyRandomTelefone(TipoDadoParaTesteTelefone tipoDadoParaTeste) {
		EasyRandomParameters parameters = new EasyRandomParameters();
		switch (tipoDadoParaTeste) {
		case VALIDO:
			dadosTelefoneValidos(parameters);
			break;
		case DDD_NULL:
			dadosTelefoneDddNull(parameters);
			break;
		case PHONE_BLANK_SPACE:
			dadosTelefoneBlankSpace(parameters);
			break;
		case PHONE_EMPTY:
			dadosTelefoneEmpty(parameters);
			break;
		case PHONE_INVALID_SIZE:
			dadosTelefoneInvalidSize(parameters);
			break;
		case PHONE_NULL:
			dadosTelefoneNull(parameters);
			break;
		case TIPO_TELEFONE_NULL:
			dadosTelefoneTipoTelefoneNull(parameters);
			break;
		}
		EasyRandom generator = new EasyRandom(parameters);
		return generator.nextObject(Telefone.class);
	}

	private static void dadosTelefoneValidos(EasyRandomParameters parameters) {
		numeroTelefone(parameters, new Generex(Constante.PHONE_VALIDATION).random());
		dddTelefone(parameters, DddBrasil.values()[new Random().nextInt(new Random().nextInt(DddBrasil.values().length))]);
		tipoTelefone(parameters, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
	}
	
	private static void dadosTelefoneDddNull(EasyRandomParameters parameters) {
		numeroTelefone(parameters, "39852913");
		dddTelefone(parameters, null);
		tipoTelefone(parameters, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
	}
	
	private static void dadosTelefoneBlankSpace(EasyRandomParameters parameters) {
		numeroTelefone(parameters, " ");
		dddTelefone(parameters, DddBrasil.values()[new Random().nextInt(new Random().nextInt(DddBrasil.values().length))]);
		tipoTelefone(parameters, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
	}
	
	private static void dadosTelefoneEmpty(EasyRandomParameters parameters) {
		numeroTelefone(parameters, StringUtils.EMPTY);
		dddTelefone(parameters, DddBrasil.values()[new Random().nextInt(new Random().nextInt(DddBrasil.values().length))]);
		tipoTelefone(parameters, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
	}
	
	private static void dadosTelefoneInvalidSize(EasyRandomParameters parameters) {
		numeroTelefone(parameters, "3985291");
		dddTelefone(parameters, DddBrasil.values()[new Random().nextInt(new Random().nextInt(DddBrasil.values().length))]);
		tipoTelefone(parameters, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
	}
	
	private static void dadosTelefoneNull(EasyRandomParameters parameters) {
		numeroTelefone(parameters, null);
		dddTelefone(parameters, DddBrasil.values()[new Random().nextInt(new Random().nextInt(DddBrasil.values().length))]);
		tipoTelefone(parameters, TipoTelefone.values()[new Random().nextInt(TipoContrato.values().length)]);
	}
	
	private static void dadosTelefoneTipoTelefoneNull(EasyRandomParameters parameters) {
		numeroTelefone(parameters, null);
		dddTelefone(parameters, DddBrasil.values()[new Random().nextInt(new Random().nextInt(DddBrasil.values().length))]);
		tipoTelefone(parameters, null);
	}

	private static void tipoTelefone(EasyRandomParameters parameters, TipoTelefone tipoTelefoneLength) {
		parameters.randomize(TipoTelefone.class,
				() -> tipoTelefoneLength);
	}

	private static void dddTelefone(EasyRandomParameters parameters, DddBrasil dddBrasilLength) {
		parameters.randomize(DddBrasil.class,
				() -> dddBrasilLength);
	}

	private static void numeroTelefone(EasyRandomParameters parameters, String numeroTelefone) {
		parameters.randomize(String.class, () -> numeroTelefone);
	}
}
