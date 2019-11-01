package com.jarqprog.artGallery.api.domains.personal.contact.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.domain.personal.ContactData;

@JsonDeserialize(as=ContactThin.class)
public interface ContactDTO extends ContactData, DTO {
}
