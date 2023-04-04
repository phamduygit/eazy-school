package com.example.learningspringboot.validations;

import com.example.learningspringboot.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;

    private String confirmField;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.field = constraintAnnotation.field();
        this.confirmField = constraintAnnotation.confirmField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(o).getPropertyValue(field);
        Object confirmFieldValue = new BeanWrapperImpl(o).getPropertyValue(confirmField);
        if (fieldValue != null) {
            return fieldValue.equals(confirmFieldValue);
        } else {
            return confirmFieldValue == null;
        }
    }
}
