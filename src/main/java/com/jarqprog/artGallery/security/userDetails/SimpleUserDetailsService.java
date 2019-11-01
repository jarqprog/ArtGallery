package com.jarqprog.artGallery.security.userDetails;

import com.jarqprog.artGallery.api.domains.personal.user.UserEntity;
import com.jarqprog.artGallery.api.domains.personal.user.UserRepository;

import com.jarqprog.artGallery.api.domains.personal.roleUser.RoleUserRepository;
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

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final RoleUserRepository roleUserRepository;

    @Autowired
    public SimpleUserDetailsService(@NonNull UserRepository userRepository,
                                    @NonNull RoleUserRepository roleUserRepository) {
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("checking user with name: " + username);

        UserEntity user = userRepository.findUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        return new SimpleUserDetails(user, roleUserRepository);
    }
}
