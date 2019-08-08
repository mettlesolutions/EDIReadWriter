package com.mettles.rioc.x12;

public enum IK3ErrorCodes {

	 ERR1("Unrecognized segment ID"),
	  ERR2("Unexpected segment"),
	  ERR3("Required Segment Missing"),
	  ERR4("Loop Occurs Over Maximum Times"),
	    ERR5("Segment Exceeds Maximum Use"),
	   ERR6("Segment Not in Defined Transaction Set"),
	   ERR7("Segment Not in Proper Sequence"),
	   ERR8("Segment Has Data Element Errors"),
	   ERRI4("Implementation \"Not Used\" Segment Present"),
	   ERRI6("Implementation Dependent Segment Missing"),
	   ERRI7("Implementation Loop Occurs Under Minimum Times"),
	   ERRI8("Implementation Segment Below Minimum Use"),
	   ERRI9("Implementation Dependent \"Not Used\" Segment Present");
	 
	    private String description;
	 
	    IK3ErrorCodes(String desp) {
	        this.description = desp;
	    }
	 
	    public String getDescription() {
	        return description;
	    }
}
