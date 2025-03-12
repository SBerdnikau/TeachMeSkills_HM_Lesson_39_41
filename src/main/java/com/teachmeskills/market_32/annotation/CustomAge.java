package com.teachmeskills.market_32.annotation;

import com.teachmeskills.market_32.servise.validator.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented // могут пользоваться другие
@Constraint(validatedBy = {AgeValidator.class})
public @interface CustomAge {
    String message() default "Age must be between 18 and 120";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
