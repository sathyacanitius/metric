package io.egen.service;

public interface AlertService {
	
	public String getAllAlert();
	
	public String getAlertByTimeStamp(String startTime, String endTime);

}
