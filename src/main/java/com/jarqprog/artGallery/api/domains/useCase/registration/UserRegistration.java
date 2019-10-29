package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.Contact;;
import com.jarqprog.artGallery.domain.personal.User;

public interface UserRegistration {

    User createUserFromRegistration(RegistrationForm registrationForm, Contact contact);

}
