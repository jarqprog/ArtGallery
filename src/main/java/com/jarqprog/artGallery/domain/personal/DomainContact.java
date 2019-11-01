package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.DomainModel;
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

    @Builder(builderMethodName = "createWith")
    public static DomainContact buildWithData(long id, int version, @NonNull String firstName, String lastName,
                                              String nickname, @NonNull String email) {
        return new DomainContact(id, version, firstName, lastName, nickname, email);
    }

    @NonNull
    private final String firstName;

    private final String lastName;

    private final String nickname;

    @NonNull
    private final String email;



    private DomainContact(long id, int version, @NonNull String firstName, String lastName,
                          String nickname, @NonNull String email) {
        super(id, version);
        assert StringUtils.isNotEmpty(firstName);
        assert StringUtils.isNotEmpty(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
    }

    private DomainContact(long id, @NonNull Contact contact) {
        this(id, contact.getVersion(), contact.getFirstName(), contact.getLastName(),
                contact.getNickname(), contact.getEmail());
    }

    private DomainContact(@NonNull Contact contact) {
        this(contact.getId(), contact);
    }
}
