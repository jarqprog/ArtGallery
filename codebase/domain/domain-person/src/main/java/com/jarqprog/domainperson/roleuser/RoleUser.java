package com.jarqprog.domainperson.roleuser;


import com.jarqprog.commondomain.absmodel.Model;
import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.user.User;

public interface RoleUser extends RoleUserData, Model {

    User getUser();
    SystemRole getRole();

}
