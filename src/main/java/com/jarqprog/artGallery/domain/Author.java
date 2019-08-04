package com.jarqprog.artGallery.domain;

import com.jarqprog.artGallery.config.EntityNumberConstants;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", artisticNickname='" + artisticNickname + '\'' +
                ", contact=" + contact +
                ", arts=" + arts +
                '}';
    }
}
