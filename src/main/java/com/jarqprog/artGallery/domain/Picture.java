package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pictures")
public class Picture implements DomainEntity {

    private static final long entityNumber = 101;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getEntityNumber() {
        return entityNumber;
    }

}
