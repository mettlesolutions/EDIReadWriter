package com.mettles.rioc.x12;

public enum Ik4ErrorCodes {
	
	 ERR1("Required Data Element Missing"),
	  ERR2("Conditional Required Data Element Missing"),
	  ERR3("Too Many Data Elements"),
	  ERR4("Data Element Too Short"),
	    ERR5("Data Element Too Long"),
	    ERR6("Invalid Character In Data Element"),
	    ERR7("Invalid Code Value"),
	    ERR8("Invalid Date"),
	    ERR9("Invalid Time"),
	    ERR10("Exclusion Condition Violated"),
	    ERR12("Too Many Repetitions"),
	    ERR13("Too Many Components"),
	    ERRI10("Implementation \"Not Used\" Data Element Present"),
	    ERRI11("Implementation Too Few Repetitions"),
	    ERRI12("Implementation Pattern Match Failure"),
	    ERRI13("Implementation Dependent \"Not Used\" Data Element Present"),
	    ERRI6("Code Value Not Used in Implementation"),
	    ERRI9("Implementation Dependent Data Element Missing");
	    
	    
    private String description;
 
	Ik4ErrorCodes(String desp) {
        this.description = desp;
    }
 
    public String getDescription() {
        return description;
    }

}
