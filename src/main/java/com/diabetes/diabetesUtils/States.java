package com.diabetes.diabetesUtils;

public enum  States {

		AL("Alabama"),
	    AK("Alaska"),
	    AZ("Arizona"),
	    AR("Arkansas"),
	    CA("California"),
	    CO("Colorado"),
	    CT("Connecticut"),
	    DE("Delaware"),
	    FL("Florida"),
	    GA("Georgia"),
	    HI("Hawaii"),
	    ID("Idaho"),
	    IL("Illinois"),
	    IN("Indiana"),
	    IA("Iowa"),
	    KS("Kansas"),
	    KY("Kentucky"),
	    LA("Louisiana"),
	    ME("Maine"),
	    MD("Maryland"),
	    MT("Montana"),
	    MA("Massachusetts"),
	    VA("Virginia"),
	    MI("Michigan"),
	    WA("Washington"),
	    MN("Minnesota"),
	    MS("Mississippi"),
	    MO("Missouri"),
	    NC("North Carolina"),
	    ND("North Dakota"),
	    NE("Nebraska"),
	    NH("New Hampshire"),
	    NJ("New Jersey"),
	    NM("New Mexico"),
	    NV("Nevada"),
	    NY("New York"),
	    OH("Ohio"),
	    OK("Oklahoma"),
	    OR("Oregon"),
	    PA("Pennsylvania"),
	    RI("Rhode Island"),
	    SC("South Carolina"),
	    SD("South Dakota"),
	    TN("Tennessee"),
	    TX("Texas"),
	    UT("Utah"),
	    VT("Vermont"),
	    WV("West Virginia"),
	    WI("Wisconsin"),
	    WY("Wyoming");
		

	     private String id;

	     private States(String id){
	         this.id = id;
	     }

	     public String getId()
	     {
	         return id;
	     }
	     
	     public enum Days {
	    	 SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
	 	 }
	     
	     
}
