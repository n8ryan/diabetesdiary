package com.diabetes.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import com.diabetes.model.Exercise;
import com.diabetes.model.Glucose;
import com.diabetes.model.Statistics;
import com.diabetes.service.MailService;
import com.diabetes.service.SendMail;
import com.diabetes.service.DiabetesService;
/*import com.diabetes.service.LocationService;*/
import com.diabetes.diabetesUtils.States;
import com.diabetes.diabetesUtils.States.Days;

@Controller
@SessionAttributes("emailAddress")
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);
	// static final Logger logger = (Logger)
	// LoggerFactory.getLogger(UserController.class);

	public UserController() {
		logger.info("in UserController servelet");
	}

	@Autowired
	private MailService mailServices;

	@Autowired
	DataSource dataSource;

	SendMail sendMail = new SendMail();

	SendMail mailService = new SendMail();

	@Autowired
	private DiabetesService diabetesService;

	/*
	 * @Autowired LocationService locationService;
	 */
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public String service(Model model) {
		model.addAttribute("message", "Welcome to diabetes club ");

		return "services";
	}

	@RequestMapping(value = "/signups", method = RequestMethod.GET)
	public String signups(Model model) {
		model.addAttribute("signupMember", new UserLogin());
		logger.info("#######################signup out : ");

		return "signups";
	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.GET)
	public String loginuser(Model model) {
		model.addAttribute("msg", "New Login for Test!");
		model.addAttribute("newlogin", new UserLogin());
		return "loginUser";
	}

	@RequestMapping("newlogin")
	public String newlogin(@ModelAttribute("newlogin") UserLogin user, ModelMap model) throws ParseException {

		logger.info("user logged in : " + user.getEmail());

		try {

			UserLogin userdetail = diabetesService.getUserByEmailId(user.getEmail());

			if (validateLogin(user.getEmail(), user.getPassword())) {
				model.addAttribute("success", "Welcome " + userdetail.getFirstName());
				model.addAttribute("attribs", userdetail);
				model.addAttribute("user", userdetail.getFirstName());

				return "profile";

			} else {
				model.addAttribute("error", "Invalid credentials try again ");
			}
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("error", "User " + user.getEmail() + " does not exists <a href='signups'>Sign up </a>");
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}

		return "login";
	}

	/*
	 * @RequestMapping(value = "/newlogin", method = RequestMethod.POST) public
	 * String newlogin(@ModelAttribute("newlogin") UserLogin user, ModelMap
	 * model) throws ParseException {
	 * 
	 * logger.info("user logged in : " + user.getEmail());
	 * 
	 * try { logger.info("This is info : " + user.getEmail()); UserLogin
	 * userdetail = diabetesService.getUserByEmailId(user.getEmail()); if
	 * (diabetesService.findUser(user.getEmail()) == true) { if
	 * (userdetail.getEmail().equals(user.getEmail()) &&
	 * userdetail.getPassword().equals(user.getPassword())) { {
	 * model.addAttribute("success", "Welcome " + userdetail.getFirstName() +
	 * ". This is a secure zone! "); model.addAttribute("attribs", userdetail);
	 * model.addAttribute("user", userdetail.getFirstName());
	 * 
	 * return "profile"; } } else { model.addAttribute("error",
	 * "Invalid credentials try again "); } } } catch
	 * (EmptyResultDataAccessException e) { model.addAttribute("error", "User "
	 * + user.getEmail() + " does not exists <a href='signups'>Sign up </a>"); }
	 * catch (RuntimeException e) { model.addAttribute("error",
	 * "Unexpected error occured"); e.printStackTrace();
	 * 
	 * }
	 * 
	 * return "loginUser"; }
	 */

	@RequestMapping("signup")
	public String signup(@ModelAttribute("signup") UserLogin userLogin, BindingResult bindingResult, ModelMap model)
			throws ParseException {
		String msg = "Welcome to diabetes Login now <a href='http://localhost:8080/diabetes/login'>Login </a>";
		model.addAttribute("message", "Welcome " + userLogin.getFirstName() + " Login now <a href='login'>Login </a>");

		return "signups";

	}

	@RequestMapping("signupMember")
	public String signupMember(@ModelAttribute("signupMember") UserLogin userLogin, BindingResult bindingResult,
			ModelMap model) throws ParseException {
		String msg = "Welcome to diabetes Login now <a href='http://localhost:8080/diabetes/login'>Login </a>";
		try {
			if (diabetesService.findUser(userLogin.getEmail()) == false) {
				if (userLogin.getPassword().equals(userLogin.getPasswordConfirm())) {
					diabetesService.createUser(userLogin);
					model.addAttribute("message",
							"Welcome " + userLogin.getFirstName() + " Login now <a href='login'>Login </a>");

					try {
						sendMail.sendMails(userLogin.getEmail(), "Welcome to diabetes ", msg);
						model.addAttribute("success",
								"Confirmation email has been sent to " + userLogin.getEmail() + " thanks");

					} catch (Exception e) {
						model.addAttribute("error", "Error sending confirmation email to " + userLogin.getEmail());
					}

					return "login";
				} else {
					model.addAttribute("error", "Password not matching ");
				}

			} else {
				model.addAttribute("error", "A user exists with the supplied email " + userLogin.getEmail()
						+ " <a href='forgotpassword'> Forgot Password click here </a>");

			}

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			logger.error("Sorry, Unexpected error occured!", e);
			e.printStackTrace();

		}

		return "signups";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		logger.info("someone logg in out : ");
		if (logger.isDebugEnabled()) {
			logger.debug("logout out : ");
		}

		model.addAttribute("msg", "You have been logged out!");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		logger.info("called  log in : ");

		if (logger.isDebugEnabled()) {
			logger.debug("This is debug : ");
		}

		logger.debug("someone logg in out : ");

		model.addAttribute("loginMember", new UserLogin());
		model.addAttribute("msg", "Login to explore the complete features!");
		return "login";
	}

	@RequestMapping(value = "/loginMember", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginMember") UserLogin user, ModelMap model) throws ParseException {

		logger.info("user logged in : " + user.getEmail());
		System.out.println("Here: " + user.getEmail());

		try {
			logger.info("This is info : " + user.getEmail());
			UserLogin userdetail = diabetesService.getUserByEmailId(user.getEmail());
			if (diabetesService.findUser(user.getEmail()) == true) {
				if (userdetail.getEmail().equals(user.getEmail())
						&& userdetail.getPassword().equals(user.getPassword())) {
					{
						model.addAttribute("success",
								"Welcome " + userdetail.getFirstName() + ". This is a secure zone! ");
						model.addAttribute("attribs", userdetail);
						model.addAttribute("user", userdetail.getFirstName());

						return "profile";
					}
				} else {
					model.addAttribute("error", "Invalid credentials try again ");
				}
			}
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("error", "User " + user.getEmail() + " does not exists <a href='signups'>Sign up </a>");
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}

		return "login";
	}

	@RequestMapping("userEdits")
	public String userEdits(@RequestParam int id, @ModelAttribute UserLogin userLogin, ModelMap model) {

		try {
			model.addAttribute("attribs", diabetesService.getUserById(id));
			model.addAttribute("user", diabetesService.getUserById(id).getFirstName());
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}

		return "UserUpdateForm";
	}

	// @RequestMapping("updateStats")
	// public String saupdate(@ModelAttribute("updateStats") Statistics
	// userLogin, BindingResult bindingResult, ModelMap model)
	// throws ParseException {
	// try {
	//
	// System.out.println(userLogin.getActivity());
	// } catch (RuntimeException e) {
	// model.addAttribute("error", "Unexpected error occured");
	// e.printStackTrace();
	//
	// }
	// return "about";
	// }
	
	@RequestMapping("exercises")
	public String exercise(@ModelAttribute("exercises") Exercise exercise, BindingResult bindingResult, ModelMap model)
			throws ParseException {
		try {
			insert(exercise.getActivity(), exercise.getDuration());
			model.addAttribute("message", "Update Success "+exercise.getActivity()+ "Duration " +exercise.getDuration() );
			System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+exercise.getDuration());
			
			
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}
		return "stats";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Exercise> getstas() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		final String sql = "SELECT * FROM diabetes.Exercise";

		List<Exercise> allstats = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Exercise.class));
		return allstats;

	}
	
	
	public void insert(String d, String a) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//final String sql = "INSERT INTO diabetes.Exercise SET activity=?,  duration=?", d, a);
		jdbcTemplate.update("INSERT INTO diabetes.Exercise SET activity=?,  duration=?", d, a);
	}
	
	

	@RequestMapping("userEdit")
	public String userEdit(@ModelAttribute("userEdit") UserLogin userLogin, BindingResult bindingResult, ModelMap model)
			throws ParseException {
		try {
			diabetesService.updateUserById(userLogin, userLogin.getId());
			model.addAttribute("message", "Update Success");
			model.addAttribute("attribs", diabetesService.getUserById(userLogin.getId()));
			model.addAttribute("user", diabetesService.getUserById(userLogin.getId()).getFirstName());

			return "profile";
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}
		return "UserUpdateForm";
	}

	@RequestMapping("saveUser")
	public String adminEdit(@ModelAttribute("saveUser") UserLogin userLogin, @RequestParam int loggedin,
			BindingResult bindingResult, ModelMap model) throws ParseException {
		try {
			diabetesService.updateUserById(userLogin, userLogin.getId());
			model.addAttribute("message",
					"User " + userLogin.getFirstName() + " " + userLogin.getLastName() + " Updated");
			model.addAttribute("users", diabetesService.getAllusers());

			model.addAttribute("user", diabetesService.getUserById(loggedin).getLastName());
			model.addAttribute("id", loggedin);
			return "admin";
		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}
		return "dataEdit";
	}

	@RequestMapping("statEntry")
	public String statEntry(@RequestParam int id, @ModelAttribute UserLogin user, Model model) {

		System.out.println("User details: " + user.getEmail());
		model.addAttribute("updateStats", new Statistics());
		return "dataEntry";

	}

	@RequestMapping(value = "updateStats", method = RequestMethod.POST)
	public String updateStats(@ModelAttribute("updateStats") Statistics myStatistics, Model model) {
		Exercise exercise = new Exercise();
		exercise.setActivity(myStatistics.getActivity());
		
		
		
		
		Glucose glucose = new Glucose();
		
		System.out.println("New Statistics : " + myStatistics.getActivity());
		model.addAttribute("myStatistics", new Statistics());
		return "about";
	}

	@RequestMapping("viewCharts")
	public String viewCharts(@RequestParam int id, @ModelAttribute UserLogin user, Model model) {

		return "charts";

	}	
	
	@RequestMapping("stats")
	public String stats(@RequestParam int id, @ModelAttribute UserLogin user, Model model) {

		return "dataEntry";

	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profile(@RequestParam int id, @ModelAttribute UserLogin user, Model model) {
		UserLogin userdetail = diabetesService.getUserById(id);

		model.addAttribute("success", "Welcome " + userdetail.getFirstName() + ". This is a secure zone! ");
		model.addAttribute("attribs", userdetail);
		model.addAttribute("user", userdetail.getFirstName());

		return "profile";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotpass(Model model) {
		return "forgotpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetpass(Model model) {
		model.addAttribute("message", "Reset your password");

		return "resetpassword";
	}

	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	public String forgotpass(@ModelAttribute("forgotpass") Diabetes diabetes, BindingResult bindingResult,
			Model model) {

		String validemails = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		model.addAttribute("em", diabetes.getEmail());

		if (!diabetes.getEmail().matches(validemails)) {
			model.addAttribute("emailrror",
					"Invalid email " + diabetes.getEmail() + " use format johnsmith@scglobal.com ");
		} else

			try {
				if (diabetesService.findUser(diabetes.getEmail()) == true) {
					try {
						mailServices.sendpasswordemail(diabetes.getEmail());
						model.addAttribute("success",
								"Password reset instuction has been sent to " + diabetes.getEmail());
						model.addAttribute("emailrror", "");
						model.addAttribute("em", "");

					} catch (Exception e) {
						model.addAttribute("error", "Error sending email to " + diabetes.getEmail());
					}

				} else {
					model.addAttribute("success", "user not found " + diabetes.getEmail());
				}
			} catch (Exception e) {
				model.addAttribute("sendmailfail", "Error sending email  ");
				e.printStackTrace();
			}

		return "login";
	}

	@RequestMapping(value = "/getnewpass", method = RequestMethod.POST)
	public String setpass(@ModelAttribute("getnewpass") UserLogin userLogin,
			@RequestParam(value = "id", required = false) long id, BindingResult bindingResult, ModelMap model)
			throws ParseException {
		try {
			UserLogin userdetail = diabetesService.getUserByEmailId(userLogin.getEmail());
			if (diabetesService.findUser(userLogin.getEmail()) == true) {
				if (userLogin.getPassword().equals(userLogin.getPasswordConfirm())) {

					diabetesService.passReset(userLogin, userLogin.getPassword(), userLogin.getEmail());
					model.addAttribute("success", "password reset success, please keep your password safely.");
					model.addAttribute("attribs", userdetail);
					model.addAttribute("user", userdetail.getFirstName());

					try {
						mailService.sendMails(userLogin.getEmail(), "diabetes Password",
								"Your password was reset contact admin if you did not do it");

					} catch (Exception e) {
						if (id < 0) {
							model.addAttribute("error",
									"Error sending email to " + userdetail.getEmail() + " Please check your network");
							return "resetpassword";
						}
						model.addAttribute("error",
								"Error sending email to " + userdetail.getEmail() + " Please check your network");
					}
				} else {

					if (id < 0) {
						model.addAttribute("error", "Password not matching ");
						return "resetpassword";
					} else

						model.addAttribute("attribs", userdetail);
					model.addAttribute("user", userdetail.getFirstName());
					model.addAttribute("error", "Password not matching ");
				}
			} else {
				model.addAttribute("error", "No user found, <a href='signups'>Sign up </a>");
				return "login";
			}

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}
		return "profile";
	}

	/*
	 * @RequestMapping("changeSubscriptionType") public String
	 * changeMembershipType(@ModelAttribute("changeSubscriptionType") UserLogin
	 * userLogin, BindingResult bindingResult, ModelMap model) throws
	 * ParseException { try { // long //
	 * creditcard=diabetesService.getUserById(userLogin.getId()).getCard();
	 * 
	 * diabetesService.updateSubscription(userLogin,
	 * userLogin.getSubscription(), userLogin.getId());
	 * model.addAttribute("success", "Hi " +
	 * diabetesService.getUserById(userLogin.getId()).getFname() +
	 * " Your subscription changed, your credit card on file was proccessed");
	 * model.addAttribute("attribs",
	 * diabetesService.getUserById(userLogin.getId()));
	 * 
	 * } catch (RuntimeException e) { model.addAttribute("error",
	 * "Unexpected error occured"); e.printStackTrace();
	 * 
	 * } return "profile"; }
	 */

	@RequestMapping("searchMembers")
	public String searchMembers(ModelMap model, @RequestParam("searchNames") String names, @RequestParam("id") int id) {

		try {
			// List<UserLogin> memberList =
			// diabetesService.lookupMembers(names);
			if (diabetesService.lookupMembers(names).isEmpty()) {
				model.addAttribute("error", "There are no matches for this Look-up");
				model.addAttribute("user", diabetesService.getUserById(id).getLastName());
				model.addAttribute("id", id);
			} else
				model.addAttribute("users", diabetesService.lookupMembers(names));
			model.addAttribute("user", diabetesService.getUserById(id).getLastName());
			model.addAttribute("id", id);

		} catch (RuntimeException e) {
			model.addAttribute("error", "Unexpected error occured");
			e.printStackTrace();

		}

		return "admin";
	}

	@ModelAttribute("states")
	public List<States> populateStates() {
		return Arrays.asList(States.values());
	}

	/*
	 * @ModelAttribute("address") public List<Addresses> address() { return
	 * locationService.getAllAddresses(); }
	 * 
	 * @ModelAttribute("days") public Days[] day() { return Days.values(); }
	 */

	public boolean validateLogin(String uname, String pswd) {
		UserLogin dbCredentials = diabetesService.getUserByEmailId(uname);
		if (!(uname.equals(dbCredentials.getEmail()) && pswd.equals(dbCredentials.getPassword()))) {
			return false;
		} else
			return true;
	}

}
