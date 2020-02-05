package br.com.contmatic.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidadorAnnotionsMsgErro<T> {
    
    public String returnAnnotationMsgError(T t){
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> erros = validador.validate(t);
        List<String> errosMsg = new ArrayList<>();
        erros.stream().forEach(t1 -> errosMsg.add(t1.getMessage()));
        // return !errosMsg.isEmpty;
        return errosMsg.get(0);
    }
 
    public static String lalala(Object yourObject) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> erros = validador.validate(yourObject);
        List<String> errosMsg = new ArrayList<>();
        erros.stream().forEach(t1 -> errosMsg.add(t1.getMessage()));
        return errosMsg.get(0);
    }
    
}