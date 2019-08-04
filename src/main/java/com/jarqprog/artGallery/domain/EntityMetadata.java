package com.jarqprog.artGallery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="entity_metadata")
public class EntityMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @GeneratedValue
    private LocalDateTime createdDate;

//    @CreatedBy
//    private GalleryUser user;

    private boolean isRemovedEntity;

    @NonNull
    private final long entityId;

    @NonNull
    private final long entityNumber;

    @Setter
    private String archivedEntityAsString;

    public EntityMetadata(DomainEntity domainEntity) {
        this.entityId = domainEntity.getId();
        this.entityNumber = domainEntity.getEntityNumber();
    }

    public EntityMetadata(DomainEntity removedDomainEntity, String removedEntityAsString) {
        this.entityId = removedDomainEntity.getId();
        this.entityNumber = removedDomainEntity.getEntityNumber();
        this.isRemovedEntity = true;
        this.archivedEntityAsString = removedEntityAsString;
    }
}
