package com.jarqprog.artGallery.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@Table(name="contacts")
public class Contact implements MetadataSupplier {


    private static final long ENTITY_NUMBER = EntityNumberConstants.CONTACT;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonContact.class)
    private long id;

    @NotNull
    @JsonView(View.JsonContact.class)
    private String firstName;

    @JsonView(View.JsonContact.class)
    private String lastName;

    @JsonView(View.JsonContact.class)
    private String email;

    @OneToOne(mappedBy = "contact")
    private User user;

    public Contact(@NotNull String firstName) {
        this.firstName = firstName;
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
