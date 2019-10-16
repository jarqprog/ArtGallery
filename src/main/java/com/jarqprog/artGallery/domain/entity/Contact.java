package com.jarqprog.artGallery.domain.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
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

    @NotNull
    @NaturalId(mutable = true)
    @Column(unique = true)
    private String email;

    //todo Addressee

    public Contact(@NotNull String firstName, @NotNull String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public Contact(@NotNull String firstName, String lastName, @NotNull String email) {
        this(firstName, email);
        this.lastName = lastName;
    }
}
