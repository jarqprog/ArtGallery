module domain.person {
    exports com.jarqprog.domainperson.contact;
    exports com.jarqprog.domainperson.roleuser;
    exports com.jarqprog.domainperson.user;
    exports com.jarqprog.domainperson.readuser;
    exports com.jarqprog.domainperson.registration;
    exports com.jarqprog.domainperson.login;
    exports com.jarqprog.domainperson;

    requires lombok;
    requires org.apache.commons.lang3;
    requires domain.shared;
}