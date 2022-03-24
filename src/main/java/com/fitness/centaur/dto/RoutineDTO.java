package com.fitness.centaur.dto;

import com.fitness.centaur.entity.RoutineType;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoutineDTO implements Serializable {

    private Long id;
    private String name;
    private RoutineType routineType;
    private List<ExercisePDTO> exercises = new ArrayList<>();
}
