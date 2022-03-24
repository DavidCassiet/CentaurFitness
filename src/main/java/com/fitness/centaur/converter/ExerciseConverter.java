package com.fitness.centaur.converter;

import com.fitness.centaur.dto.ExerciseDTO;
import com.fitness.centaur.entity.Exercise;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExerciseConverter implements Converter<Exercise, ExerciseDTO> {

    @Override
    public ExerciseDTO convert(Exercise exercise) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setName(exercise.getName());
        return exerciseDTO;
    }

    public ExerciseDTO convertToDTO(Exercise exercise, ExerciseDTO exerciseDTO) {
        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setName(exercise.getName());
        return exerciseDTO;
    }

    public void convertToEntity(ExerciseDTO exerciseDTO, Exercise exercise) {
        if (Objects.nonNull(exerciseDTO.getId())) {exercise.setId(exerciseDTO.getId());}
        exercise.setName(exerciseDTO.getName());
    }
}
