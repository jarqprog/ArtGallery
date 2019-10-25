package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
public abstract class DomainDTO implements DTO, Serializable {

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

    @Override
    public final boolean isNew() {
        return this.id <= 0;
    }
}
