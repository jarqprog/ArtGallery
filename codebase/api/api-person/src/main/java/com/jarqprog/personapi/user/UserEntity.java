package com.jarqprog.personapi.user;

import com.jarqprog.commonapi.absmodel.DomainEntity;
import com.jarqprog.domainperson.contact.Contact;
import com.jarqprog.domainperson.user.User;
import com.jarqprog.domainperson.user.UserData;
import com.jarqprog.personapi.contact.ContactEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="users")
public class UserEntity extends DomainEntity implements User {

    public static UserEntity fromUser(@NonNull final User user) {
        return new UserEntity(user);
    }

    @NonNull
    @NotNull
    @NaturalId
    @Column(unique = true)
    private String login;

    @Setter
    private String password;

    @Setter
    private Boolean enabled = Boolean.TRUE;

    @Setter
    private Boolean tokenExpired = Boolean.FALSE;

    @NonNull
    @NotNull
    @OneToOne
    @JoinColumn(name = "contact_entity_id")
    private ContactEntity contactEntity;

    private UserEntity(final User user) {
        this(user.getId(),
            user.getVersion(),
            user.getLogin(),
            user.getPassword(),
            ContactEntity.fromContact(user.getContact()));
    }

    private UserEntity(final UserData userData, ContactEntity contactEntity) {
        this(userData.getId(), userData.getVersion(), userData.getLogin(), userData.getPassword(), contactEntity);
    }

    private UserEntity(long id, int version, String login,
                       String password, @NonNull ContactEntity contactEntity) {
        super(id, version);
        assert StringUtils.isNotBlank(login);
        assert StringUtils.isNotBlank(password);
        this.login = login;
        //todo
        this.password = password;
        this.enabled = true;
        this.tokenExpired = false;
        this.contactEntity = contactEntity;

    }

    @Override
    @Transient
    public long getContactId() {
        return getEntityId(contactEntity);
    }

    @Override
    @Transient
    public Contact getContact() {
        return contactEntity;
    }

}
