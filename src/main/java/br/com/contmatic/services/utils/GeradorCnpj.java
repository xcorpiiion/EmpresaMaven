package br.com.contmatic.services.utils;

import com.mifmif.common.regex.Generex;

import br.com.contmatic.constantes.Constante;

public class GeradorCnpj {
	
	private GeradorCnpj() {
		
	}
	
	public static String cnpjGenerator() {
		return new Generex(Constante.CNPJ_VALIDIDO).random();
	}
	
}
