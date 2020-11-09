package br.com.contmatic.easyrandom.enums;

public enum EnumStringInvalida {

    NOME_NULL("nome"),
    NOME_EMPTY("nome"),
    NOME_BLANK_SPACE("nome"),
    NOME_INVALID_SIZE("nome"),
    NOME__WITH_SPECIAL_CARACTER("nome"),
    EMAIL_NULL("email"),
    EMAIL_BLANK_SPACE("email"),
    EMAIL_EMPTY("email"),
    EMAIL_INVALID_SIZE("email"),
    EMAIL_WITH_SPECIAL_CARACTER("email"),
    EMAIL_WITH_BLANK_SPACE("email"),
    EMAIL_WITH_NUMBER_AFTER_ARROBA("email"),
    EMAIL_WITHOU_ARROBA("email"),
    EMAIL_WITHOUT_DOT("email"),
    CPF_NULL("cpf"),
    CPF_EMPTY("cpf"),
    CPF_BLANK_SPACE("cpf"),
    CPF_WITH_SPECIAL_CARACTER("cpf"),
    CNPJ_NULL("cnpj"),
    CNPJ_EMPTY("cnpj"),
    CNPJ_BLANK_SPACE("cnpj"),
    CNPJ_WITH_SPECIAL_CARACTER("cnpj"),
    RUA_NULL("rua"),
    RUA_EMPTY("rua"),
    RUA_LESS_3_CARACTER("rua"),
    RUA_GREATER_50_CARACTER("rua"),
    RUA_WITH_SPECIAL_CARACTER("rua"),
    CEP_NULL("cep"),
    CEP_EMPTY("cep"),
    CEP_CONTAINS_WORD("cep"),
    CEP_LENGTH_DIFFERENCE_8("cep"),
    BAIRRO_NULL("bairro"),
    BAIRRO_EMPTY("bairro"),
    BAIRRO_CONTAINS_NUMBER("bairro"),
    BAIRRO_LESS_3_CARACTER("bairro"),
    BAIRRO_GREATER_50_CARACTER("bairro"),
    BAIRRO_WITH_SPECIAL_CARACTER("bairro"),
    PHONE_NULL("phone"),
    PHONE_EMPTY("phone"),
    PHONE_BLANK_SPACE("phone"),
    PHONE_INVALID_SIZE("phone");

    private String campo;

    private EnumStringInvalida(String campo) {
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}
