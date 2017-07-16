package com.diabetes.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.diabetes.model.Exercise;
import com.diabetes.model.Glucose;
import com.diabetes.model.UserLogin;
import com.diabetes.service.DiabetesService;
//import com.diabetes.model.UserLogin;
//import com.diabetes.model.Diabetes;
import com.diabetes.service.StatsService;
import com.diabetes.diabetesUtils.States;

@Controller
public class StatsController {

	@Autowired
	StatsService statsService;
	
	@Autowired
	private DiabetesService diabeteservice;

	@Autowired
	DataSource dataSource;

	
	@ModelAttribute("exercise")
	public List<Exercise> exercise() {
		return statsService.getAllExercise();
	}

	@ModelAttribute("glucose")
	public List<Glucose> calories() {
		return statsService.getAllGlucose();
	}

/*		ExerciseService.deleteExerciseById(id);
		model.addAttribute("success", "delete success");
		model.addAttribute("users", exerciseService.getAllExercises());
		

		return "about";
	}*/
	
	/*@RequestMapping(value = "/exercise", method = RequestMethod.GET)
	public String exercise(Model model) {
		model.addAttribute("exercise", new Exercise());

		return "exercise";
	}*/
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats(Model model) {
		model.addAttribute("exercises", new Exercise());
		//model.addAttribute("glucose", new Glucose());

		return "stats";
	}

	@RequestMapping(value = "/stats", method = RequestMethod.POST)
    public String stats(@Valid @ModelAttribute("exercise")
    	Exercise exercise, BindingResult result, Model model) {

		if (result.hasErrors()) {
    		return "stats";
	    }
		return "stats";
	}
	
	@RequestMapping(value = "glucose", method = RequestMethod.POST)
    public String stats(@Valid @ModelAttribute("glucose")
    	Glucose glucose, BindingResult result, Model model) {
    
    	if (result.hasErrors()) {
    		return "stats";
    	}
    	return "stats";
    }
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Exercise> lookupCity() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		final String sql = "SELECT * FROM diabetes.Exercise";

		List<Exercise> allstats = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Exercise.class));
		return allstats;

	}
	
	
	public void getallstats(String d, String a) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//final String sql = "INSERT INTO diabetes.Exercise SET activity=?,  duration=?", d, a);
		jdbcTemplate.update("INSERT INTO diabetes.Exercise SET activity=?,  duration=?", d, a);
	}
	
    
/*     if(diabeteservice.findUser(userLogin.getEmail())== true){
    model.addAttribute("error", "A user exists with the supplied email "+userLogin.getEmail()+" <a href='forgotpassword'> Forgot Password click here </a>");	
            	  return "stats";  
      	}
       if(userLogin.getPassword().equals(userLogin.getPasswordConfirm())){
		 diabeteservice.createUser(userLogin);
		 
		 }else{
		 model.addAttribute("error", "These Passwords don't match ");
		 }
       
          return "redirect:/login";
    }
*/

/*	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Model model) {
		model.addAttribute("users", new Users());

		return "users";
	}
*/
	/*@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		model.addAttribute("msg", "this is test page");

		return "test";
	}*/

/*	@RequestMapping(value = "/testpost", method = RequestMethod.POST)
	public String test(@ModelAttribute("testpost") UserLogin user, ModelMap model) throws ParseException {
		model.addAttribute("msg", user.getEmail() + " " + user.getFirstName() + " Post success");
		model.addAttribute("users", exerciseService.getAllExercise());
		return "test";
	}
*/
/*	@RequestMapping(value = "/lookupcity", method = RequestMethod.POST)
	public String testlookup(@ModelAttribute("lookupcity") long lookupcity, ModelMap model) {
		model.addAttribute("message", lookupcity + " Lookup");
		model.addAttribute("users", getJoinTables(lookupcity));
		return "services";
	}

	public List<Map<String, Object>> getJoinTables(long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String join = "SELECT users.*, locations.*  FROM users JOIN locations" + " ON locations.zip = users.zip"
				+ " WHERE users.zip like '%" + id + "%'";
		return jdbcTemplate.queryForList(join);
	}
*/
	@RequestMapping(value = "exercise", method = RequestMethod.POST)
	public String exercise(@Valid @ModelAttribute("exercise") 
		Exercise exercise, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "exercise";
		}
		try {
			statsService.createExercise(exercise);
			model.addAttribute("success", "Exercise added");

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return "exercise";
		} catch (RuntimeException e) {
			model.addAttribute("error", "Exercise Not Added");

			e.printStackTrace();
			return "exercise";

		}
		return "about";
	}

/*	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserLogin> lookupCity(String cityOrZip) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		final String sql = "SELECT m.* FROM diabetes.users m WHERE m.city like '%" + cityOrZip + "%'" + " UNION "
				+ "SELECT m.* FROM diabetes.users m WHERE m.zip like '%" + cityOrZip + "%'";

		List<UserLogin> memberList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserLogin.class));
		return memberList;

	}
*/
}
