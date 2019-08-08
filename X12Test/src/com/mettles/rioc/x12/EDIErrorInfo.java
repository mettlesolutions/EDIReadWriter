package com.mettles.rioc.x12;

import java.util.ArrayList;

public class EDIErrorInfo {
	

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesp() {
		return errorDesp;
	}
	public void setErrorDesp(String errorDesp) {
		this.errorDesp = errorDesp;
	}

	private String errorCode;
	private String errorDesp;
	
	private ArrayList<SegLvlErrInfo> segerrs;

	public ArrayList<SegLvlErrInfo> getSegerrs() {
		return segerrs;
	}
	public void setSegerrs(ArrayList<SegLvlErrInfo> segerrs) {
		this.segerrs = segerrs;
	}
	
	private String Status;

	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

}
