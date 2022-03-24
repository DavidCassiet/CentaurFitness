package com.fitness.centaur.converter;

import com.fitness.centaur.dto.ExercisePDTO;
import com.fitness.centaur.entity.Exercise;
import com.fitness.centaur.entity.ExerciseFeatures;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFConverter implements Converter<ExerciseFeatures, ExerciseFeatures> {

    @Override
    public ExerciseFeatures convert(ExerciseFeatures exercise) {
        ExerciseFeatures exerciseFeatures = new ExerciseFeatures();
        exerciseFeatures.setSeasons(exercise.getSeasons());
        exerciseFeatures.setRepetitions(exercise.getRepetitions());
        exerciseFeatures.setWeight(exercise.getWeight());
        return exerciseFeatures;
    }

    public ExerciseFeatures convertToEntity(Exercise exercise) {
        ExerciseFeatures exerciseFeatures = new ExerciseFeatures();
        exerciseFeatures.setName(exercise.getName());
        exerciseFeatures.setDescription(exercise.getDescription());
        return exerciseFeatures;
    }
    public ExercisePDTO convertToEPDTO(ExerciseFeatures exerciseFeatures) {
        ExercisePDTO exercisePDTO = new ExercisePDTO();
        exercisePDTO.setExercise(exerciseFeatures.getName());
        exercisePDTO.setSeasons(exerciseFeatures.getSeasons());
        exercisePDTO.setRepetitions(exerciseFeatures.getRepetitions());
        exercisePDTO.setWeight(exerciseFeatures.getWeight());
        return exercisePDTO;
    }
}
