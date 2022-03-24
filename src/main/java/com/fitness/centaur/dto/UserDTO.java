package com.fitness.centaur.dto;

import com.fitness.centaur.entity.UserType;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private UserType userType;
}
