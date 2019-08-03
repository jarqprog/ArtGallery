package com.jarqprog.artGallery.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.view.jsonView.View;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pictures")
public class Picture implements MetadataSupplier {

    private static final long entityNumber = 101;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.JsonPicture.class)
    private long id;

    @JsonView(View.JsonPicture.class)
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
