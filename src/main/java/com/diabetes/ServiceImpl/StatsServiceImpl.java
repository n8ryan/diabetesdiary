package com.diabetes.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diabetes.Dao.StatsDao;
import com.diabetes.model.Exercise;
import com.diabetes.model.Glucose;
import com.diabetes.service.StatsService;

@Service
@Transactional
public class StatsServiceImpl implements StatsService {

	@Autowired
	StatsDao statsDao;

	@Override
	public long createExercise(Exercise exercise) {
		return statsDao.createExercise(exercise);
	}

	@Override
	public Exercise updateExercise(Exercise exercise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exercise> getAllExercise() {
		return statsDao.getAllExercise();
	}

	@Override
	public void deleteExerciseById(long id) {
		statsDao.deleteExerciseById(id);
		
	}

	@Override
	public long createGlucose(Glucose glucose) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Glucose updateGlucose(Glucose calories) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Glucose> getAllGlucose() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGlucoseById(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}

