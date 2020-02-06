package br.com.contmatic.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidadorAnnotionsMsgErro {
 
    public static String returnAnnotationMsgError(Object yourObject, String msgErroEsperada) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> erros = validador.validate(yourObject);
        List<String> errosMsg = new ArrayList<>();
        erros.stream().forEach(t1 -> errosMsg.add(t1.getMessage()));
        for(String erros1 : errosMsg) {
            if(msgErroEsperada.equalsIgnoreCase(erros1)) {
                System.out.println("mensagem de erro: " + errosMsg.get(0));
                return erros1;
            }
        }
        return "";
    }
    
}