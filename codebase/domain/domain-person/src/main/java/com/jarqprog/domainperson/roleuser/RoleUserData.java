package com.jarqprog.domainperson.roleuser;


import com.jarqprog.commondomain.Identity;
import com.jarqprog.domainperson.SystemRole;

import java.io.Serializable;

public interface RoleUserData extends Identity, Serializable {

    SystemRole getRole();
    String getUserLogin();

}
