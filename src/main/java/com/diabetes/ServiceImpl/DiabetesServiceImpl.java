package com.diabetes.ServiceImpl;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diabetes.Dao.DiabetesDao;
import com.diabetes.model.UserLogin;
import com.diabetes.service.DiabetesService;


@Service
@Transactional
public class DiabetesServiceImpl implements DiabetesService {

	@Autowired
	DiabetesDao diabetesDao;
	
	@Override
	public boolean findUser(String email) {
		return diabetesDao.findUser(email);
	}

	@Override
	public long createUser(UserLogin userLogin) {
		return diabetesDao.createUser(userLogin);
	}

	@Override
	public UserLogin updateUser(UserLogin userLogin) {
		return diabetesDao.updateUser(userLogin);
	}

	@Override
	public void deleteUserById(long id) {
		diabetesDao.deleteUserById(id);
		
	}

	@Override
	public List<UserLogin> getAllusers() {
		return diabetesDao.getAllusers();
	}

	@Override
	public UserLogin getUserByEmailId(String email) {
		return diabetesDao.getUserByEmailId(email);
	}

	@Override
	public UserLogin getUserById(long id) {
		return diabetesDao.getUserById(id);
	}
	
	@Override
	public void passReset(UserLogin userLogin, String pass, String email) {
		diabetesDao.passReset(userLogin, pass, email);
		
	}

	/*@Override
	public void updateSubscription(UserLogin userLogin, String membership, long id) {
		diabetesDao.updateSubscription(userLogin, membership, id);
	}*/

	@Override
	public List<UserLogin> lookupMembers(String name) {		
		return diabetesDao.lookupMembers(name);
	}

	@Override
	public void updateUserById(UserLogin userLogin, long id) {
		diabetesDao.updateUserById(userLogin, id);
		
	}

	/*@Override
	public List<UserLogin> getUserByName(String user) {
		return diabetesDao.getUserByName(user);
	}*/

	@Override
	public List<UserLogin> lookupCity(String cityOrZip) {		
		return diabetesDao.lookupCity(cityOrZip);
	}

	@Override
	public JSONObject jdbcDbConnect(String email) {
		return diabetesDao.jdbcDbConnect(email);
	}

}
