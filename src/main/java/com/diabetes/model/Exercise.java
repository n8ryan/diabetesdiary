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
@Table(name = "exercise")
public class Exercise implements Serializable{
	
	private static final long serialVersionUID = -787666;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	private String activity; 	
	private String duration;	
	@NotEmpty(message = "Date required ")
	private Date dayof;	
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public Date getDayof() {
		return dayof;
	}
	public void setDayof(Date dayof) {
		this.dayof = dayof;
	}
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	@NotEmpty(message = "userid required ")
	private long userid;

}
