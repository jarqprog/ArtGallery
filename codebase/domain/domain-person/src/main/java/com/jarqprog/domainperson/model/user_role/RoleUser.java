package com.jarqprog.domainperson.model.user_role;


import com.jarqprog.commondomain.absmodel.Model;
import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.user.User;

public interface RoleUser extends RoleUserData, Model {

    User getUser();
    SystemRole getRole();

}
