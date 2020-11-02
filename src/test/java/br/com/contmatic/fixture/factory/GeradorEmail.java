package br.com.contmatic.fixture.factory;

public final class GeradorEmail {

    private GeradorEmail() {

    }

    public static String EMAIL_LESS_10_CARACTER = "[a-z]{9}@gmail.com|@hotmail.com";
    
    public static String EMAIL_GREATER_100_CARACTER = "[a-z]{101}@gmail.com|@hotmail.com";
    
    public static String EMAIL_WITHOUT_ARROBA_CARACTER = "[a-z]{101}gmail.com|hotmail.com";
    
    public static String EMAIL_WITHOUT_PONTO_COM = "[a-z]{101}@gmail|@hotmail";
    
    public static String EMAIL_WITH_NUMBER_AFTER_ARROBA = "[a-z]{101}@gmail45.com|@hotmail45.com";
    
    public static String EMAIL_WITH_BLANK_SPACE = "[a-z]{101}@gm ail.com|@ho tmail.com";
    
    public static String EMAIL_WITHOUT_COM = "[a-z]{10}@gmail.com|@hotmail.";
    
    public static String EMAIL_WITH_SPECIAL_CARACTER = "[a-z]{10}@g%%%mail.com|@hotmail.com";
    
    public static String VALID_EMAIL = "[a-z]{10}@gmail.com|@hotmail.com";
    
}
