package com.jarqprog.artGallery.api.domains.artistic.author;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@Table(name="authors")
public class AuthorEntity extends DomainEntity {

    private String artisticNickname;
    private long contactId;

}
