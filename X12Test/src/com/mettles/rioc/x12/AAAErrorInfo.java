package com.mettles.rioc.x12;

public class AAAErrorInfo {
private String error_code;
public String getError_code() {
	return error_code;
}
public void setError_code(String error_code) {
	this.error_code = error_code;
}
public String getError_string() {
	return error_string;
}
public void setError_string(String error_string) {
	this.error_string = error_string;
}
public String getFollowup_action() {
	return followup_action;
}
public void setFollowup_action(String followup_action) {
	this.followup_action = followup_action;
}
public String getSegment_info() {
	return segment_info;
}
public void setSegment_info(String segment_info) {
	this.segment_info = segment_info;
}
private String error_string;
private String followup_action;
private String segment_info;
}
