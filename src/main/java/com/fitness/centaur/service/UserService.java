package com.fitness.centaur.service;

import com.fitness.centaur.converter.UserConverter;
import com.fitness.centaur.dto.UserDTO;
import com.fitness.centaur.entity.User;
import com.fitness.centaur.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDTO> allUsersDTO = allUsers.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    return userConverter.convertToDTO(user, userDTO);
                }).collect(Collectors.toList());
        return allUsersDTO;
    }

    public UserDTO editUser(Long idUser, UserDTO userDTO) {
        User actualUser = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userConverter.convertToEntity(userDTO, actualUser);
        userRepository.save(actualUser);
        UserDTO actualUserDTO = userConverter.convert(actualUser);
        return actualUserDTO;
    }

    public void deleteUser(Long idUser) {
        userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(idUser);
    }
}
