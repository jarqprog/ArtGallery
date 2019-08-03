package com.jarqprog.artGallery.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@Table(name="commentaries")
public class Commentary implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.COMMENTARY;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonCommentary.class)
    private long id;

    @NotNull
    @JsonView(View.JsonCommentary.class)
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private final User user;

    @ManyToOne
    @JoinColumn(name="picture_id")
    private final Picture picture;

    public Commentary(@NotNull String content, @NotNull User user, @NotNull Picture picture) {
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
