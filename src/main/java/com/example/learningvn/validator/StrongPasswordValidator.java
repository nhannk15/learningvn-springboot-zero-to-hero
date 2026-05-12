package com.example.learningvn.validator;

import com.example.learningvn.annotation.StrongPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private int minLength;
    private boolean requireUppercase;
    private boolean requireLowercase;
    private boolean requireDigit;
    private boolean requireSpecialChar;

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.requireUppercase = constraintAnnotation.requireUppercase();
        this.requireLowercase = constraintAnnotation.requireLowercase();
        this.requireDigit = constraintAnnotation.requireDigit();
        this.requireSpecialChar = constraintAnnotation.requireSpecialChar();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        if (password.length() < minLength) {
            addConstraintViolation(context, "Password length must be at least " + minLength);
            return false;
        }

        if (requireUppercase && !password.matches(".*[A-Z].*")) {
            addConstraintViolation(context, "Password must have at least 1 Uppercase Character");
            return false;
        }

        if (requireLowercase && !password.matches(".*[a-z].*")) {
            addConstraintViolation(context, "Password must have at least 1 Lowercase Character");
            return false;
        }

        if (requireDigit && !password.matches(".*\\d.*")) {
            addConstraintViolation(context, "Password must have at least 1 digit");
            return false;
        }

        if (requireSpecialChar &&
                !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {

            addConstraintViolation(context,
                    "Password must have at least 1 special character");

            return false;
        }
        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
