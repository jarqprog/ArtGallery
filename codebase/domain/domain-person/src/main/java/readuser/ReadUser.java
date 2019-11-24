package readuser;

import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.user.User;
import com.jarqprog.domainperson.model.roleuser.RoleUser;

import java.util.Optional;

public interface ReadUser {

    User user();
    Optional<Contact> contact();
    Optional<RoleUser> roleUser();
}
