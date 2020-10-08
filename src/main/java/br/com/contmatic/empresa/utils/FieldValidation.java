package br.com.contmatic.empresa.utils;

import static br.com.contmatic.constantes.Constante.CNPJ_VALIDIDO;
import static br.com.contmatic.constantes.Constante.VALOR_INVALIDO;
import static br.com.contmatic.constantes.Constante.VALOR_MAXIMO_INCORRETOS;
import static br.com.contmatic.constantes.Constante.VALOR_MINIMO_INCORRETOS;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.services.EmptyStringException;
import br.com.estudo.projetoweb.services.validation.utils.CpfValidation;

public final class FieldValidation {

	private FieldValidation() {
		super();
	}

	public static void isCnpjValido(String yourCnpj) {
		if (!yourCnpj.matches(CNPJ_VALIDIDO)) {
			campoInvalido("Cnpj invalido");
		}
	}

	public static void isCpfValido(String yourCpf) {
		if (!CpfValidation.isCpfValid(yourCpf)) {
			campoInvalido("Cpf invalido");
		}
	}

	public static void isStringContaisJustWord(String yourString) {
		if (!StringUtils.isAlphaSpace(yourString)) {
			campoInvalido(VALOR_INVALIDO);
		}
	}

	public static void isStringContaisJustNumber(String yourString) {
		if (!StringUtils.isNumericSpace(yourString)) {
			campoInvalido(VALOR_INVALIDO);
		}
	}

	public static void isStringContaisWordAndNumber(String yourString) {
		if (!StringUtils.isAlphanumericSpace(yourString)) {
			campoInvalido(VALOR_INVALIDO);
		}
	}

	public static void isEmailValido(String yourEmail) {
		if (!yourEmail.matches(Constante.EMAIL_VALIDO)) {
			campoInvalido("Email invalido");
		}
	}

	public static void isNull(Object yourObject) {
		if (yourObject == null) {
			throw new NullPointerException("Object is null");
		}
	}

	public static void isStringEmpty(String yourString) {
		if (StringUtils.isEmpty(yourString) || StringUtils.isBlank(yourString)) {
			throw new EmptyStringException("String está vazia");
		}
	}

	public static void minAndMaxValue(int min, int max, int yourValue) {
		if (yourValue < min) {
			campoInvalido(VALOR_MINIMO_INCORRETOS);
		}
		if (yourValue > max) {
			campoInvalido(VALOR_MAXIMO_INCORRETOS);
		}
	}

	public static void minAndMaxValue(int min, int max, String yourString) {
		if (yourString.length() < min) {
			campoInvalido(VALOR_MINIMO_INCORRETOS);
		}

		if (yourString.length() > max) {
			campoInvalido(VALOR_MAXIMO_INCORRETOS);
		}
	}

	public static void minAndMaxValue(BigDecimal min, BigDecimal max, BigDecimal yourValue) {
		if (yourValue.compareTo(min) < 0) {
			campoInvalido(VALOR_MINIMO_INCORRETOS);
		}

		if (yourValue.compareTo(max) > 0) {
			campoInvalido(VALOR_MAXIMO_INCORRETOS);
		}
	}

	private static void campoInvalido(String message) {
		throw new IllegalArgumentException(message);
	}

}
