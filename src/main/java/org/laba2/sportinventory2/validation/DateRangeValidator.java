package org.laba2.sportinventory2.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.laba2.sportinventory2.validation.annotations.ValidDateRange;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Object> {

    private String startDateField;
    private String endDateField;

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        this.startDateField = constraintAnnotation.startDate();
        this.endDateField = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            // Reflection to access fields
            LocalDate startDate = (LocalDate) value.getClass().getDeclaredField(startDateField).get(value);
            LocalDate endDate = (LocalDate) value.getClass().getDeclaredField(endDateField).get(value);

            // Validate the date range
            if (startDate != null && endDate != null) {
                return !endDate.isBefore(startDate); // End date must not be before start date
            }
            return false; // If one or both dates are null
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false; // Validation fails if reflection fails
        }
    }
}
