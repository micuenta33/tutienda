package com.Tutienda.validations;

import com.Tutienda.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistEmailValidation implements ConstraintValidator<ExistEmail,String> {
    private final IUserRepository userRepository;

    public ExistEmailValidation(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByEmail(s);
    }
}
