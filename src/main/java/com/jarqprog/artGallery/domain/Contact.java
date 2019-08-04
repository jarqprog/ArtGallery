package com.jarqprog.artGallery.domain;

import com.jarqprog.artGallery.config.EntityNumberConstants;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contacts")
public class Contact implements DomainEntity {

    private static final long ENTITY_NUMBER = EntityNumberConstants.CONTACT;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String firstName;

    private String lastName;

    private String nickname;

    private String email;

    //todo Addressee

    @Nullable
    @OneToOne(mappedBy = "contact")
    private User user;

    @Nullable
    @OneToOne(mappedBy = "contact")
    private Author author;

    public Contact(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public Contact(@NotNull String firstName, String lastName) {
        this(firstName);
        this.lastName = lastName;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getEntityNumber() {
        return ENTITY_NUMBER;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
