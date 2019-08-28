package com.mettles.rioc.x12;

import java.util.ArrayList;

public class UnSolicitedPWKElems {
  public EntityIdentityInfo getPayer() {
		return payer;
	}
	public void setPayer(EntityIdentityInfo payer) {
		this.payer = payer;
	}
	public EntityIdentityInfo getSubmitter() {
		return submitter;
	}
	public void setSubmitter(EntityIdentityInfo submitter) {
		this.submitter = submitter;
	}
	public EntityIdentityInfo getProvider() {
		return provider;
	}
	public void setProvider(EntityIdentityInfo provider) {
		this.provider = provider;
	}
	public EntityIdentityInfo getPatient() {
		return patient;
	}
	public void setPatient(EntityIdentityInfo patient) {
		this.patient = patient;
	}
	public String getEsMDClaimID() {
		return esMDClaimID;
	}
	public void setEsMDClaimID(String esMDClaimID) {
		this.esMDClaimID = esMDClaimID;
	}
	public String getAttctrlNum() {
		return attctrlNum;
	}
	public void setAttctrlNum(String attctrlNum) {
		this.attctrlNum = attctrlNum;
	}

private EntityIdentityInfo payer;
  private EntityIdentityInfo  submitter;
  private EntityIdentityInfo provider;
  private EntityIdentityInfo patient;
  private String esMDClaimID;
  private String attctrlNum;
  private ArrayList<String> b64PayloadList;
  
  public  ArrayList<String> getB64PayloadList(){
	  if(this.b64PayloadList == null) {
		  this.b64PayloadList  = new ArrayList<String>();
	  }
	  return b64PayloadList;
  }
}
