package com.fitness.centaur.converter;

import com.fitness.centaur.dto.UserDTO;
import com.fitness.centaur.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserType(user.getUserType());
        return userDTO;
    }

    public UserDTO convertToDTO(User user, UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserType(user.getUserType());
        return userDTO;
    }

    public void convertToEntity(UserDTO userDTO, User user) {
        if (Objects.nonNull(userDTO.getId())) {user.setId(userDTO.getId());}
        user.setName(userDTO.getName());
        user.setUserType(userDTO.getUserType());
    }
}
