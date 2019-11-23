package com.jarqprog.commondomain.absmodel;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public abstract class DomainModel implements Identity {

    private final String uuid = UUID.randomUUID().toString();

    private final long id;
    private final int version;

    protected DomainModel(DomainModel domainModel) {
        this(domainModel.id, domainModel.version);
    }

    protected DomainModel(long id, int version) {
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
        if (other instanceof DomainModel)
            return uuid.equals(((DomainModel) other).uuid);
        return false;
    }

    protected long getModelId(Identity model) {
        return model != null ? model.getId() : 0;
    }

    @Override
    public boolean isNew() {
        return id > 0;
    }
}
