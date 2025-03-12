package com.teachmeskills.market_32.servise.validator;

import com.teachmeskills.market_32.annotation.CustomAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//ConstraintValidator<CustomAge, Integer> - принимает тип анотации и кокого тип поля хранит
public class AgeValidator implements ConstraintValidator<CustomAge, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value >= 18 && value <= 120;
    }
}
