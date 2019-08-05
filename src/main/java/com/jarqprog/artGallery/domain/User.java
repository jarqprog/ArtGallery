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
@ToString
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;

    public User(@NotNull Contact contact, @NotNull String login, @NotNull String password) {
        this.contact = contact;
        this.login = login;
        this.password = password;
    }
}
