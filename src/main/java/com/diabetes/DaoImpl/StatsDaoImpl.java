package com.diabetes.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diabetes.Dao.StatsDao;
import com.diabetes.Repository.HibernateUtil;
import com.diabetes.model.Exercise;
import com.diabetes.model.Glucose;
import com.diabetes.model.UserLogin;

@Repository
public class StatsDaoImpl implements StatsDao{
	
	
	
	@Autowired
    private HibernateUtil hibernateUtil;

	@Override
	public long createExercise(Exercise exercise) {
		return (Long) hibernateUtil.create(exercise);
	}

	@Override
	public List<Exercise> getAllExercise() {
		return hibernateUtil.fetchAll(Exercise.class);
	}

	@Override
	public void deleteExerciseById(long id) {
		Exercise exercise = new Exercise();
		exercise.setId(id);
        hibernateUtil.delete(exercise);		
	}

	@Override
	public long createGlucose(Glucose glucose) {
		return (long) hibernateUtil.create(glucose);
	}

	@Override
	public List<Glucose> getAllGlucose() {
		return hibernateUtil.fetchAll(Glucose.class);
	}

	@Override
	public void deleteGlucoseById(long id) {
		Glucose glucose = new Glucose();
		glucose.setId(id);
        hibernateUtil.delete(glucose);		
	}

}
