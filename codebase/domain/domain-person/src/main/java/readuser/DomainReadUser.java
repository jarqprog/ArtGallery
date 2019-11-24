package readuser;

import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.ContactData;
import com.jarqprog.domainperson.model.user.UserData;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(fluent = true)
@Builder
@ToString
public class DomainReadUser implements ReadUser {

    @Builder(builderMethodName = "createWith")
    public static ReadUser buildWithData(@NonNull UserData user, ContactData contact,
                                         @NonNull Set<SystemRole> roles) {
        return new DomainReadUser(user, contact, roles);
    }

    @NonNull
    private final UserData user;

    private final ContactData contact;

    @NonNull
    private final Set<SystemRole> roles;
}
