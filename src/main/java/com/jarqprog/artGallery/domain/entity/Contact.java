package com.jarqprog.artGallery.domain.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="contacts")
public class Contact extends DomainEntity {

    @NotNull
    private String firstName;

    private String lastName;

    private String nickname;

    @NonNull
    @NaturalId(mutable = true)
    @Column(unique = true)
    @Email
    private String email;

    //todo Addressee

    public Contact(@NonNull String firstName, @NonNull String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public Contact(@NonNull String firstName, String lastName, @NonNull String email) {
        this(firstName, email);
        this.lastName = lastName;
    }
}
