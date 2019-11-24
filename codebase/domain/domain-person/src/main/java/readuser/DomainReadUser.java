package readuser;

import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.roleuser.RoleUser;
import com.jarqprog.domainperson.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(fluent = true)
@Builder
public class DomainReadUser implements ReadUser {

    @Builder(builderMethodName = "createWith")
    public static DomainReadUserBuilder buildWithData(@NonNull User user) {
        return new DomainReadUserBuilder().user(user);
    }

    @Builder.Default
    @NonNull
    private final User user;

    private final Contact contact;

    private final RoleUser roleUser;

}
