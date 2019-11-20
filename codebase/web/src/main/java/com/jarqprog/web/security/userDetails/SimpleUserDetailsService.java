package com.jarqprog.web.security.userDetails;


import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.personapi.domains.contact.ContactEntity;
import com.jarqprog.personapi.domains.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SimpleUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserDetailsService.class);

//    @NonNull
//    private final UserRepository userRepository;
//
//    @NonNull
//    private final RoleUserRepository roleUserRepository;

//    @Autowired
//    public SimpleUserDetailsService(@NonNull UserRepository userRepository,
//                                    @NonNull RoleUserRepository roleUserRepository) {
//        this.userRepository = userRepository;
//        this.roleUserRepository = roleUserRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("checking user with name: " + username);
        final var contact = ContactEntity.fromContact(DomainContact.createWith()
                .email("username@gmail.com")
                .firstName(username)
                .build());
        final var user = UserEntity.fromUser(DomainUser.createWith().login(username).password(username).build());   //userRepository.findUserByLogin(username).orElseThrow(
//                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        return new SimpleUserDetails(user);
    }
}
