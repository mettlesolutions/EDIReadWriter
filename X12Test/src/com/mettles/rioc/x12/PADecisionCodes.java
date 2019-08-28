package com.mettles.rioc.x12;

public enum PADecisionCodes {
	
	HCRA1("Certified in total (Affirmed Decision)"),
	HCRA2("Certified – partial (Partially Affirmed Decision)"),
	HCRA3("Not Certified (Non-Affirmed Decision)"),
	HCRA4("Pended"),
	HCRC("Cancelled");
	
	  private String description;
	 
	PADecisionCodes(String desp) {
      this.description = desp;
  }

  public String getDescription() {
      return description;
  }

}
