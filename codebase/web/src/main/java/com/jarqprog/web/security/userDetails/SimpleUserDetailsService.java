package com.jarqprog.web.security.userDetails;


import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.web.service.DummyUserService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SimpleUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserDetailsService.class);

    @NonNull private final DummyUserService userService;

    @Autowired
    public SimpleUserDetailsService(@NonNull DummyUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("checking user with name: " + username);
        final var dummyUser = userService.getDummyUser();
        //userRepository.findUserByLogin(username).orElseThrow(
//                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        final var user = DomainUser.fromUser(dummyUser);
        return SimpleUserDetails.fromUser(user);
    }
}
