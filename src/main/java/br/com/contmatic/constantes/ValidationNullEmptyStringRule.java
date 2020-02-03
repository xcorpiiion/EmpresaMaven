package br.com.contmatic.constantes;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;

public final class ValidationNullEmptyStringRule {
	
	private ValidationNullEmptyStringRule() {
		
	}
	
	public static void stringIsEmpty(String yourString) {
	    if (StringUtils.isEmpty(yourString) || yourString.trim().equals("")) {
            throw new EmptyStringException("O nome está vazio");
        }
	}
	
	public static void objectIsNull(Object yourObject) {
	    if (yourObject instanceof Date) {
	        System.out.println("Teste ok");
            throw new NullPointerException("teste");
        }
	}
	
	public static void emailValidation(String email) {
        if(!email.matches(Constante.VALIDATION_EMAIL))
            throw new StringFormatException("O email que você informou esta incorreto");
    }

	public static void dataNascimentoIsNull(Date dataNascimento) {
		if (dataNascimento == null) {
			throw new NullPointerException("A data de nascimento está null");
		}
	}
	
	public static void lojaIsNull(Empresa loja) {
		if (loja == null) {
			throw new NullPointerException("A loja está null");
		}
	}
	
	public static void enderecoIsNull(Endereco endereco) {
		if (endereco == null) {
			throw new NullPointerException("O endereco está null");
		}
	}
	
}
