package com.jarqprog.commonapi.absmodel;

import com.jarqprog.commondomain.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@MappedSuperclass
@NoArgsConstructor
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class DomainEntity implements Identity {

    private final String uuid = UUID.randomUUID().toString();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter private long id;
    @CreatedDate @Setter @Column(updatable = false) private Date createdDate;
    @LastModifiedDate @Setter private Date modifiedDate;
    //@CreatedBy private User user; //todo

    @Version @Setter private int version;

    protected DomainEntity(long id, int version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof DomainEntity)
            return uuid.equals(((DomainEntity) other).uuid);
        return false;
    }

    protected long getEntityId(DomainEntity entity) {
        return entity != null ? entity.getId() : 0;
    }

    @Override
    public boolean isNew() {
        return id > 0;
    }


}
