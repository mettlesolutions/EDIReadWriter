package com.mettles.rioc.x12;
import java.util.ArrayList;

public class X12278EDIResponse {
	
	
	private String senderId;
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRcvrId() {
		return rcvrId;
	}

	public void setRcvrId(String rcvrId) {
		this.rcvrId = rcvrId;
	}

	private String rcvrId;
	
	private String esmdtransid;
	
	public String getEsmdtransid() {
		return esmdtransid;
	}

	public void setEsmdtransid(String esmdtransid) {
		this.esmdtransid = esmdtransid;
	}
	
	private String clienttransid;
	

	public String getClienttransid() {
		return clienttransid;
	}

	public void setClienttransid(String clienttransid) {
		this.clienttransid = clienttransid;
	}

	public ArrayList<AAAErrorInfo> getErrors() {
		if(errors == null)
			errors = new ArrayList<AAAErrorInfo>();
		
		return errors;
	}
	
	public HealthCareReviewInfo getConsolidated_hcr() {
		return consolidated_hcr;
	}
	public void setConsolidated_hcr(HealthCareReviewInfo consolidated_hcr) {
		this.consolidated_hcr = consolidated_hcr;
	}
	public ArrayList<HealthCareReviewInfo> getService_healthcare() {
		if(service_healthcare == null)
		{
			service_healthcare = new ArrayList<HealthCareReviewInfo>();
		}
		return service_healthcare;
	}
/*	public void setService_healthcare(ArrayList<HealthCareReviewInfo> service_healthcare) {
		this.service_healthcare = service_healthcare;
	}*/
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private ArrayList<AAAErrorInfo> errors;
	private HealthCareReviewInfo consolidated_hcr;
	private ArrayList<HealthCareReviewInfo> service_healthcare;
	private String message;

}
