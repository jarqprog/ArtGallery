module domain.art {
    exports com.jarqprog.artdomain.author;
    exports com.jarqprog.artdomain.commentary;
    exports com.jarqprog.artdomain.picture;

    requires lombok;
    requires org.apache.commons.lang3;
    requires domain.common;
}