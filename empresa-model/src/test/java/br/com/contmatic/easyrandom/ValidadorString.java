package br.com.contmatic.easyrandom;

import br.com.contmatic.easyrandom.enums.EnumStringInvalida;

public interface ValidadorString {

    String nome(EnumStringInvalida validadorString);

    String email(EnumStringInvalida validadorString);

    String cpf(EnumStringInvalida validadorString);

    String cnpj(EnumStringInvalida validadorString);

    String rua(EnumStringInvalida validadorString);

    String cep(EnumStringInvalida validadorString);

    String bairro(EnumStringInvalida validadorString);

    String phone(EnumStringInvalida validadorString);

}
