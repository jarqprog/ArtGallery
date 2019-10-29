package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.DomainModel;

import java.io.Serializable;

public interface RoleUser extends DomainModel, Serializable {

    String ANONYMOUS = "anonymous";

    SystemRole getRole();
    String getUserLogin();

}
