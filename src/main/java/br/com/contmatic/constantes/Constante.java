package br.com.contmatic.constantes;

public final class Constante {

    public static final String ILLEGAL_WORD = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$";

    public static final String ILLEGAL_NUMBER = "^[0-9]*$";

    public static final String VALIDATION_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static final String APENAS_REPOSITORES_PODEM_ALTERAR_OS_DADOS_DO_PRODUTO = "Apenas repositores podem alterar os dados do produto";
    
    public static final String NOME_NAO_PODE_ESTA_VAZIO = "Nome não pode esta vazio";
    
    public static final String EMAIL_INVALIDO = "Email invalido";
    
    public static final String EMAIL_NAO_PODE_ESTA_VAZIO = "Email não pode esta vazio";
    
    public static final String DATA_DE_NASCIMENTO_NAO_PODE_SER_NULLA = "Data de nascimento não pode ser nulla";
    
    public static final String O_ENDEREÇO_ESTA_NULL = "O endereço esta null";
    
    public static final String O_CPF_ESTA_VAZIO = "O cpf está vazio";
    
    public static final String O_CPF_ESTA_COM_UM_ESPAÇO_EM_BRANCO = "O cpf está com um espaço em branco";
    
    public static final String O_CPF_ESTA_INVALIDO = "O cpf está invalido";
    
    public static final String O_VALOR_ESTA_NULLO = "O valor esta nullo";
    
    public static final String O_VALOR_NÃO_É_VALIDO = "O valor não é valido";
    
    private Constante() {
    }

}
