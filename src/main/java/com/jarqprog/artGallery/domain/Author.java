package com.jarqprog.artGallery.domain;

import com.jarqprog.artGallery.config.EntityNumberConstants;
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
    private long id;

    private String artisticNickname;

    @OneToOne
    @JoinColumn(name="contact_id", referencedColumnName="id", nullable=false)
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
