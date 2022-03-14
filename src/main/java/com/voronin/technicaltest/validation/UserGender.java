package com.voronin.technicaltest.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

@Constraint(validatedBy = UserGenderConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserGender {

    public String message() default "Gender incorrect";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
