package com.jarqprog.personapi.contact;

import com.jarqprog.commonapi.absmodel.DomainEntity;
import com.jarqprog.domainperson.contact.Contact;
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

    private String firstName;

    private String lastName;

    @NonNull
    @NotNull
    @NaturalId(mutable = true)
    @Column(unique = true)
    @Email
    private String email;

    private ContactEntity(long id, int version, String firstName, String lastName, @NonNull String email) {
        super(id, version);
        assert StringUtils.isNotEmpty(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private ContactEntity(@NonNull Contact contact) {
        this(contact.getId(), contact.getVersion(), contact.getFirstName(), contact.getLastName(), contact.getEmail());
    }
}
