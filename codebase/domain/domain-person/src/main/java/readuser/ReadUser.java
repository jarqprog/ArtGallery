package readuser;

import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.user.User;

import java.util.Optional;
import java.util.Set;

public interface ReadUser {

    User user();
    Optional<Contact> contact();
    Set<SystemRole> roles();
}
