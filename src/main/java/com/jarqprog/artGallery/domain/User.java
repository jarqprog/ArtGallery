package com.jarqprog.artGallery.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.config.EntityNumberConstants;
import com.jarqprog.artGallery.view.jsonView.View;
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
public class User implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.USER;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonUser.class)
    private long id;

    @OneToOne
    @JoinColumn(name="contact_id", referencedColumnName="id", nullable=false)
    @JsonView(View.JsonUser.class)
    private Contact contact;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonManagedReference
    private Set<Commentary> commentaries;

    @JsonView(View.JsonUser.class)
    private String login;

    @JsonView(View.JsonUser.class)
    private String password;

    @JsonView(View.JsonUser.class)
    private boolean enabled;

    @JsonView(View.JsonUser.class)
    private boolean tokenExpired;

    public User(@NotNull Contact contact, @NotNull String login, @NotNull String password) {
        this.contact = contact;
        this.login = login;
        this.password = password;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getEntityNumber() {
        return ENTITY_NUMBER;
    }
}
