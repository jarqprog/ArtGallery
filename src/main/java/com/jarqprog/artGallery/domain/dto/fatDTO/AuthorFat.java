package com.jarqprog.artGallery.domain.dto.fatDTO;

import com.jarqprog.artGallery.domain.dto.AuthorDTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AuthorFat extends DomainDTO implements AuthorDTO, DTOFat {

    private String artisticNickname;
    private ContactFat contact;


    @Override
    public long getContactId() {
        return contact != null ? contact.getId() : 0;
    }
}
