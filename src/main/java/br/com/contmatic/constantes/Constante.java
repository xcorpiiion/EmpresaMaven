package br.com.contmatic.constantes;

/**
 * The Class Constante.
 */
public final class Constante {

    /**
     * The Constant ILLEGAL_WORD.
     */
    public static final String ILLEGAL_WORD = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$";

    /**
     * The Constant ILLEGAL_NUMBER.
     */
    public static final String ILLEGAL_NUMBER = "^[0-9]*$";

    /**
     * The Constant VALIDATION_EMAIL.
     */
    public static final String VALIDATION_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     * The Constant PHONE_VALIDATION.
     */
    public static final String PHONE_VALIDATION = "(([0-9]{8})|([0-9]{9}))";

    public static final String VALID = "valid";

    public static final String NOME_NULL = "nomeNull";

    public static final String NOME_EMPTY = "nomeEmpty";

    public static final String NOME_BLANK_SPACE = "nomeBlankSpace";

    public static final String NOME_LESS_3_CARACTER = "nomeLess3Caracter";

    public static final String NOME_GREATER_CARACTER = "nomeGreaterCaracter";

    public static final String NOME_WITH_SPECIAL_CARACTER = "nomeWithSpecialCaracter";

    public static final String EMAIL_NULL = "emailNull";

    public static final String EMAIL_EMPTY = "emailEmpty";

    public static final String EMAIL_BLANK_SPACE = "emailBlankSpace";

    public static final String EMAIL_INVALID = "emailInvalid";

    public static final String EMAIL_LESS_10_CARACTERES = "emailLess10Caracteres";

    public static final String EMAIL_GREATER_100_CARACTERES = "emailGreater100Caracteres";

    public static final String EMAIL_WITH_BLANK_SPACE_IN_WORD = "emailWithBlankSpaceInWord";

    public static final String EMAIL_WITH_NUMBER_AFTER_ARROBA = "emailWithNumberAfterArroba";

    public static final String EMAIL_WITHOUT_ARROBA = "emailWithoutArroba";

    public static final String EMAIL_WITHOUT_PONTO_COM = "emailWithoutPontoCom";

    public static final String EMAIL_WITHOUT_COM = "emailWithoutCom";

    public static final String EMAIL_WITH_SPECIAL_CARACTER = "emailWithSpecialCaracter";

    public static final String CNPJ_NULL = "cnpjNull";

    public static final String CNPJ_BLANK_SPACE = "cnpjBlankSpace";

    public static final String CNPJ_CONTAINS_WORD = "cnpjContainsWord";

    public static final String CNPJ_WRONG_SIZE = "cnpjWrongSize";

    public static final String CPF_NULL = "cpfNull";

    public static final String CPF_BLANK_SPACE = "cpfBlankSpace";

    public static final String CPF_EMPTY = "cpfEmpty";

    public static final String CPF_INVALID = "invalidCpf";

    public static final String CPF_CONTAINS_WORD = "cpfContainsWord";

    public static final String CPF_WRONG_SIZE = "cpfWrongSize";

    public static final String ENDERECO_NULL = "enderecoNull";

    public static final String PRODUTO_NULL = "produtoNull";

    public static final String FUNCIONARIO_NULL = "funcionarioNull";

    public static final String CLIENTE_NULL = "clienteNull";

    public static final String DATA_NASCIMENTO_NULL = "dataNascimentoNull";

    public static final String TELEFONE_NULL = "telefoneNull";

    public static final String CARGO_NULL = "cargoNull";

    public static final String SALARIO_NULL = "salarioNull";

    public static final String SALARIO_LESS_1 = "salarioLess1";

    public static final String TIPO_CONTRATO_NULL = "tipoContratoNull";

    public static final String CIDADE_NULL = "cidadeNull";

    public static final String CIDADE_EMPTY = "cidadeEmpty";

    public static final String CIDADE_BLANK_SPACE = "cidadeBlankSpace";

    public static final String CIDADE_CONTAINS_NUMBER = "cidadeContainsNumber";

    public static final String CIDADE_LESS_3_CARACTER = "cidadeLess3Caracter";

    public static final String CIDADE_GREATER_CARACTER = "cidadeGreaterCaracter";

    public static final String CIDADE_WITH_SPECIAL_CARACTER = "cidadeWithSpecialCaracter";

    public static final String CIDADE_GREATER_50_CARACTER = "cidadeGreater50Caracter";

    public static final String ESTADO_NULL = "estadoNull";

    public static final String NUMERO_RESIDENCIA_LESS_THAN_ZERO = "numeroResidenciaLessThanZero";

    public static final String RUA_NULL = "ruaNull";

    public static final String RUA_EMPTY = "ruaEmpty";

    public static final String RUA_BLANK_SPACE = "ruaBlankSpace";

    public static final String RUA_LESS_3_CARACTER = "ruaLess3Caracter";

    public static final String RUA_WITH_SPECIAL_CARACTER = "ruaWithSpecialCaracter";

    public static final String RUA_GREATER_50_CARACTER = "ruaGreater50Caracter";

    public static final String CEP_NULL = "cepNull";

    public static final String CEP_BLANK_SPACE = "cepBlankSpace";

    public static final String CEP_EMPTY = "cepEmpty";

    public static final String CEP_CONTAINS_WORD = "cepContainsWord";

    public static final String CEP_LENGTH_DIFFERENCE_8 = "cepLengthDifference8";

    public static final String BAIRRO_NULL = "bairroNull";

    public static final String BAIRRO_EMPTY = "bairroEmpty";

    public static final String BAIRRO_BLANK_SPACE = "bairroBlankSpace";

    public static final String BAIRRO_LESS_3_CARACTER = "bairroLess3Caracter";

    public static final String BAIRRO_WITH_SPECIAL_CARACTER = "bairroWithSpecialCaracter";

    public static final String BAIRRO_GREATER_50_CARACTER = "bairroGreater50Caracter";

    public static final String PHONE_NULL = "phoneNull";

    public static final String PHONE_EMPTY = "phoneEmpty";

    public static final String PHONE_BLANK_SPACE = "phoneBlankSpace";

    public static final String PHONE_INVALID_SIZE = "phoneInvalidSize";

    public static final String TIPO_TELEFONE_NULL = "tipoTelefoneNull";

    public static final String DDD_NULL = "dddNull";

    public static final String PRECO_NULL = "precoNull";

    public static final String PRECO_LESS_1 = "precoLess1";

    public static final String ESTOQUE_LESS_0 = "estoqueLess0";


    /**
     * Instantiates a new constante.
     */
    private Constante() {
    }

}
