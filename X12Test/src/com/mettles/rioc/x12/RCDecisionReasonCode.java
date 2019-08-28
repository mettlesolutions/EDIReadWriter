package com.mettles.rioc.x12;

public enum RCDecisionReasonCode {
	RC0U("Successful Submission "),
	RC0P("2 – Business Day Warning"),
	RC0B("RC Pickup Notification / Cancellation if Document is not Submitted"),
	RC25("");
	
	  private String description;
	 
	  RCDecisionReasonCode(String desp) {
      this.description = desp;
  }

  public String getDescription() {
      return description;
  }

}
