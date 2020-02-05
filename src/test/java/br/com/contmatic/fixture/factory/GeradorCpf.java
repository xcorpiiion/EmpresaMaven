package br.com.contmatic.fixture.factory;

import java.util.Random;

public abstract class GeradorCpf {
    
    public static String gerardorRandomCpf() {
        StringBuilder cpfRandom = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            cpfRandom.append(100 + new Random().nextInt(999)).append(".");
        }
        return cpfRandom.append("-").append(10 + new Random().nextInt(99)).toString();
    }
    
}
