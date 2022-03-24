package com.fitness.centaur.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExercisePDTO implements Serializable {

    private Long id;
    private String exercise;
    private Integer seasons;
    private Integer repetitions;
    private Integer weight;
}
