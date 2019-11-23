package com.jarqprog.personapi.domains.contact.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.commonapi.absmodel.DTO;
import com.jarqprog.domainperson.model.contact.ContactData;

@JsonDeserialize(as=ContactThin.class)
public interface ContactDTO extends ContactData, DTO {
}
