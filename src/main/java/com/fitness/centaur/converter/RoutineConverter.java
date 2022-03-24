package com.fitness.centaur.converter;

import com.fitness.centaur.dto.RoutineDTO;
import com.fitness.centaur.entity.Routine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoutineConverter implements Converter<Routine, RoutineDTO> {

    @Override
    public RoutineDTO convert(Routine routine) {
        RoutineDTO routineDTO = new RoutineDTO();
        routineDTO.setId(routine.getId());
        routineDTO.setName(routine.getName());
        routineDTO.setRoutineType(routine.getRoutineType());
        /*routineDTO.setExercises(routine.getFeatures());*/

        return routineDTO;
    }

    public RoutineDTO convertToDTO(Routine routine, RoutineDTO routineDTO) {
        routineDTO.setId(routine.getId());
        routineDTO.setName(routine.getName());
        routineDTO.setRoutineType(routine.getRoutineType());
        return routineDTO;
    }

    public Routine convertToEntity(RoutineDTO routineDTO, Routine routine) {
        if (Objects.nonNull(routineDTO.getId())) {
            routine.setId(routineDTO.getId());
        }
        routine.setName(routineDTO.getName());
        routine.setRoutineType(routineDTO.getRoutineType());
        return routine;
    }
}
