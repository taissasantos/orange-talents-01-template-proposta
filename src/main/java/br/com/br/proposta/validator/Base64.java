package br.com.br.proposta.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidacaoBase64.class)
public @interface Base64 {

	String message() default "Caracteres especiais não são aceitos!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String fieldName();

   	Class<?> domainClass();
}
