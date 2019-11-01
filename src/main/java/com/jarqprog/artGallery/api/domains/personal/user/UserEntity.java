package com.jarqprog.artGallery.api.domains.personal.user;

import com.jarqprog.artGallery.api.domains.personal.contact.ContactEntity;
import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.domain.personal.User;
import com.jarqprog.artGallery.domain.personal.UserData;
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

    @OneToOne
    @JoinColumn(name = "contact_entity_id")
    private ContactEntity contactEntity;


    public UserEntity(@NonNull UserEntity user) {
        this.contactEntity = user.getContactEntity();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.tokenExpired = user.getTokenExpired();
    }

    private UserEntity(final User user) {

        this(user.getId(),
            user.getVersion(),
            user.getLogin(),
            user.getPassword(),
            user.hasContact() ? ContactEntity.fromContact(user.getContact()) : null);
    }

    private UserEntity(final UserData userData, ContactEntity contactEntity) {
        this(userData.getId(), userData.getVersion(), userData.getLogin(), userData.getPassword(), contactEntity);
    }

    private UserEntity(long id, int version, String login,
                       String password, ContactEntity contactEntity) {
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

    @Override
    @Transient
    public boolean hasContact() {
        return contactEntity != null;
    }
}
