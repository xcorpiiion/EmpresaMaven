package br.com.contmatic.constantes;

public final class Constante {
	
	public static final String ILLEGAL_WORD = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$";
	
	public static final String ILLEGAL_NUMBER = "^[0-9]*$";
	
	public static final String CPF_VALIDIDO = "^([0-9]{3}\\.?){3}-?[0-9]{2}$";
	
	public static final String CNPJ_VALIDIDO = "([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})";
	
	public static final String EMAIL_VALIDO = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
	
	public static final String VALOR_MINIMO_INCORRETOS = "Seu campo é menor do que o valor minimo";
	
	public static final String VALOR_MAXIMO_INCORRETOS = "Seu campo é maior do que o valor maximo";
	
	public static final String VALOR_INVALIDO = "Valor invalido";
	
	private Constante() {
		
	}
	
	
}
