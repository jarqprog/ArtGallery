package com.jarqprog.personapi.domains.contact.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.contact.ContactDTO;

@JsonDeserialize(as=ContactThin.class)
public interface ApiContactDTO extends ContactDTO {
}
