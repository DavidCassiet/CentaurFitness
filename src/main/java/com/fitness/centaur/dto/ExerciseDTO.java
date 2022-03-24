package com.fitness.centaur.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExerciseDTO implements Serializable {

    private Long id;
    private String name;
}
