package com.jarqprog.web.service.registration;


import com.jarqprog.domainperson.contact.Contact;
import com.jarqprog.domainperson.registration.RegistrationFormDTO;
import com.jarqprog.domainperson.user.User;

public interface UserRegistration {

    User createUserFromRegistration(RegistrationFormDTO registrationFormDTO, Contact contactData);

}
