package br.com.contmatic.easyrandom;

import br.com.contmatic.easyrandom.enums.EnumStringInvalida;
import org.apache.commons.lang3.StringUtils;

import static br.com.contmatic.easyrandom.enums.EnumStringInvalida.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class EasyRandomStringInvalida implements ValidadorString {

    private String valor;

    @Override
    public String nome(EnumStringInvalida validadorString) {
        this.valorEmpty(validadorString);
        this.valorNull(validadorString);
        return this.valor;
    }

    @Override
    public String email(EnumStringInvalida validadorString) {
        this.valorEmpty(validadorString);
        return this.valor;
    }

    @Override
    public String cpf(EnumStringInvalida validadorString) {
        return this.valor;
    }

    @Override
    public String cnpj(EnumStringInvalida validadorString) {
        return this.valor;
    }

    @Override
    public String rua(EnumStringInvalida validadorString) {
        return this.valor;
    }

    @Override
    public String cep(EnumStringInvalida validadorString) {
        return this.valor;
    }

    @Override
    public String bairro(EnumStringInvalida validadorString) {
        return this.valor;
    }

    @Override
    public String phone(EnumStringInvalida validadorString) {
        return this.valor;
    }

    private void valorEmpty(EnumStringInvalida validadorString) {
        if (validadorString == BAIRRO_EMPTY || validadorString == RUA_EMPTY || validadorString == CEP_EMPTY
                || validadorString == EMAIL_EMPTY || validadorString == NOME_EMPTY || validadorString == CPF_EMPTY
                || validadorString == CNPJ_EMPTY || validadorString == PHONE_EMPTY) {
            this.valor = EMPTY;
        }
    }

    private void valorNull(EnumStringInvalida validadorString) {
        if (validadorString == BAIRRO_NULL || validadorString == RUA_NULL || validadorString == CEP_NULL
                || validadorString == EMAIL_NULL || validadorString == NOME_NULL || validadorString == CPF_NULL
                || validadorString == CNPJ_NULL || validadorString == PHONE_NULL) {
            this.valor = null;
        }
    }

}
