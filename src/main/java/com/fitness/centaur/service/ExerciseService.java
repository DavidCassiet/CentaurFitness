package com.fitness.centaur.service;

import com.fitness.centaur.converter.ExerciseConverter;
import com.fitness.centaur.dto.ExerciseDTO;
import com.fitness.centaur.entity.Exercise;
import com.fitness.centaur.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService {

    private ExerciseRepository exerciseRepository;
    private ExerciseConverter exerciseConverter;

    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    public List<ExerciseDTO> getAllExercises() {
        List<Exercise> allExercises = exerciseRepository.findAll();
        List<ExerciseDTO> allExercisesDTO = allExercises.stream()
                .map(exercise -> {
                    ExerciseDTO exerciseDTO = new ExerciseDTO();
                    return exerciseConverter.convertToDTO(exercise, exerciseDTO);
                }).collect(Collectors.toList());
        return allExercisesDTO;
    }

    public ExerciseDTO editExercise(Long idExercise, ExerciseDTO exerciseDTO) {
        Exercise actualExercise = exerciseRepository.findById(idExercise)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found"));
        exerciseConverter.convertToEntity(exerciseDTO, actualExercise);
        exerciseRepository.save(actualExercise);
        ExerciseDTO actualExerciseDTO = exerciseConverter.convert(actualExercise);
        return actualExerciseDTO;
    }

    public void deleteExercise(Long idExercise) {
        exerciseRepository.findById(idExercise)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found"));
        exerciseRepository.deleteById(idExercise);
    }
}
