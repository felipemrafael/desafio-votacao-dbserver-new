package com.desafio.dbserver.pauta.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {

    String message() default "cpf inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
