package com.diabetes.DaoImpl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.diabetes.Dao.DiabetesDao;
import com.diabetes.Repository.HibernateUtil;
import com.diabetes.model.UserLogin;
import com.diabetes.diabetesUtils.Jdbc.DbConnection;

@Repository
public class DiabetesDaoImpl implements DiabetesDao{

	@Autowired
	DataSource dataSource;
	
	public DiabetesDaoImpl() {
    	System.out.println("diabetesDaoImpl");
    }
	
	@Autowired
    private HibernateUtil hibernateUtil;


	@Override
	public boolean findUser(String email) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    	String sql = "select email from diabetes.users where email = ? ";
        
        try {
        	jdbcTemplate.queryForObject(sql, String.class, email);
        	//System.out.println("true" );
            return true;
        } catch (EmptyResultDataAccessException e) {
        	//System.out.println("false");
            return false;
        }
	}

	
	@Override
	public long createUser(UserLogin userLogin) {
		return (Long) hibernateUtil.create(userLogin);
	}

	@Override
	public UserLogin updateUser(UserLogin userLogin) {
		return hibernateUtil.update(userLogin);
		}

	@Override
	public void deleteUserById(long id) {
		UserLogin userLogin = new UserLogin();
		userLogin.setId(id);
        hibernateUtil.delete(userLogin);
		
	}

	@Override
	public List<UserLogin> getAllusers() {
		return hibernateUtil.fetchAll(UserLogin.class);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public UserLogin getUserByEmailId(String email){
		String sql = "SELECT * FROM diabetes.users where email= ?";
     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
     UserLogin userLogin = (UserLogin) jdbcTemplate.queryForObject(
     sql, new Object[] { email }, new BeanPropertyRowMapper(UserLogin.class));
     return userLogin;
	}


	@Override
	public UserLogin getUserById(long id) {
		UserLogin userLogin = new UserLogin();
		userLogin.setId(id);
		return hibernateUtil.fetchById(id, UserLogin.class);
	}




	@Override
	 public void passReset(UserLogin userLogin, String pass, String email) {    	
    	String UpdateSql = "UPDATE users SET password=? where emailAddress=? ";    	
  	     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		  	  jdbcTemplate.update(
		  			UpdateSql,
		  			new Object[] { 		   
		  					    userLogin.getPassword(), 
		  					    userLogin.getEmail()
		  					  
		  						});  
		  	
		  	
  	  }


/*	@Override
	public void updateSubscription(UserLogin userLogin, String membership, long id) {    	
    	String UpdateSql = "UPDATE users SET subscription=? where id=? ";    	
  	     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		  	  jdbcTemplate.update(
		  			UpdateSql,
		  			new Object[] { 		   
		  					    userLogin.getSubscription(), 
		  					    userLogin.getId()
		  					  
		  						});  
		  	
		  	
  	  }
*/

	@Override
	@SuppressWarnings("rawtypes")
	public List<UserLogin> lookupMembers(String name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);        	
       	final String sql  = "SELECT m.* FROM diabetes.users m WHERE m.lname like '%"+ name +"%'";
       	@SuppressWarnings("unchecked")
   		List<UserLogin> memberList = jdbcTemplate.query(sql, new BeanPropertyRowMapper( UserLogin.class ));						
   		return memberList;
   		 
	}

	@Override
	public void updateUserById(UserLogin userLogin, long id) {

		String UpdateSql = "UPDATE users SET address=?, age=?, card=?, city=?, emailAddress=?, fname=?,"
		         + " lname=?, state=?, tel=?, zip=?, website=?, background=?, selectClub=? where id=? ";    	
	     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	  	  jdbcTemplate.update(
	  			UpdateSql,
	  			new Object[] { 
	  					userLogin.getAge(),
	  					userLogin.getCity(),
	  					userLogin.getEmail(),
	  					userLogin.getFirstName(),
	  					userLogin.getLastName(),
	  					userLogin.getState(),
	  					userLogin.getPhone(),
	  					userLogin.getId()
	  					
	  					}); 	  			
	     }
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<UserLogin> getUserByName(String lastname) { 
		
		String query = "SELECT m.* FROM diabetes.users m WHERE m.lname like '%"+ lastname +"%'";
		List<Object[]> UserLoginObjects = hibernateUtil.fetchAll(query);
		List<UserLogin> UserLogins = new ArrayList<UserLogin>();
		for(Object[] UserLoginObject: UserLoginObjects) {
			UserLogin UserLogin = new UserLogin();
			//id, address, age, card, city, emailAddress, fname, lname, password, state, tel, zip, subscription, passwordConfirm
			long id = ((BigInteger) UserLoginObject[0]).longValue();		
			String address = (String) UserLoginObject[1];
			int age = (int) UserLoginObject[2];
			long card = ((BigInteger) UserLoginObject[3]).longValue();		
			String city = (String) UserLoginObject[4];
			String emailAddress = (String) UserLoginObject[5];	
			String fname = (String) UserLoginObject[6];				
			String lname = (String) UserLoginObject[7];
			String membership = (String) UserLoginObject[13];						
			String state = (String) UserLoginObject[10];
			long tel = ((BigInteger) UserLoginObject[11]).longValue();	
			long zip = ((BigInteger) UserLoginObject[12]).longValue();	
			
            UserLogin.setId(id);
            UserLogin.setAddress(address);			
			UserLogin.setAge(age);
			UserLogin.setCard(card);
			UserLogin.setCity(city);
			UserLogin.setLname(lname);
			UserLogin.setFname(fname);			
			UserLogin.setEmailAddress(emailAddress);
			UserLogin.setSubscription(membership);
			UserLogin.setTel(tel);
			UserLogin.setState(state);
			UserLogin.setZip(zip);				
			UserLogins.add(UserLogin);
		}
		System.out.println(UserLogins);
		return UserLogins;
	}
*/

	@Override@
	SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserLogin> lookupCity(String cityOrZip) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 
		
       	final String sql  = "SELECT m.* FROM diabetes.users m WHERE m.city like '%"+ cityOrZip +"%'"
       			+ " UNION "
       			+ "SELECT m.* FROM diabetes.users m WHERE m.zip like '%"+ cityOrZip +"%'";
    
   		List<UserLogin> memberList = jdbcTemplate.query(sql, new BeanPropertyRowMapper( UserLogin.class ));						
   		return memberList;
   		 
	}


	@SuppressWarnings("unchecked")
	@Override
	public JSONObject jdbcDbConnect(String email) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;			
		
		JSONObject jsonObj = new JSONObject();	
		
		String selectSQL =  "SELECT id, firstName, lastName, userName, email, type, startDate, "
				+ "sex, height, age, phone FROM diabetes.users WHERE email = ?";
		
	try
	{
		if(dbConnection == null || dbConnection.isClosed())
			{
			dbConnection = (Connection) DbConnection.getConnection();
			}
		    		
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			// execute select SQL stetement
			 rs = preparedStatement.executeQuery();
			
			 int count = 0;
			 if (rs.next()) {
				    do {
				    	++count;
				    	String id = rs.getString("id");
						String firstName= rs.getString("firstName");	
						String lastName= rs.getString("firstname");	
						String userName= rs.getString("userName");	
						String userEmail= rs.getString("email");
						String type= rs.getString("type");						
						String startDate= rs.getString("startDate");						
						String sex= rs.getString("sex");						
						String height= rs.getString("height");						
						String age= rs.getString("age");						
						String phone= rs.getString("phone");						
						
					    System.out.println("count "+count);
						 
					    jsonObj.put("id", id);
						jsonObj.put("firstName", firstName);
						jsonObj.put("lastName" , lastName);
						jsonObj.put("userName", userName);
						jsonObj.put("email", userEmail);
						jsonObj.put("type", type); 
						jsonObj.put("startDate", startDate);  
						jsonObj.put("sex", sex); 								
						jsonObj.put("height", height); 
						jsonObj.put("age", age);  
						jsonObj.put("phone", phone);
						
						System.out.println(jsonObj); 
		 				
				    } while (rs.next());
				    rs.close();
				}	
			 rs.close();
		}
	
		catch(SQLException e){
			e.printStackTrace();;
		}
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally{
		try{
	        
	        if (!dbConnection.isClosed()){                                
	        	dbConnection.close();                           
	        }
	        
	        }catch(SQLException esqle){
	               System.out.println("In finally" + esqle.getMessage());                            
	        }
		}
	 return jsonObj; 
	}
	
}
