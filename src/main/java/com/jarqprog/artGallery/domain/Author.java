package com.jarqprog.artGallery.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.config.EntityNumberConstants;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authors")
public class Author implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.AUTHOR;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonAuthor.class)
    private long id;

    @JsonView(View.JsonAuthor.class)
    private String artisticNickname;

    @OneToOne
    @JoinColumn(name="contact_id", referencedColumnName="id", nullable=false)
    @JsonView(View.JsonAuthor.class)
    private Contact contact;

    @OneToMany(mappedBy="author", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Picture> arts;

    public Author(Contact contact) {
        this.contact = contact;
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
