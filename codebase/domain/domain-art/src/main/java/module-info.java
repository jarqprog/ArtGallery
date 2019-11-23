module domain.art {
    exports com.jarqprog.artdomain.model.author;
    exports com.jarqprog.artdomain.model.commentary;
    exports com.jarqprog.artdomain.model.picture;

    requires lombok;
    requires org.apache.commons.lang3;
    requires domain.common;
}