package com.github.jlgrock.informatix.workmanager.controllers

import com.github.jlgrock.informatix.workmanager.domain.tokens.ResetPasswordDTO

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 *
 */
class PasswordMatchesValidator  implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        ResetPasswordDTO user = (ResetPasswordDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
