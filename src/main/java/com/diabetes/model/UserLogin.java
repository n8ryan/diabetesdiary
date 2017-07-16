package com.diabetes.model;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class UserLogin implements Serializable {
	
	private static final long serialVersionUID = -7988799579036200037L;	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@NotEmpty
	@Pattern(regexp = "([a-zA-Z0-9]+([ ][a-zA-Z0-9])*)+",message="Last Name Must be alphanumeric ")
	private String lastName;

	@Pattern(regexp = "([a-zA-Z0-9]+([ ][a-zA-Z0-9])*)+",message="User Name Must be alphanumeric ")
	private String userName;

	//@NotEmpty(message = "First name cannot be empty")
	@Pattern(regexp = "([a-zA-Z0-9]+([ ][a-zA-Z0-9])*)+",message="First Name Required")
	private String firstName;

	private int age; 
	
	private int height;
	
	@Column(name = "receivetexts", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean receivetexts;
	
	@Column(name = "receiveemails", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean receiveemails;
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean getReceivetexts() {
		return receivetexts;
	}
	public void setReceivetexts(boolean receivetexts) {
		this.receivetexts = receivetexts;
	}
	public boolean getReceiveemails() {
		return receiveemails;
	}
	public void setReceiveemails(boolean receiveemails) {
		this.receiveemails = receiveemails;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String sex;

    @NotEmpty(message = "City required")
    private String city;

    private String state;     
    
    private String phone;
    
    private String type;
    
    @Column(updatable=false)
    // @NotEmpty(message = "User name cannot be empty")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Invalid Email Use format JohnSmith@sbc.com")
	private String email;

    // @NotEmpty(message = "Password name cannot be empty")
	@Size(min=8, max=15,message="The length must be between {min} and {max}")
	private String password;
	    
	@NotEmpty(message = "Please re-enter password ")
	private String passwordConfirm;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
	

}
