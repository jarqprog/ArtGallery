package com.jarqprog.artGallery.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
