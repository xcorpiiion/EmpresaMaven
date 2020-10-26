package br.com.contmatic.services.utils;

import static br.com.estudo.projetoweb.services.validation.utils.CpfOrCnpjValidation.isCpfValid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GeradorCpf {

	private static List<Integer> armazenaNumerosParaCalculoDoCpf;

	private static List<Integer> armazenaMultiplicacao = new ArrayList<>();

	private static int resultadoModulo;

	private GeradorCpf() {

	}
	
	public static String gerardorRandomCpf() {
		StringBuilder cpfRandom = new StringBuilder();
		armazenaNumerosParaCalculoDoCpf = new ArrayList<>();
		geraNumerosAleatorios(cpfRandom);
		cpfRandom.append(armazenaNumerosParaCalculoDoCpf.get(9)).append(armazenaNumerosParaCalculoDoCpf.get(10));
		if (!isCpfValid(cpfRandom.toString())) {
			gerardorRandomCpf();
		}
		return cpfRandom.toString();
	}

	private static void geraNumerosAleatorios(StringBuilder cpfRandom) {
		for (int i = 0; i < 9; i++) {
			armazenaNumerosParaCalculoDoCpf.add(1 + new Random().nextInt(9));
			cpfRandom.append(armazenaNumerosParaCalculoDoCpf.get(i));
		}
		multiplicaNumeros(10);
	}

	private static void multiplicaNumeros(int diminuiValores) {
		armazenaMultiplicacao.clear();
		for (int i = 0; i < armazenaNumerosParaCalculoDoCpf.size(); i++) {
			armazenaMultiplicacao.add(diminuiValores * armazenaNumerosParaCalculoDoCpf.get(i));
			diminuiValores--;
		}
		somarValoresAndCalculaModulo();
	}

	private static void somarValoresAndCalculaModulo() {
		resultadoModulo = 0;
		for (int i = 0; i < armazenaMultiplicacao.size(); i++) {
			resultadoModulo += armazenaMultiplicacao.get(i);
		}
		resultadoModulo %= 11;
		subtrairModuloPor11();
	}

	/**
	 * Nunca trocar o número 11
	 */
	private static void subtrairModuloPor11() {
		if (armazenaNumerosParaCalculoDoCpf.size() < 11) {
			armazenaNumerosParaCalculoDoCpf.add(11 - resultadoModulo);
			multiplicaNumeros(11);
		}
	}

}
