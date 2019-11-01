package com.jarqprog.artGallery.api.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.domain.Identity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@ToString
@Getter
public abstract class DomainDTO implements Identity, Serializable {

    @JsonIgnore
    private final String uuid = UUID.randomUUID().toString();

    @Setter private long id;
    @Setter private int version;

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof DomainDTO)
            return uuid.equals(((DomainDTO) other).uuid);
        return false;
    }

    @JsonIgnore
    @XmlTransient
    public final boolean isNew() {
        return this.id <= 0;
    }

    protected long getDTOId(DomainDTO dto) {
        return dto != null ? dto.id : 0;
    }
}
