package com.diabetes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "glucose")
public class Glucose implements Serializable{
	
	private static final long serialVersionUID = -787666;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@NotEmpty(message = "glucose required ")
	private int glucose; 	
	@NotEmpty(message = "when required ")
	private String whenmeal;	
	@NotEmpty(message = "Date required ")
	private Date date;	
	@NotEmpty(message = "userid required ")
	private long userid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getGlucose() {
		return glucose;
	}
	public void setGlucose(int glucose) {
		this.glucose = glucose;
	}
	public String getWhenmeal() {
		return whenmeal;
	}
	public void setWhenmeal(String whenmeal) {
		this.whenmeal = whenmeal;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}

}
