package com.jarqprog.artGallery.domain;

import com.jarqprog.artGallery.config.EntityNumberConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="commentaries")
public class Commentary implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.COMMENTARY;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name="picture_id")
    @ToString.Exclude
    private Picture picture;

    public Commentary(String content, User user, Picture picture) {
        this.content = content;
        this.user = user;
        this.picture = picture;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getEntityNumber() {
        return ENTITY_NUMBER;
    }
}
