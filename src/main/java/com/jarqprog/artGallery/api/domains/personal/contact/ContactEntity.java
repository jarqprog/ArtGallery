package com.jarqprog.artGallery.api.domains.personal.contact;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Table(name="contacts")
public class ContactEntity extends DomainEntity {

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

    //todo Addressee

    ContactEntity(@NonNull String firstName, @NonNull String email) {
        this.firstName = firstName;
        this.email = email;
    }

    ContactEntity(@NonNull String firstName, String lastName, @NonNull String email) {
        this(firstName, email);
        this.lastName = lastName;
    }
}
