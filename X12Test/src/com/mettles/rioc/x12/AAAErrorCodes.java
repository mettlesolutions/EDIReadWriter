package com.mettles.rioc.x12;

public enum AAAErrorCodes {
	
		
	AAAERR79("Invalid Participant Identification"),
	AAAERR35("Out of Network"),
	AAAERR43("Invalid/Missing Provider Identification"),
	AAAERR44("Invalid/Missing Provider Name"),
	AAAERR97("Invalid or Missing Provider Address"),
	AAAERR64("Invalid/Missing Patient ID"),
	AAAERR66("Invalid/Missing Patient Gender Code"),
	AAAERR65("Invalid/Missing Patient Name"),
	AAAERR58("Invalid/Missing Date-of-Birth"),
	AAAERRAF("Invalid/Missing Diagnosis Code(s)"),
	AAAERRAG("Invalid/Missing Procedure Code(s)"),
	AAAERR15("Required application data missing"),
	AAAERR57("Invalid/Missing Date(s) of Service"),
	AAAERR33("Input Errors"),
	AAAERR42("Unable to Respond at Current Time"),
	AAAERR51("Provider Not on File"),
	AAAERR50("Provider ineligible for inquiries"),
	AAAERR72("Invalid/Missing Subscriber/Insured ID"),
	AAAERR56(" Inappropriate Date (Issue Date)"),
	AAAERR62(" Date of Service Not within allowable Inquiry Period"),
	AAAERR63(" Date of Service in Future");
	
	  private String description;
	 
	AAAErrorCodes(String desp) {
        this.description = desp;
    }
 
    public String getDescription() {
        return description;
    }

}
