package io.egen.controller;

import io.egen.service.AlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/readAlert")
public class ReadAlertController {

	@Autowired
	private AlertService alertService;

	/**
	 * Method to read all alert
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "readAllAlert")
	@ResponseBody
	public String readAllAlert() {
		String alertStr = alertService.getAllAlert();
		return alertStr;
	}

	/**
	 * Method to read alert between two timestamp
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/startTime/{startTime}/endTime/{endTime}", method = RequestMethod.GET)
	@ResponseBody
	public String readlAlertByTimeStamp(
			@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime) {
		String alertStr = alertService.getAlertByTimeStamp(startTime, endTime);
		return alertStr;
	}

}
