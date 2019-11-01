package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.Identity;

import java.io.Serializable;

public interface RoleUserData extends Identity, Serializable {

    SystemRole getRole();
    String getUserLogin();

}
