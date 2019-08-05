package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authors")
public class Author implements DomainEntity {

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
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", artisticNickname='" + artisticNickname + '\'' +
                ", contact=" + contact +
                '}';
    }
}
