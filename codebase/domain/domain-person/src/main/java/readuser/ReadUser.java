package readuser;

import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.ContactData;
import com.jarqprog.domainperson.model.user.UserData;

import java.util.Set;

public interface ReadUser {

    UserData user();
    ContactData contact();
    Set<SystemRole> roles();
}
