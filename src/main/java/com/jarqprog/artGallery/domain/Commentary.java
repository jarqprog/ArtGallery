package com.jarqprog.artGallery.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
