package com.jarqprog.artGallery.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="users")
public class User extends DomainEntity {

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String login;

    private String password;

    private Boolean enabled;

    private Boolean tokenExpired;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(@NotNull Contact contact, @NotNull String login, @NotNull String password) {
        this.contact = contact;
        this.login = login;
        this.password = password;
    }

    public User(@NotNull Contact contact, @NotNull String login, @NotNull String password, @NotNull Role roles) {
        this(contact, login, password);
        this.roles = new HashSet<>();
        this.roles.add(roles);
    }

    public User(@NotNull User user) {
        this.contact = user.getContact();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.tokenExpired = user.getTokenExpired();
        this.roles = user.getRoles();
    }
}
