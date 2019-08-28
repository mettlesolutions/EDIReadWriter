package com.mettles.rioc.x12;

public class EDIWriteStatus {

	private String EDIString;
	public String getEDIString() {
		return EDIString;
	}
	public void setEDIString(String eDIString) {
		EDIString = eDIString;
	}
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErr_dataelement() {
		return err_dataelement;
	}
	public void setErr_dataelement(String err_dataelement) {
		this.err_dataelement = err_dataelement;
	}
	public String getErr_message() {
		return err_message;
	}
	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}
	private int status_code;
	private String status;
	private String err_dataelement;
	private String err_message;
	private String pwk;
	public String getPwk() {
		return pwk;
	}
	public void setPwk(String pwk) {
		this.pwk = pwk;
	}
	
}
