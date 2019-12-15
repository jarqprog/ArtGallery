package com.jarqprog.commonapi.absmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.commondomain.DTO;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;
import java.util.UUID;

@ToString
@Data
public abstract class ApiDomainDTO implements DTO {

    @JsonIgnore
    private final String uuid = UUID.randomUUID().toString();

    private long id;
    private int version;

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof ApiDomainDTO)
            return uuid.equals(((ApiDomainDTO) other).uuid);
        return false;
    }

    @JsonIgnore
    @XmlTransient
    public final boolean isNew() {
        return this.id <= 0;
    }

    protected long getDTOId(ApiDomainDTO dto) {
        return dto != null ? dto.id : 0;
    }
}
