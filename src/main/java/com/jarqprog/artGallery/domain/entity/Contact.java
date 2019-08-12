package com.jarqprog.artGallery.domain.entity;

import lombok.*;

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
