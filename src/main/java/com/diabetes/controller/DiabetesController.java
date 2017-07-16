package com.diabetes.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.diabetes.model.UserLogin;
import com.diabetes.model.Diabetes;
import com.diabetes.service.SendMail;
import com.diabetes.service.DiabetesService;
import com.diabetes.diabetesUtils.States;
import com.diabetes.diabetesUtils.DiabetesUtils;

@Controller
@SessionAttributes("email")
public class DiabetesController {

	public DiabetesController() {
		System.out.println("in diabetesController servelet");
	}

	@Autowired
	private DiabetesService diabeteservice;

	@Autowired
	DataSource dataSource;
	
	SendMail sendMail = new SendMail();
	DiabetesUtils diabetesUtils = new DiabetesUtils();

	@RequestMapping(value = "/jdbcCrudes", method = RequestMethod.GET)
	public String services(Model model) {
		model.addAttribute("attribs", diabeteservice.jdbcDbConnect("kenn.juma@yahoo.com"));

		return "jdbcCrudes";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("registration", new UserLogin());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("registration")
    UserLogin userLogin, BindingResult result, Model model) {
    
    if (result.hasErrors()) {
	return "registration";
	   }
    if(diabeteservice.findUser(userLogin.getEmail())== true){
    model.addAttribute("error", "A user exists with the supplied email "+userLogin.getEmail()+" <a href='forgotpassword'> Forgot Password click here </a>");	
            	  return "registration";  
       }
    if(userLogin.getPassword().equals(userLogin.getPasswordConfirm())){
		 diabeteservice.createUser(userLogin);
		 
	   }else{
		 model.addAttribute("error", "These Passwords don't match ");
	   }
       
       return "redirect:/login";
    }



	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	
	public String charts(Model model) {
		model.addAttribute("charts", new UserLogin());

		return "charts";
	}

	@RequestMapping(value = "/charts", method = RequestMethod.POST)
    public String charts(@Valid @ModelAttribute("charts")
    UserLogin userLogin, BindingResult result, Model model) {
    
    if (result.hasErrors()) {
	return "charts";
	   }          return "redirect:/login";
    }
	
@RequestMapping(value = "/dataEntry", method = RequestMethod.GET)
	
	public String dataEntry(Model model) {
		model.addAttribute("dataEntry", new UserLogin());

		return "dataEntry";
	}

	@RequestMapping(value = "/dataEntry", method = RequestMethod.POST)
    public String dataEntry(@Valid @ModelAttribute("dataEntry")
    UserLogin userLogin, BindingResult result, Model model) {
    
    if (result.hasErrors()) {
	return "dataEntry";
	   }          return "redirect:/login";
    }

	@ModelAttribute("states")
	public List<States> populateStates() {
		return Arrays.asList(States.values());
	}

	@RequestMapping("email-lookup")
	public String handlepost(@ModelAttribute("email-lookup") UserLogin userLogin, BindingResult bindingResult,
			ModelMap model) throws ParseException {

		try {
			if (diabeteservice.findUser(userLogin.getEmail()) == true) {
				model.addAttribute("attribs", diabeteservice.getUserByEmailId(userLogin.getEmail()));
				// UserLogin userdetail=
				// diabeteservice.getUserByEmailId(userLogin.getEmail());
				// model.addAttribute("attribs", userdetail);
			} else {
				model.addAttribute("error", "User email does not exist in the Data base");
			}
		} catch (EmptyResultDataAccessException e) {

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occurred");
			e.printStackTrace();

		}

		return "services";

	}
	
	

	/*
	 * @RequestMapping("lookUpname") public String
	 * lookUpname(@ModelAttribute("lookUpname") UserLogin userLogin,
	 * BindingResult bindingResult, ModelMap model){
	 * 
	 * try { UserLogin userdetail= getUserByNane(userLogin.getLname());
	 * model.addAttribute("attribs", userdetail);
	 * 
	 * } catch(EmptyResultDataAccessException e){ model.addAttribute("error",
	 * "User "+userLogin.getLname()+" does noe exist in the Data base");
	 * 
	 * }catch(RuntimeException e) { model.addAttribute("error",
	 * "Unexpected error occured"+e); e.printStackTrace();
	 * 
	 * }
	 * 
	 * 
	 * return "services";
	 * 
	 * }
	 */

	@RequestMapping("lookUpByLastnameOrFirstname")
	public String lookUpname(@ModelAttribute("lookUpByLastnameOrFirstname") UserLogin userLogin, ModelMap model){
			//List<UserLogin> userdetail = lookupMembers(userLogin.getLname()) {

		try {
			
			model.addAttribute("users", lookupMembers(userLogin.getLastName()));

		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("error", "User " + userLogin.getLastName() + " does not exist in the Data base");
			model.addAttribute("users", lookupMembers(userLogin.getLastName()));
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occurred" + e);
			model.addAttribute("users", lookupMembers(userLogin.getLastName()));
			e.printStackTrace();

		}

		return "services";

	}

	@RequestMapping(value = "/logins", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("loginUser", new UserLogin());
		model.addAttribute("msg", "Login to explore the complete features!");
		return "logins";
	}

	@RequestMapping("bio")
	public String bio(ModelMap model, @RequestParam("id") long id) {

		try {
			UserLogin userdetail = diabeteservice.getUserById(id);
			model.addAttribute("attribs", userdetail);
			model.addAttribute("user", userdetail.getFirstName());
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occurred");
			e.printStackTrace();
		}

		return "bio";
	}

	/*@RequestMapping("LookupMembers")
	public String searchMembers(ModelMap model, @RequestParam("LookupMembers") String names,
			@RequestParam(value = "id", required = false) long id) {

		try {
			long membersId = id;
			List<UserLogin> memberList = diabeteservice.lookupMembers(names);
			if (diabeteservice.getUserByName(names).isEmpty()) {
				model.addAttribute("error", "There are no matches for this Look-up");
			} else
				model.addAttribute("success", memberList.size() + " members found with name " + names);
			model.addAttribute("users", memberList);
			model.addAttribute("user", diabeteservice.getUserById(membersId));

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occurred");
			e.printStackTrace();
		}

		return "members";
	}
*/
	/*@RequestMapping("lookupArtist")
	public String lookartist(ModelMap model, @RequestParam("lookupArtist") String names) {

		try {

			List<UserLogin> memberList = diabeteservice.getUserByName(names);
			if (diabeteservice.getUserByName(names).isEmpty()) {
				model.addAttribute("error", "There are no matches for this Look-up");
			} else
				model.addAttribute("success", memberList.size() + " members found with name " + names);
			model.addAttribute("users", memberList);

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occurred");
			e.printStackTrace();
		}

		return "services";
	}*/

	@RequestMapping("Lookupcity")
	public String searchCity(ModelMap model, @RequestParam("Lookupcity") String cityorZip,
			@RequestParam(value = "id", required = false) int id) {

		try {
			// List<UserLogin> memberList =
			// diabeteservice.getUserByName(cityorZip);
			if (lookupCity(cityorZip).isEmpty()) {
				model.addAttribute("error", "There are no matches for this Look-up");
			} else {
				if (!(id == -1)) {
					model.addAttribute("users", lookupCity(cityorZip));
					model.addAttribute("user", diabeteservice.getUserById(id));
					if (Pattern.matches("^\\d+$", cityorZip)) {
						model.addAttribute("success",
								lookupCity(cityorZip).size() + " Members found in Postal ZIP  " + cityorZip);
					} else
						model.addAttribute("success",
								lookupCity(cityorZip).size() + " Members found in the city of " + cityorZip);
					return "members";
				} else

					model.addAttribute("users", lookupCity(cityorZip));
				model.addAttribute("success", lookupCity(cityorZip).size() + " Members found in " + cityorZip);

			}

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occurred");
			e.printStackTrace();
		}

		return "services";
	}

	@SuppressWarnings("rawtypes")
	public List<UserLogin> lookupCity(String cityOrZip) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		final String sql = "SELECT m.* FROM diabetes.users m WHERE m.city like '%" + cityOrZip + "%'" + " UNION "
				+ "SELECT m.* FROM diabetes.users m WHERE m.zip like '%" + cityOrZip + "%'";
		@SuppressWarnings("unchecked")
		List<UserLogin> memberList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserLogin.class));
		return memberList;

	}

	@RequestMapping(value = { "index", "/" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("msg", "Hi welcome to diabetes club");
		model.addAttribute("m", "Select Units");
		model.addAttribute("wt", "lb/kg");
		model.addAttribute("ht", "ft/cm");

		return "index";
	}

	@RequestMapping(value = "bmi", method = RequestMethod.POST)
	public String bmi(@RequestParam Map<String, String> requestParams, Model model) throws Exception {
		String w = requestParams.get("w");
		String h = requestParams.get("h");
		String m = requestParams.get("system");
		Integer wt = Integer.valueOf(w);
		Integer ht = Integer.valueOf(h);

		if (m.equals("metric")) {
			// metric system SI units
			double bmi = diabetesUtils.getBmi(wt, ht, 10000);
			// model.addAttribute("welcome", "You selected "+m+" Height "+ h+ "
			// cm weight " +w+ " kg" );
			model.addAttribute("bmi", "Your BMI is " + bmi);
			model.addAttribute("status", "You are " + diabetesUtils.getStatus(bmi));
			model.addAttribute("m", m);
			model.addAttribute("wt", "kg");
			model.addAttribute("ht", "cm");
			model.addAttribute("w", wt);
			model.addAttribute("h", ht);
		} else {
			// Imperial system US units
			if (m.equals("imperial")) {
				double bmi = diabetesUtils.getBmi(wt, ht, 703);
				// model.addAttribute("welcome", "You selected " +m+ " Hight "+
				// h+ " in weight " +w+ " lb");
				model.addAttribute("bmi", "Your BMI is " + bmi);
				model.addAttribute("status", " - " + diabetesUtils.getStatus(bmi));
				model.addAttribute("m", m);
				model.addAttribute("wt", "lb");
				model.addAttribute("ht", "ft");
				model.addAttribute("w", wt);
				model.addAttribute("h", ht);
			}

		}
		return "index";
	}

/*	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		
		model.addAttribute("users", locationService.getAllAddresses());

		return "about";
	}
*/
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model) {
		model.addAttribute("sendEmail", new UserLogin());
		model.addAttribute("message", "Welcome to diabetes, please drop us an email");

		return "contact";
	}

	@RequestMapping("messageArtist")
	public String contactartist(@ModelAttribute("messageArtist") Diabetes diabetes, BindingResult bindingResult,
			Model model) {

		String validemails = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String message = "Name: " + diabetes.getName() + "\nEmail: " + diabetes.getSubject() + "\n\nMessage: "
				+ diabetes.getMsg();

		try {
			UserLogin userdetail = diabeteservice.getUserById(diabetes.getId());

			if (!diabetes.getSubject().matches(validemails)) {
				model.addAttribute("attribs", userdetail);
				model.addAttribute("error",
						"Invalid email " + diabetes.getSubject() + " please use format johnsmith@scglobal.com ");
			} else {
				sendMail.sendMails(diabetes.getEmail(), diabetes.getSubject(), message.toString());
				model.addAttribute("success",
						"Thank you " + diabetes.getName() + ", an email has been sent to " + userdetail.getFirstName());
				model.addAttribute("attribs", userdetail);
			}
		} catch (Exception e) {
			model.addAttribute("sendmailfail", "Error sending email  ");
			e.printStackTrace();
		}

		return "bio";
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("sendEmail") Diabetes diabetes, BindingResult bindingResult, Model model) {

		String validemails = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		model.addAttribute("em", diabetes.getEmail());
		model.addAttribute("sb", diabetes.getSubject());
		String message = "Subject: " + diabetes.getSubject() + "\nMessage: " + diabetes.getMsg();

		if (!diabetes.getEmail().matches(validemails)) {
			model.addAttribute("emailrror",
					"Invalid email " + diabetes.getEmail() + " use format johnsmith@scglobal.com ");
		}

		if (diabetes.getSubject().isEmpty()) {
			model.addAttribute("sbjrror", "Please enter Emain Subject");
		}

		if (diabetes.getMsg().length() <= 1 || diabetes.getMsg().length() > 3000) {
			model.addAttribute("msgerror", "Must be between 10 and 300 characters");
		}

		else

			try {
				sendMail.sendMails(diabetes.getEmail(), diabetes.getSubject(), message.toString());
				model.addAttribute("success", "Thanks Email has been sent to " + diabetes.getEmail());
				model.addAttribute("emailrror", "");
				model.addAttribute("sbjrror", "");
				model.addAttribute("msgerror", "");
				model.addAttribute("em", "");
				model.addAttribute("sb", "");

			} catch (Exception e) {
				model.addAttribute("sendmailfail", "Error sending email  ");
				e.printStackTrace();
			}

		return "contact";
	}

	@RequestMapping("writeBio")
	public String changeMembershipType(@ModelAttribute("writeBio") UserLogin userLogin, ModelMap model)
			throws ParseException {
		try {
//			writeBio(userLogin, userLogin.getBackground(), userLogin.getId());
			model.addAttribute("success",
					"Hi " + diabeteservice.getUserById(userLogin.getId()).getFirstName() + " Thanks, Biography saved");
			model.addAttribute("attribs", diabeteservice.getUserById(userLogin.getId()));

		} catch (RuntimeException e) {
			model.addAttribute("attribs", diabeteservice.getUserById(userLogin.getId()));
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}
		return "profile";
	}

/*	public void writeBio(UserLogin userLogin, String bio, long id) {

		String UpdateSql = "UPDATE users SET background=? where id=? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(UpdateSql, new Object[] { userLogin.getBackground(), userLogin.getId()});
	}*/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserLogin getUserByNane(String name) {
		String sql = "SELECT * FROM diabetes.users WHERE lname = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		UserLogin userLogin = (UserLogin) jdbcTemplate.queryForObject(sql, new Object[] { name },
				new BeanPropertyRowMapper(UserLogin.class));
		return userLogin;
	}
	
	@SuppressWarnings("rawtypes")
	public List<UserLogin> lookupMembers(String name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);        	
       	final String sql  = "SELECT m.* FROM diabetes.users m WHERE m.lname like '%"+ name +"%'"
       			            + " UNION "
       			            + "SELECT m.* FROM diabetes.users m WHERE m.fname like '%"+ name +"%'";
       	@SuppressWarnings("unchecked")
   		List<UserLogin> memberList = jdbcTemplate.query(sql, new BeanPropertyRowMapper( UserLogin.class ));						
   		return memberList;
   		 
	}
}
