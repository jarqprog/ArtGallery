package com.jarqprog.artGallery.springSecurity.userDetails;

import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.springData.repository.UserRepository;

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

    private final UserRepository userRepository;

    @Autowired
    public SimpleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("&&&&&&&&&&&&&Checking user with name: " + username);

        User user = userRepository.findUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        return new SimpleUserDetails(user);
    }
}
