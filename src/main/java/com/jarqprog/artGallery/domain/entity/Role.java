package com.jarqprog.artGallery.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="roles")
public class Role extends DomainEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 12, unique = true)
    private AuthorizationRole role;
}
