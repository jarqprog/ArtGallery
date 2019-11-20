module domain.person {
    exports com.jarqprog.domainperson.model.contact;
    exports com.jarqprog.domainperson.model.user_role;
    exports com.jarqprog.domainperson.model;
    exports com.jarqprog.domainperson.model.user;

    requires lombok;
    requires org.apache.commons.lang3;
    requires domain.common;
}