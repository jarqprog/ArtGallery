package com.jarqprog.artGallery.domain;

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
public class Contact implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
