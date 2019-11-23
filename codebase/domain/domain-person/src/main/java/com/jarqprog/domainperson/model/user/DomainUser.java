package com.jarqprog.domainperson.model.user;

import com.jarqprog.commondomain.absmodel.DomainModel;
import com.jarqprog.domainperson.model.contact.Contact;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class DomainUser extends DomainModel implements User {

    public static DomainUser fromUser(@NonNull final User user) {
        return new DomainUser(user);
    }

    @Builder(builderMethodName = "createWith")
    public static DomainUser buildWithData(long id, int version,
                                           @NonNull final String login,
                                           @NonNull final String password,
                                           @NonNull final Contact contact) {
        return new DomainUser(id, version, login, password, contact);
    }

    @NonNull
    private final String login;

    private final String password;

    @NonNull
    private final Contact contact;

    private DomainUser(long id, final User user) {
        this(id, user.getVersion(), user.getLogin(), user.getPassword(), user.getContact());
    }

    private DomainUser(final User user) {
        this(user.getId(), user);
    }

    private DomainUser(long id, int version, String login,
                       String password, Contact contact) {
        super(id, version);
        assert StringUtils.isNotBlank(login);
        assert StringUtils.isNotBlank(password);
        this.login = login;
        this.contact = contact;
        this.password = password;
    }

    @Override
    public long getContactId() {
        return getModelId(contact);
    }

}
