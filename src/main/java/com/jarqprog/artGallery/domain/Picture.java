package com.jarqprog.artGallery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@AllArgsConstructor
@Table(name="PICTURES")
public class Picture extends AbstractEntity {

    private String title;

}
