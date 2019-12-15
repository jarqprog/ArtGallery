package com.jarqprog.personapi.contact.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.contact.ContactDTO;

@JsonDeserialize(as=ContactThin.class)
public interface ApiContactDTO extends ContactDTO {
}
