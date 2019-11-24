package com.jarqprog.web.service.registration;


import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.usecase.registration.RegistrationFormDTO;
import com.jarqprog.domainperson.model.user.User;

public interface UserRegistration {

    User createUserFromRegistration(RegistrationFormDTO registrationFormDTO, Contact contactData);

}
