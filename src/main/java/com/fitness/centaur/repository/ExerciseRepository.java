package com.fitness.centaur.repository;

import com.fitness.centaur.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findByName(String name);
}