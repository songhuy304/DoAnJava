package com.example.doanjava.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.doanjava.Validator.annotation.ValidUsername;
import com.example.doanjava.repository.IUserRepository;
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername,String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context){
        if (userRepository==null)
            return true;
        return userRepository.findByUserName(username)==null;
    }
}
