package org.laba2.sportinventory2.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.laba2.sportinventory2.validation.CustomValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomValidator.CategoryValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {
    String message() default "Invalid category. Valid values are BEGINNER, AMATEUR, PROFESSIONAL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
