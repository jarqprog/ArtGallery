package com.jarqprog.artGallery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
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

    private LocalDateTime discontinueDate;

    @NonNull
    private final long domainEntityId;

    @NonNull
    private final long domainEntityNumber;

    public EntityMetadata(DomainEntity domainEntity) {
        this.domainEntityId = domainEntity.getId();
        this.domainEntityNumber = domainEntity.getEntityNumber();
    }
}
