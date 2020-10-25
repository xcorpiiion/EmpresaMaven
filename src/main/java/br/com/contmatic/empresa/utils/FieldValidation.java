package br.com.contmatic.empresa.utils;

import static br.com.contmatic.constantes.Constante.EMAIL_VALIDO;
import static br.com.estudo.projetoweb.services.validation.utils.CpfOrCnpjValidation.isCnpjValid;
import static br.com.estudo.projetoweb.services.validation.utils.CpfOrCnpjValidation.isCpfValid;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isAlphaSpace;
import static org.apache.commons.lang3.StringUtils.isAlphanumericSpace;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNumericSpace;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

public final class FieldValidation {

	private FieldValidation() {
		super();
	}

	public static void isCnpjValido(String yourCnpj, String errorMenssage) {
		if (!isCnpjValid(yourCnpj)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isCpfValido(String yourCpf, String errorMenssage) {
		if (!isCpfValid(yourCpf)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isStringContaisJustWord(String yourString, String errorMenssage) {
		if (!isAlphaSpace(yourString)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isStringContaisJustNumber(String yourString, String errorMenssage) {
		if (!isNumericSpace(yourString)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isStringContaisWordAndNumber(String yourString, String errorMenssage) {
		if (!isAlphanumericSpace(yourString)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isEmailValido(String yourEmail, String errorMenssage) {
		if (!yourEmail.matches(EMAIL_VALIDO)) {
			illegalState(errorMenssage);
		}
	}

	public static void isNull(Object yourObject, String errorMenssage) {
		if (yourObject == null) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isListEmpty(List<? extends Object> yourObjects, String errorMenssage) {
		if (isEmpty(yourObjects)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isStringEmpty(String yourString, String errorMenssage) {
		if (isEmpty(yourString) || isBlank(yourString)) {
			illegalArgument(errorMenssage);
		}
	}

	public static void minAndMaxValue(int min, int max, int yourValue, String errorMenssage) {
		if (yourValue < min || yourValue > max) {
			illegalArgument(errorMenssage);
		}
	}

	public static void minAndMaxValue(int min, int max, String yourString, String errorMenssage) {
		if (yourString.length() < min || yourString.length() > max) {
			illegalArgument(errorMenssage);
		}
	}

	public static void minAndMaxValue(BigDecimal min, BigDecimal max, BigDecimal yourValue, String errorMenssage) {
		if (yourValue.compareTo(min) < 0 || yourValue.compareTo(max) > 0) {
			illegalArgument(errorMenssage);
		}
	}

	public static void isDataGreaterThanCurrent(DateTime yourDate, String errorMenssage) {
		if (yourDate.isAfter(new DateTime().getMillis())) {
			illegalState(errorMenssage);
		}
	}

	public static void isLessThanCreateDate(DateTime createDate, DateTime yourDate, String errorMenssage) {
		if (yourDate.isBefore(createDate.getMillis())) {
			illegalState(errorMenssage);
		}
	}

	public static void isDataLessThan1998(DateTime yourDate, String errorMenssage) {
		DateTime date = new DateTime(1998, 07, 01, 0, 0);
		if (yourDate.isBefore(date.getMillis())) {
			illegalState(errorMenssage);
		}
	}

	public static void isDataLessThan1920(DateTime yourDate, String errorMenssage) {
		DateTime date = new DateTime(1920, 01, 01, 0, 0);
		if (yourDate.isBefore(date.getMillis())) {
			illegalState(errorMenssage);
		}
	}

	public static void isGreaterThanEmpresaCreateDate(DateTime dataSaida, DateTime dataEntrada) {
		if (dataSaida.isBefore(dataEntrada.getMillis())) {
			throw new IllegalStateException("A data de saida da empresa precisa ser maior do que a data de entrada");
		}
	}

	private static void illegalArgument(String message) {
		throw new IllegalArgumentException(message);
	}

	private static void illegalState(String message) {
		throw new IllegalStateException(message);
	}

}
