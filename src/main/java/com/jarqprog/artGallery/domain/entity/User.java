package com.jarqprog.artGallery.domain.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="users")
public class User extends DomainEntity {

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @NotNull
    @NaturalId(mutable = true)
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

    private Boolean enabled = Boolean.TRUE;

    private Boolean tokenExpired = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.JOIN)
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
