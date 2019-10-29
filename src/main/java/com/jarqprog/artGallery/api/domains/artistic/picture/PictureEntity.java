package com.jarqprog.artGallery.api.domains.artistic.picture;

import com.jarqprog.artGallery.api.domains.artistic.author.AuthorEntity;
import com.jarqprog.artGallery.api.domains.DomainEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@Table(name="pictures")
public class PictureEntity extends DomainEntity {

    private String title;
    private String path;
    private String userLogin;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
}
