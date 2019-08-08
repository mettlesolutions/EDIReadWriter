package com.mettles.rioc.x12;

public enum IK5ErrorCodes {
	
	
	ERR1("Transaction Set Not Supported"),
	  ERR2("Transaction Set Trailer Missing"),
	  ERR3("Transaction Set Control Number in Header and Trailer Do Not Match"),
	  ERR4("Number of Included Segments Does Not Match Actual Count"),
	 ERR5("One or More Segments in Error"),
	 ERR6("Missing or Invalid Transaction Set Identifier"),
	 ERR7("Missing or Invalid Transaction Set Control Number"),
	 ERR8("Authentication Key Name Unknown"),
	 ERR9("Encryption Key Name Unknown"),
	 ERR10("Requested Service (Authentication or Encrypted) Not Available"),
	 ERR11("Unknown Security Recipient"),
	 ERR12("Incorrect Message Length (Encryption Only)"),
	 ERR13("Message Authentication Code Failed"),
	 ERR15("Unknown Security Originator"),
	 ERR16("Syntax Error in Decrypted Text"),
	 ERR17("Security Not Supported"),
	 ERR18("Transaction Set not in Functional Group"),
	 ERR19("Invalid Transaction Set Implementation Convention Reference"),
	 ERR23("Transaction Set Control Number Not Unique within the Functional Group"),
	 ERR24("S3E Security End Segment Missing for S3S Security Start Segment"),
	 ERR25("S3S Security Start Segment Missing for S3E Security End Segment"),
	 ERR26("S4E Security End Segment Missing for S4S Security Start Segment"),
	ERR27("S4S Security Start Segment Missing for S4E Security End Segment"),
	ERRI5("Implementation One or More Segments in Error"),
	ERRI6("Implementation Convention Not Supported");
	
    private String description;
 
	IK5ErrorCodes(String desp) {
        this.description = desp;
    }
 
    public String getDescription() {
        return description;
    }


}
