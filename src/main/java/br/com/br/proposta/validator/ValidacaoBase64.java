package br.com.br.proposta.validator;

import static org.apache.tomcat.util.codec.binary.Base64.isBase64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidacaoBase64 implements ConstraintValidator<Base64, String> {
	
	@Override
    public void initialize(Base64 constraintAnnotation) {
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        return string != null && isBase64(string.getBytes());
    }

}
