package com.jarqprog.artGallery.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="pictures")
public class Picture extends DomainEntity {

    private String title;

    private String path;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
