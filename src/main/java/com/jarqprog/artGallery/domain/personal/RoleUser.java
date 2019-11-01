package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.Model;

public interface RoleUser extends RoleUserData, Model {

    User getUser();
    SystemRole getRole();

}
