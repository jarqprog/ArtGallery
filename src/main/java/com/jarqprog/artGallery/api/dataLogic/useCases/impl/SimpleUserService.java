package com.jarqprog.artGallery.api.dataLogic.useCases.impl;

import com.jarqprog.artGallery.domain.dto.lightDto.UserDTOLight;
import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.repositories.CommentaryRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.PictureRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.UserRepository;
import com.jarqprog.artGallery.api.dataLogic.useCases.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class SimpleUserService implements UserService {

    @NonNull private final UserRepository userRepository;
    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final PasswordEncoder passwordEncoder;

    @Autowired
    public SimpleUserService(@NonNull UserRepository userRepository,
                             @NonNull PictureRepository pictureRepository,
                             @NonNull CommentaryRepository commentaryRepository,
                             @NonNull DtoConverter dtoConverter,
                             @NonNull PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToDto(p, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(long id) {
        User User = findById(id);
        return dtoConverter.convertEntityToDto(User, UserDTO.class);
    }

    @Override
    public UserDTO findUserByLogin(String login) {
        User User = findByLogin(login);
        return dtoConverter.convertEntityToDto(User, UserDTO.class);
    }

    @Override
    public UserDTO addUser(@NonNull UserDTOLight userDTO) {
        preventCreatingExistingUser(userDTO.getId());
        User user = dtoConverter.convertDtoToEntity(userDTO, User.class);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        User saved = userRepository.save(user);
        return dtoConverter.convertEntityToDto(saved, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(long id, @NonNull UserDTOLight userDTO) {
        validateUserExists(id);
        userDTO.setId(id);
        User updated = dtoConverter.convertDtoToEntity(userDTO, User.class);
        User saved = userRepository.save(updated);
        return dtoConverter.convertEntityToDto(saved, UserDTO.class);
    }

    @Override
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
