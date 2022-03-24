package com.fitness.centaur.repository;

import com.fitness.centaur.entity.ExerciseFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseFRepository extends JpaRepository<ExerciseFeatures, Long> {
}
