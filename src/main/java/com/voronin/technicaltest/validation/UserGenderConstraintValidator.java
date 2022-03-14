package com.voronin.technicaltest.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class UserGenderConstraintValidator implements ConstraintValidator<UserGender, String> {
    @Value("${user.gender}")
    Set<String> genders;

    @Override
    public void initialize(UserGender userGender) {
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        return genders == null || genders.isEmpty() || genders.contains(gender);

    }
}
