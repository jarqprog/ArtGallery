package com.jarqprog.artGallery.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class DomainEntity {


    private final String uuid = UUID.randomUUID().toString();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter private long id;
    @CreatedDate @Setter @Column(updatable = false) private LocalDateTime createdDate;
    @LastModifiedDate @Setter private LocalDateTime modifiedDate;
    //@CreatedBy private User user; //todo

    @Version @Setter private int version;

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof DomainEntity)
            return uuid.equals(((DomainEntity) other).uuid);
        return false;
    }


}
