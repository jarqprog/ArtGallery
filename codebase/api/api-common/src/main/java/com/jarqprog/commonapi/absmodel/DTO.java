package com.jarqprog.commonapi.absmodel;


import com.jarqprog.commondomain.absmodel.Identity;

import java.io.Serializable;

public interface DTO extends Identity, Serializable {

    long getId();
    void setId(long id);
    int getVersion();
    void setVersion(int version);
    boolean isNew();

}
