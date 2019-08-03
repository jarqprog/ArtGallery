package com.jarqprog.artGallery.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@Setter
@ToString
@Table(name="users")
public class User implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.USER;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonUser.class)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    @JsonView(View.JsonUser.class)
    private final Contact contact;

    @JsonView(View.JsonUser.class)
    @NotNull
    private String password;

    @JsonView(View.JsonUser.class)
    private boolean enabled;

    @JsonView(View.JsonUser.class)
    private boolean tokenExpired;

    public User(@NotNull Contact contact, @NotNull String password) {
        this.contact = contact;
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
