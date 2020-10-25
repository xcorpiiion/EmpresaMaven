package br.com.contmatic.services.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GeradorCnpj {

	private GeradorCnpj() {
		super();
	}

	private static int randomiza(int randomNumber) {
		return new Random().nextInt(randomNumber);
	}

	private static int mod(int dividendo, int divisor) {
		return dividendo - (dividendo / divisor) * divisor;
	}

	public static String gerardorRandomCnpj() {
		final int n = 9;
		List<Integer> armazenaNumerosParaCalculo = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			if (i > 7 && i < 11) {
				armazenaNumerosParaCalculo.add(0);
			} else if (i == 11) {
				armazenaNumerosParaCalculo.add(1);
			} else {
				armazenaNumerosParaCalculo.add(randomiza(n));
			}
		}
		int n1 = randomiza(n);
		int n2 = randomiza(n);
		int n3 = randomiza(n);
		int n4 = randomiza(n);
		int n5 = randomiza(n);
		int n6 = randomiza(n);
		int n7 = randomiza(n);
		int n8 = randomiza(n);
		int n9 = 0;
		int n10 = 0;
		int n11 = 0;
		int n12 = 1;
		int digito1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3
				+ n2 * 4 + n1 * 5;
		digito1 = 11 - (mod(digito1, 11));
		if (digito1 >= 10) {
			digito1 = 0;
		}
		int digito2 = digito1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3
				+ n3 * 4 + n2 * 5 + n1 * 6;
		digito2 = 11 - (mod(digito2, 11));
		if (digito2 >= 10) {
			digito2 = 0;
		}
		return "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + digito1 + digito2;
	}

}
