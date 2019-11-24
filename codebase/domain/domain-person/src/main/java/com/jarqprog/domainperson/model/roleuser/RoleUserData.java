package com.jarqprog.domainperson.model.roleuser;


import com.jarqprog.commondomain.absmodel.Identity;
import com.jarqprog.domainperson.model.SystemRole;

import java.io.Serializable;

public interface RoleUserData extends Identity, Serializable {

    SystemRole getRole();
    String getUserLogin();

}
