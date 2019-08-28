package com.mettles.rioc.x12;

public class HealthCareReviewInfo {
	public String getAction_code() {
		return action_code;
	}
	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}
	public String getReason_code() {
		return reason_code;
	}
	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}
	public String getUtn_value() {
		return utn_value;
	}
	public void setUtn_value(String utn_value) {
		this.utn_value = utn_value;
	}
	private String action_code;
	private String reason_code;
	private String utn_value;
	private String service_code;
	public String getService_code() {
		return service_code;
	}
	public void setService_code(String service_code) {
		this.service_code = service_code;
	}
	public String getService_cnt() {
		return service_cnt;
	}
	public void setService_cnt(String service_cnt) {
		this.service_cnt = service_cnt;
	}
	private String service_cnt;
	private String srv_message;
	public String getSrv_message() {
		return srv_message;
	}
	public void setSrv_message(String srv_message) {
		this.srv_message = srv_message;
	}

}
