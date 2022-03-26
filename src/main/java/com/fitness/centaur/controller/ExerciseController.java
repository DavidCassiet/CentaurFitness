package com.fitness.centaur.controller;

import com.fitness.centaur.dto.ExerciseDTO;
import com.fitness.centaur.entity.Exercise;
import com.fitness.centaur.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody @Valid Exercise exercise) {
        exerciseService.createExercise(exercise);
        return new ResponseEntity<>("Exercise created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllExercises() {
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }

    @PutMapping("/{idExercise}")
    public ResponseEntity<?> editUser(@PathVariable("idExercise") Long idExercise,
                                      @RequestBody @Valid ExerciseDTO exerciseDTO) {
        return new ResponseEntity<>(exerciseService.editExercise(idExercise, exerciseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idExercise}")
    public ResponseEntity<?> deleteUser(@PathVariable("idExercise") Long idExercise) {
        exerciseService.deleteExercise(idExercise);
        return new ResponseEntity<>("Exercise deleted successfully",HttpStatus.ACCEPTED);
    }
}
