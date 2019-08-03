package com.jarqprog.artGallery.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pictures")
public class Picture implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.PICTURE;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonPicture.class)
    private long id;

    @JsonView(View.JsonPicture.class)
    private String title;

    @OneToMany(mappedBy="picture", fetch = FetchType.EAGER)
    private Set<Commentary> commentaries;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getEntityNumber() {
        return ENTITY_NUMBER;
    }
}
