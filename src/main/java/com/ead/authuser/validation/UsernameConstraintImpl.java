package com.ead.authuser.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameConstraintImpl implements ConstraintValidator<UsernameConstraint, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return StringUtils.hasText(username) && !StringUtils.containsWhitespace(username);
    }
}
