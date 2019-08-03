package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pictures")
public class Picture extends AbstractEntity {

    private String title;

}
