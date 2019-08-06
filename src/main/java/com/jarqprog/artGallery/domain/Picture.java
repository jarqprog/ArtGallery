package com.jarqprog.artGallery.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="pictures")
public class Picture extends DomainEntity {

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
