package com.jarqprog.artGallery.api.domains.personal.user;

import com.jarqprog.artGallery.api.domains.personal.contact.ContactEntity;
import com.jarqprog.artGallery.api.domains.DomainEntity;
import lombok.*;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Table(name="users")
public class UserEntity extends DomainEntity {

    @NonNull
    @NotNull
    @NaturalId
    @Column(unique = true)
    private String login;

    @Setter
    private String password;

    @Setter
    private Boolean enabled = Boolean.TRUE;

    @Setter
    private Boolean tokenExpired = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactEntity contact;

    UserEntity(@NonNull String login) {
        this.login = login;
    }

    UserEntity(@NonNull String login, @NonNull ContactEntity contact) {
        this.login = login;
        this.contact = contact;
    }

    public UserEntity(@NonNull UserEntity user) {
        this.contact = user.getContact();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.tokenExpired = user.getTokenExpired();
    }
}
