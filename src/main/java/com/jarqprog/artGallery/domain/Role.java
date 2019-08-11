package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="roles")
public class Role extends DomainEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 12, unique = true)
    private Roles role;
}
