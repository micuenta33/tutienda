package com.Tutienda.validations;

import com.Tutienda.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistUsernameValidation implements ConstraintValidator<ExistUsername, String> {
    private final IUserRepository userRepository;

    public ExistUsernameValidation(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // Verificar si el username ya existe en el repositorio
        return !userRepository.findByUsername(username).isPresent();
    }
}
