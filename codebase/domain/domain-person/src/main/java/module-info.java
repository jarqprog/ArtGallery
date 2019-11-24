module domain.person {
    exports com.jarqprog.domainperson.model.contact;
    exports com.jarqprog.domainperson.model.roleuser;
    exports com.jarqprog.domainperson.model;
    exports com.jarqprog.domainperson.model.user;
    exports readuser;
    exports com.jarqprog.domainperson.usecase.registration;
    exports com.jarqprog.domainperson.usecase.login;

    requires lombok;
    requires org.apache.commons.lang3;
    requires domain.common;
}