package com.jarqprog.artGallery.service.user;

import com.jarqprog.artGallery.domain.User;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SimpleUserService implements UserService {

    private final PictureRepository pictureRepository;
    private final EntityMetadataService entityMetadataService;
    private final UserRepository userRepository;

    @Autowired
    public SimpleUserService(PictureRepository pictureRepository,
                             EntityMetadataService entityMetadataService,
                             UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.entityMetadataService = entityMetadataService;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public <P extends User> P save(P user) {
        P created = userRepository.save(user);
        entityMetadataService.createMetadata(created);  // todo maybe use aspect? jarq
        return created;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        boolean isRemoved = false;
        try {
            log.info("Removing user with id: " + id);
            User user = findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
            entityMetadataService.markDiscontinued(user);
            userRepository.delete(user);
            isRemoved = true;
            log.info("User removed");
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
        }
        return isRemoved;
    }
}
