package com.jarqprog.artGallery.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="pictures")
public class Picture extends DomainEntity {

    private String title;

    private String path;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
