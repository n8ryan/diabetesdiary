package com.diabetes.service;

import java.util.List;

import com.diabetes.model.Exercise;
import com.diabetes.model.Glucose;

public interface StatsService {
	
	public long createExercise(Exercise exercise);
	public Exercise updateExercise(Exercise exercise);
	public List<Exercise> getAllExercise();
	public void deleteExerciseById(long id);
	public long createGlucose(Glucose glucose);
	public Glucose updateGlucose(Glucose calories);
	public List<Glucose> getAllGlucose();
	public void deleteGlucoseById(long id);

}
