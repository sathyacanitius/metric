package io.egen.controller;

import io.egen.service.MetricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/readMetric")
public class ReadMetricController {

	@Autowired
	private MetricService metricService;

	/**
	 * Method to read all metric
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "readAllMetric")
	@ResponseBody
	public String readAllMetric() {
		String alertStr = metricService.getAllMetric();
		return alertStr;
	}

	/**
	 * Method to read metric between two timestamp
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/startTime/{startTime}/endTime/{endTime}", method = RequestMethod.GET)
	@ResponseBody
	public String readMetricByTimeStamp(
			@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime) {
		String alertStr = metricService
				.getMetricByTimeStamp(startTime, endTime);
		return alertStr;
	}

}
