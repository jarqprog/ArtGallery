package com.jarqprog.domainperson.contact;

import com.jarqprog.commondomain.absmodel.DomainModel;
import com.jarqprog.domainperson.registration.RegistrationFormDTO;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Value
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class DomainContact extends DomainModel implements Contact {

    public static DomainContact fromContact(@NonNull final Contact contact) {
        return new DomainContact(contact);
    }

    public static DomainContact mergeID(long id, @NonNull final Contact contact) {
        assert id > 0;
        return new DomainContact(id, contact);
    }

    public static DomainContact fromRegistration(RegistrationFormDTO registration) {
        return new DomainContact(registration.firstName(), registration.lastName(), registration.email());
    }

    @Builder(builderMethodName = "createWith")
    public static DomainContact buildWithData(long id, int version, String firstName, String lastName, String email) {
        return new DomainContact(id, version, firstName, lastName, email);
    }

    private final String firstName;

    private final String lastName;

    @NonNull
    private final String email;

    private DomainContact(long id, int version, String firstName, String lastName, @NonNull String email) {
        super(id, version);
        assert StringUtils.isNotEmpty(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private DomainContact(long id, @NonNull Contact contact) {
        this(id, contact.getVersion(), contact.getFirstName(), contact.getLastName(), contact.getEmail());
    }

    private DomainContact(String firstName, String lastName, @NonNull String email) {
        this(0, 0, firstName, lastName, email);
    }

    private DomainContact(@NonNull Contact contact) {
        this(contact.getId(), contact);
    }
}
