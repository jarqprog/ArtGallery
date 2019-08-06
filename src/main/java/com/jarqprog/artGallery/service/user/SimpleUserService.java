package com.jarqprog.artGallery.service.user;

import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;


@Slf4j
@Service
public class SimpleUserService implements UserService {

    @Autowired private PictureRepository pictureRepository;
    @Autowired private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserService.class);
}
