package com.jarqprog.commondomain.absmodel;

public interface DomainDTO extends DTO, Identity {

    void setId(long id);
    void setVersion(int version);

}
