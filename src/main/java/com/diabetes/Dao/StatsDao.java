package com.diabetes.Dao;

import java.util.List;

import com.diabetes.model.Exercise;
import com.diabetes.model.Glucose;

public interface StatsDao {

	public long createExercise(Exercise exercise);
	public List<Exercise> getAllExercise();
	public void deleteExerciseById(long id);
	public long createGlucose(Glucose glucose);
	public List<Glucose> getAllGlucose();
	public void deleteGlucoseById(long id);

}
