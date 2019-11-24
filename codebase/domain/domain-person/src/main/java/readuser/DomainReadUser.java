package readuser;

import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(fluent = true)
@Builder
public class DomainReadUser implements ReadUser {

    @Builder(builderMethodName = "createWith")
    public static ReadUser buildWithData(@NonNull User user, @NonNull Set<SystemRole> roles) {
        return new DomainReadUser(user, user.getContact(), roles);
    }

    @NonNull
    private final User user;

    private final Contact contact;

    @NonNull
    private final Set<SystemRole> roles;

}
