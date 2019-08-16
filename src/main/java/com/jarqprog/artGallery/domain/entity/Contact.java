package com.jarqprog.artGallery.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="contacts")
public class Contact extends DomainEntity {

    @NotNull
    private String firstName;

    private String lastName;

    private String nickname;

    @NotNull
    @Column(unique = true)
    private String email;

    //todo Addressee

    public Contact(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public Contact(@NotNull String firstName, String lastName) {
        this(firstName);
        this.lastName = lastName;
    }
}
