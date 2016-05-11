package io.egen.service;

import io.egen.dto.WeightDTO;

public interface MetricService {

	public void createMetric(WeightDTO weightDTO);

	public String getAllMetric();

	public String getMetricByTimeStamp(String startTime, String endTime);

}
