package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Picture extends AbstractEntity {

    @Id
    @GeneratedValue private Long id;

    private String title;


}
