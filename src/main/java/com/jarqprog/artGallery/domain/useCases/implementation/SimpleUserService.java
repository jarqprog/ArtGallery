package com.jarqprog.artGallery.domain.useCases.implementation;

import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.exception.ResourceAlreadyExists;
import com.jarqprog.artGallery.domain.exception.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import com.jarqprog.artGallery.springData.repository.PictureRepository;
import com.jarqprog.artGallery.springData.repository.UserRepository;
import com.jarqprog.artGallery.domain.useCases.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired private PasswordEncoder passwordEncoder;


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
    public UserDTO findUserByLogin(String login) {
        User User = findByLogin(login);
        return dtoEntityConverter.convertEntityToDto(User, UserDTO.class);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        preventCreatingExistingUser(userDTO.getId());
        User user = dtoEntityConverter.convertDtoToEntity(userDTO, User.class);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
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

    private User findByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() -> new ResourceNotFoundException(User.class));
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
