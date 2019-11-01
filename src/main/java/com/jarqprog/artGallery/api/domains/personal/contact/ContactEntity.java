package com.jarqprog.artGallery.api.domains.personal.contact;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.domain.personal.Contact;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="contacts")
public class ContactEntity extends DomainEntity implements Contact {


    public static ContactEntity fromContact(@NonNull final Contact contact) {
        return new ContactEntity(contact);
    }

    @NonNull
    @NotNull
    private String firstName;

    private String lastName;

    private String nickname;

    @NonNull
    @NotNull
    @NaturalId(mutable = true)
    @Column(unique = true)
    @Email
    private String email;

    private ContactEntity(long id, int version, @NonNull String firstName, String lastName,
                          String nickname, @NonNull String email) {
        super(id, version);
        assert StringUtils.isNotEmpty(firstName);
        assert StringUtils.isNotEmpty(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
    }

    private ContactEntity(@NonNull Contact contact) {
        this(contact.getId(), contact.getVersion(), contact.getFirstName(), contact.getLastName(),
                contact.getNickname(), contact.getEmail());
    }
}
