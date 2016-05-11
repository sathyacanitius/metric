package io.egen.service;

import io.egen.db.connection.ConnectionFactory;
import io.egen.dto.WeightDTO;
import io.egen.metric.helper.MetricHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private MetricHelper metricHelper;

	/**
	 * Method to get all alert
	 */
	public String getAllAlert() {
		Datastore ds = connectionFactory.getDataSource();
		Query<WeightDTO> query = ds.find(WeightDTO.class, "alert", true);
		List<WeightDTO> wtList = query.asList();
		return (wtList != null && !wtList.isEmpty()) ? metricHelper
				.populateResponse(wtList) : "No Data found";
	}

	/**
	 * Method to get alert between two timestamp
	 */
	public String getAlertByTimeStamp(String startTime, String endTime) {
		JsonArray jsonArray = new JsonArray();
		try {
			Datastore ds = connectionFactory.getDataSource();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.ENGLISH);
			Date fromDate = format.parse(startTime);
			Date toDate = format.parse(endTime);
			List<WeightDTO> wtList = ds.createQuery(WeightDTO.class)
					.filter("alert", true).field("timeStamp")
					.greaterThan(fromDate).field("timeStamp").lessThan(toDate)
					.asList();
			return (wtList != null && !wtList.isEmpty()) ? metricHelper
					.populateResponse(wtList) : "No Data found";
		} catch (Exception ex) {
			
		}
		return jsonArray.toString();
	}

}
