package readuser;

import com.jarqprog.commondomain.absmodel.DTO;
import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.ContactDTO;
import com.jarqprog.domainperson.model.user.UserDTO;

import java.util.Set;

public interface ReadUserDTO extends DTO {

    UserDTO user();
    ContactDTO contact();
    Set<SystemRole> roles();
}
