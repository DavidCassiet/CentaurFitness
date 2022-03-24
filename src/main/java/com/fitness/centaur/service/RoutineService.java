package com.fitness.centaur.service;

import com.fitness.centaur.converter.ExerciseFConverter;
import com.fitness.centaur.converter.RoutineConverter;
import com.fitness.centaur.dto.ExercisePDTO;
import com.fitness.centaur.dto.RoutineDTO;
import com.fitness.centaur.entity.Exercise;
import com.fitness.centaur.entity.ExerciseFeatures;
import com.fitness.centaur.entity.Routine;
import com.fitness.centaur.entity.User;
import com.fitness.centaur.repository.ExerciseFRepository;
import com.fitness.centaur.repository.ExerciseRepository;
import com.fitness.centaur.repository.RoutineRepository;
import com.fitness.centaur.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoutineService {

    private RoutineRepository routineRepository;
    private RoutineConverter routineConverter;
    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;
    private ExerciseFConverter exerciseFConverter;
    private ExerciseFRepository exerciseFRepository;

    public void createRoutine(Long idUser, RoutineDTO routineDTO) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Routine routine = new Routine();
        routineConverter.convertToEntity(routineDTO, routine);
        routine.setUser(user);
        if (!routineDTO.getExercises().isEmpty()) {
            for (ExercisePDTO exercise : routineDTO.getExercises()) {
                try {
                    Exercise actualExercise = exerciseRepository.findByName(exercise.getExercise());
                    ExerciseFeatures exerciseFeatures = exerciseFConverter.convertToEF(actualExercise);
                    exerciseFConverter.convertToEF(exercise, exerciseFeatures);
                    routine.addExercise(exerciseFeatures);
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }
        }
        routineRepository.save(routine);
    }

    public List<RoutineDTO> getAllRoutines() {
        List<Routine> allRoutines = routineRepository.findAll();
        List<RoutineDTO> allRoutinesDTO = allRoutines.stream()
                .map(routine -> {
                    RoutineDTO routineDTO = new RoutineDTO();
                    routineConverter.convertToDTO(routine, routineDTO);
                    List<ExercisePDTO> exercisePDTOList = routine.getExercises().stream()
                            .map(exerciseFeatures -> {
                                return exerciseFConverter.convertToEPDTO(exerciseFeatures);
                            }).collect(Collectors.toList());
                    routineDTO.setExercises(exercisePDTOList);
                    return routineDTO;
                }).collect(Collectors.toList());
        return allRoutinesDTO;
    }

    public RoutineDTO editRoutine(Long idRoutine, RoutineDTO routineDTO) {
        Routine actualRoutine = routineRepository.findById(idRoutine)
                .orElseThrow(() -> new EntityNotFoundException("Routine not found"));
        routineConverter.convertToEntity(routineDTO, actualRoutine);
        List<ExerciseFeatures> actualRoutineExercises = actualRoutine.getExercises();
        List<ExerciseFeatures> routineDTOExercises = routineDTO.getExercises().stream()
                .map(exercisePDTO -> {
                    if (Objects.nonNull(exercisePDTO.getId())) {
                        ExerciseFeatures actualExercise = exerciseFRepository.findById(exercisePDTO.getId()).get();
                        exerciseFConverter.convertToEF(exercisePDTO, actualExercise);
                        exerciseFRepository.save(actualExercise);
                        return actualExercise;
                    } else {
                        ExerciseFeatures exerciseFeatures = null;
                        try {
                            Exercise actualExercise = exerciseRepository.findByName(exercisePDTO.getExercise());
                            exerciseFeatures = exerciseFConverter.convertToEF(actualExercise);
                            exerciseFConverter.convertToEF(exercisePDTO, exerciseFeatures);
                            actualRoutine.addExercise(exerciseFeatures);
                            exerciseFRepository.save(exerciseFeatures);
                            return exerciseFeatures;
                        } catch (Exception e) {
                            System.out.println("Exception: " + e);
                        }
                        return exerciseFeatures;
                    }
                }).collect(Collectors.toList());
        actualRoutineExercises.removeIf(e -> !routineDTOExercises.contains(e));
        routineRepository.save(actualRoutine);
        RoutineDTO routineDTO1 = routineConverter.convert(actualRoutine);
        List<ExercisePDTO> exercisePDTOList = actualRoutine.getExercises().stream()
                .map(exerciseFeatures -> {
                    return exerciseFConverter.convertToEPDTO(exerciseFeatures);
                }).collect(Collectors.toList());
        routineDTO1.setExercises(exercisePDTOList);
        return routineDTO1;
    }

    public void deleteRoutine(Long idRoutine) {
        routineRepository.findById(idRoutine)
                .orElseThrow(() -> new EntityNotFoundException("Routine not found"));
        routineRepository.deleteById(idRoutine);
    }
}
