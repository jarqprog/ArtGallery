package com.jarqprog.artGallery.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="commentaries")
public class Commentary extends DomainEntity {

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    public Commentary(String content) {
        this.content = content;
    }
}
