package com.Tutienda.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistEmailValidation.class )
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistEmail {
    String message() default "Ya existe un usuario con este correo electronico";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
