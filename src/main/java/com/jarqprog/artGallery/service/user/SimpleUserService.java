package com.jarqprog.artGallery.service.user;

import com.jarqprog.artGallery.domain.Commentary;
import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.domain.User;
import com.jarqprog.artGallery.dto.UserDTO;
import com.jarqprog.artGallery.exception.persistenceException.ResourceAlreadyExists;
import com.jarqprog.artGallery.exception.persistenceException.ResourceNotFoundException;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.CommentaryRepository;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class SimpleUserService implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PictureRepository pictureRepository;

    @Autowired private CommentaryRepository commentaryRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoEntityConverter.convertEntityToDto(p, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(long id) {
        User User = findById(id);
        return dtoEntityConverter.convertEntityToDto(User, UserDTO.class);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        preventCreatingExistingUser(userDTO.getId());
        User user = dtoEntityConverter.convertDtoToEntity(userDTO, User.class);
        User saved = userRepository.save(user);
        return dtoEntityConverter.convertEntityToDto(saved, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
        validateUserExists(id);
        userDTO.setId(id);
        User updated = dtoEntityConverter.convertDtoToEntity(userDTO, User.class);
        User saved = userRepository.save(updated);
        return dtoEntityConverter.convertEntityToDto(saved, UserDTO.class);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        validateUserExists(id);

        Set<Commentary> commentaries = commentaryRepository.findAllCommentaryByUserId(id);
        commentaries.forEach(c -> c.setUser(null));

        Set<Picture> pictures = pictureRepository.findAllPicturesByUserId(id);
        pictures.forEach(p -> p.setUser(null));

        commentaryRepository.saveAll(commentaries);
        pictureRepository.saveAll(pictures);
        userRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, id));
    }

    private void validateUserExists(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(User.class, userId);
        }
    }

    private void preventCreatingExistingUser(long userId) {
        if (userRepository.existsById(userId)) {
            throw new ResourceAlreadyExists(User.class, userId);
        }
    }
}
