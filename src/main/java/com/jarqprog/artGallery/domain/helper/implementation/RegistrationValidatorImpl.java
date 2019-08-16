package com.jarqprog.artGallery.domain.helper.implementation;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.exception.CreationException;
import com.jarqprog.artGallery.domain.helper.PasswordValidator;
import com.jarqprog.artGallery.domain.helper.RegistrationValidator;
import com.jarqprog.artGallery.springData.repository.ContactRepository;
import com.jarqprog.artGallery.springData.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationValidatorImpl implements RegistrationValidator {

    //todo some email validator should be used
    @Autowired private ContactRepository contactRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordValidator passwordValidator;

    @Override
    public void validateContactData(RegistrationDTO registrationDTO) throws CreationException {
        String firstName = registrationDTO.getFirstName();
        validateFirstName(firstName);
        String email = registrationDTO.getEmail();
        validateEmail(email);
    }

    @Override
    public void validateUserData(RegistrationDTO registrationDTO) {
        String login = registrationDTO.getLogin();
        validateLogin(login);
        String password = registrationDTO.getPassword();
        validatePassword(password);
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new CreationException(ContactDTO.class, "First name is missing!");
        }
    }

    private void validateEmail(String email) {
        if (contactRepository.findOneByEmail(email).isPresent()) {
            throw new CreationException(ContactDTO.class, "Contact with this email already exists!");
        }
    }

    private void validateLogin(String login) {
        if (userRepository.findOneByLogin(login).isPresent()) {
            throw new CreationException(UserDTO.class, "User with this login already exists!");
        }
    }

    private void validatePassword(String password) {
        if (!passwordValidator.validate(password)) {
            throw new CreationException(UserDTO.class, "Password is incorrect!");
        }
    }
}
