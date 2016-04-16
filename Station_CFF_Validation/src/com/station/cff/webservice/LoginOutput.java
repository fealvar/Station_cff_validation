package com.station.cff.webservice;

public class LoginOutput {

	boolean success;
	String session_guid="";
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSession_guid() {
		return session_guid;
	}
	public void setSession_guid(String session_guid) {
		this.session_guid = session_guid;
	}
	
	
}
