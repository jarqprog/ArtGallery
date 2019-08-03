package com.jarqprog.artGallery.service.user;

import com.jarqprog.artGallery.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAllUsers();
    <P extends User> P save(P user);
    Optional<User> findById(Long id);
    boolean remove(Long id);
}
