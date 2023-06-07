package com.example.doanjava.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.doanjava.Validator.annotation.ValidUserId;
import com.example.doanjava.entity.User;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if (user==null)
            return true;
        return user.getId()!=null;
    }
}
