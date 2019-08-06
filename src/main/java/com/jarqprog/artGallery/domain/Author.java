package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="authors")
public class Author extends DomainEntity {

    private String artisticNickname;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Author(Contact contact) {
        this.contact = contact;
    }
}
