package com.mettles.rioc.x12;

import java.util.ArrayList;

public class SegLvlErrInfo {
	

	public String getSegerror() {
		return segerror;
	}
	public void setSegerror(String segerror) {
		this.segerror = segerror;
	}
	public String getSegerrorcode() {
		return segerrorcode;
	}
	public void setSegerrorcode(String segerrorcode) {
		this.segerrorcode = segerrorcode;
	}

	public String getErrorCntext() {
		return errorCntext;
	}
	public void setErrorCntext(String errorCntext) {
		this.errorCntext = errorCntext;
	}
	public String getSegmentInfo() {
		return segmentInfo;
	}
	public void setSegmentInfo(String segmentInfo) {
		this.segmentInfo = segmentInfo;
	}

	private String segerror;
	private String segerrorcode;

	public ArrayList<ElemLvlErrInfo> getElemerrs() {
		if(elemerrs == null)
			elemerrs = new ArrayList<>();
			
		return elemerrs;
	}


	private ArrayList<ElemLvlErrInfo> elemerrs;
	
	private String errorCntext;
	
	private String segmentInfo;
	

}
