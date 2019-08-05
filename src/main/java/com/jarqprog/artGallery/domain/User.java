package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="users")
public class User implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name="contact_id", referencedColumnName="id", nullable=false)
    private Contact contact;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<Commentary> commentaries;

    private String login;

    private String password;

    private boolean enabled;

    private boolean tokenExpired;

    public User(@NotNull Contact contact, @NotNull String login, @NotNull String password) {
        this.contact = contact;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", contact=" + contact +
                ", commentaries=" + commentaries +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", tokenExpired=" + tokenExpired +
                '}';
    }
}
