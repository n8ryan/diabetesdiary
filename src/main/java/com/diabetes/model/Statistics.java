package com.diabetes.model;

import java.util.Date;

public class Statistics {
		private long id;
		private String activity; 	
		private Date duration;	
		private Date dayof;
		private int calories;
		private long userid;
		
		private int glucose; 	
		private String whenmeal;
		private Date date;
		
		
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
		public Date getDuration() {
			return duration;
		}
		public void setDuration(Date duration) {
			this.duration = duration;
		}
		public Date getDayof() {
			return dayof;
		}
		public void setDayof(Date dayof) {
			this.dayof = dayof;
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
		
		
}
