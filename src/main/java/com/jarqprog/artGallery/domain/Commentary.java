package com.jarqprog.artGallery.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="commentaries")
public class Commentary extends DomainEntity {

    @NotNull
    private String content;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    public Commentary(String content) {
        this.content = content;
    }
}
