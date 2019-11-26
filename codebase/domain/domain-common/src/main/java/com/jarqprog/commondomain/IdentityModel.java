package com.jarqprog.commondomain;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public abstract class IdentityModel implements Identity {

    private final String uuid = UUID.randomUUID().toString();

    private final long id;
    private final int version;

    protected IdentityModel(IdentityModel identityModel) {
        this(identityModel.id, identityModel.version);
    }

    protected IdentityModel(long id, int version) {
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
        if (other instanceof IdentityModel)
            return uuid.equals(((IdentityModel) other).uuid);
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
