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
    private final long domainEntityId;

    @NonNull
    private final long domainEntityNumber;

    @Setter
    private String archivedEntityAsString;

    public EntityMetadata(MetadataSupplier metadataSupplier) {
        this.domainEntityId = metadataSupplier.getId();
        this.domainEntityNumber = metadataSupplier.getEntityNumber();
    }

    public EntityMetadata(MetadataSupplier removedMetadataSupplier, String removedEntityAsString) {
        this.domainEntityId = removedMetadataSupplier.getId();
        this.domainEntityNumber = removedMetadataSupplier.getEntityNumber();
        this.isRemovedEntity = true;
        this.archivedEntityAsString = removedEntityAsString;
    }
}
