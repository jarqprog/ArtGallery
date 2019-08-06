package com.jarqprog.artGallery.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class DomainEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;
    @CreatedDate private LocalDateTime createdDate;
    @LastModifiedDate private LocalDateTime modifiedDate;
    private boolean isRemoved;
    //@CreatedBy private User user; todo

    public void markRemoved() {
        isRemoved = true;
    }


}
