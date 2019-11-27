package com.jarqprog.personapi.readuser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.contact.ContactDTO;
import com.jarqprog.domainperson.user.UserDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Accessors(fluent = true)
@ToString
class ApiReadUserDTOImpl implements ApiReadUserDTO {

    @Builder(builderMethodName = "createWith")
    public static ApiReadUserDTO buildWithData(@NonNull UserDTO user, ContactDTO contact,
                                               @NonNull Set<SystemRole> roles) {
        return new ApiReadUserDTOImpl(user, contact, roles);
    }

    private UserDTO user;
    private ContactDTO contact;
    private Set<SystemRole> roles;
}
