package br.com.contmatic.services.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.estudo.projetoweb.services.validation.utils.CpfValidation;

public abstract class GeradorCpf {

    /** The cpf random. */
    private static StringBuilder cpfRandom;

    /** The armazena numeros para calculo do cpf. */
    private static List<Integer> armazenaNumerosParaCalculoDoCpf;

    /** The armazena multiplicacao. */
    private static List<Integer> armazenaMultiplicacao = new ArrayList<>();

    /** The resultado modulo. */
    private static int resultadoModulo;

    /**
     * Gerardor random cpf.
     *
     * @return the string
     */
    
    private GeradorCpf() {
    	
    }
    
    public static String gerardorRandomCpf() {
        cpfRandom = new StringBuilder();
        armazenaNumerosParaCalculoDoCpf = new ArrayList<>();
        geraNumerosAleatorios();
        cpfRandom.append(armazenaNumerosParaCalculoDoCpf.get(9)).append(armazenaNumerosParaCalculoDoCpf.get(10));
        if(!CpfValidation.isCpfValid(cpfRandom.toString())) {
        	gerardorRandomCpf();
        }
        return cpfRandom.toString();
    }

    /**
     * Gera numeros aleatorios.
     */
    private static void geraNumerosAleatorios() {
        for(int i = 0 ; i < 9 ; i++) {
            armazenaNumerosParaCalculoDoCpf.add(1 + new Random().nextInt(9));
            cpfRandom.append(armazenaNumerosParaCalculoDoCpf.get(i));
        }
        multiplicaNumeros(10);
    }

    /**
     * Multiplica numeros.
     *
     * @param diminuiValores the diminui valores
     */
    private static void multiplicaNumeros(int diminuiValores) {
        armazenaMultiplicacao.clear();
        for(int i = 0 ; i < armazenaNumerosParaCalculoDoCpf.size() ; i++) {
            armazenaMultiplicacao.add(diminuiValores * armazenaNumerosParaCalculoDoCpf.get(i));
            diminuiValores--;
        }
        somarValoresAndCalculaModulo();
    }

    /**
     * Somar valores and calcula modulo.
     */
    private static void somarValoresAndCalculaModulo() {
        resultadoModulo = 0;
        for(int i = 0 ; i < armazenaMultiplicacao.size() ; i++) {
            resultadoModulo += armazenaMultiplicacao.get(i);
        }
        resultadoModulo %= 11;
        subtrairModuloPor11();
    }

    /**
     * Subtrair modulo por 11.
     */
    private static void subtrairModuloPor11() {
        if (armazenaNumerosParaCalculoDoCpf.size() < 11) {
            armazenaNumerosParaCalculoDoCpf.add(11 - resultadoModulo);
            multiplicaNumeros(11);
        }
    }

}
