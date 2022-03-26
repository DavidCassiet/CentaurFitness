package com.fitness.centaur.controller;

import com.fitness.centaur.dto.RoutineDTO;
import com.fitness.centaur.service.RoutineService;
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
@RequestMapping(value = "/routine")
public class RoutineController {

    private RoutineService routineService;

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createRoutine(@PathVariable("idUser") Long idUser,
                                           @RequestBody @Valid RoutineDTO routineDTO) {
        routineService.createRoutine(idUser, routineDTO);
        return new ResponseEntity<>("Routine created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRoutines() {
        return new ResponseEntity<>(routineService.getAllRoutines(), HttpStatus.OK);
    }

    @PutMapping("/{idRoutine}")
    public ResponseEntity<?> editRoutine(@PathVariable("idRoutine") Long idRoutine,
                                         @RequestBody @Valid RoutineDTO routineDTO) {
        return new ResponseEntity<>(routineService.editRoutine(idRoutine, routineDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idRoutine}")
    public ResponseEntity<?> deleteRoutine(@PathVariable("idRoutine") Long idRoutine) {
        routineService.deleteRoutine(idRoutine);
        return new ResponseEntity<>("Routine deleted successfully",HttpStatus.ACCEPTED);
    }
}
