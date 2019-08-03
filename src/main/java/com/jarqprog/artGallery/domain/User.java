package com.jarqprog.artGallery.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private final Contact contact;

    @JsonView(View.JsonUser.class)
    private String password;

    @JsonView(View.JsonUser.class)
    private boolean enabled;

    @JsonView(View.JsonUser.class)
    private boolean tokenExpired;

    public User(Contact contact, String password) {
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
