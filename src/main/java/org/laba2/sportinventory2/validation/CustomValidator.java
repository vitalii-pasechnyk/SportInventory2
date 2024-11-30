package org.laba2.sportinventory2.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.laba2.sportinventory2.entity.Category;
import org.laba2.sportinventory2.validation.annotations.ValidCategory;

public class CustomValidator {

    // Валідація для категорії
    public static class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.isBlank()) {
                return false; // Категорія не може бути порожньою
            }

            try {
                // Перевірка, чи входить значення до переліку ENUM Category
                Category.valueOf(value.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                return false; // Значення не є частиною ENUM
            }
        }
    }
}
