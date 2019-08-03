package com.jarqprog.artGallery.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @GeneratedValue
    private LocalDateTime createdDate;

    @LastModifiedDate
    @GeneratedValue
    private LocalDateTime modifiedDate;

    @Version
    @GeneratedValue
    private Long version;

//    @CreatedBy
//    private User user;

//    @LastModifiedBy
//    private User user;

    private LocalDateTime discontinueDate;

}
