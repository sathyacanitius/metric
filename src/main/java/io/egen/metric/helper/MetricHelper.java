package io.egen.metric.helper;

import io.egen.dto.WeightDTO;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

@Component
public class MetricHelper {

	/**
	 * Method to get actual weight from the input string
	 * 
	 * @param inputJson
	 * @return
	 */
	public double getActualWeight(String inputJson) {
		double currentWeight = 0.0;
		JsonElement jelement = new JsonParser().parse(inputJson);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonPrimitive jsonPrimitive = jobject.getAsJsonPrimitive("value");
		currentWeight = jsonPrimitive.getAsDouble();
		return currentWeight;
	}

	/**
	 * Method to get the actual time from the input string
	 * 
	 * @param inputJson
	 * @return
	 */
	public Date getActualTime(String inputJson) {
		JsonElement jelement = new JsonParser().parse(inputJson);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonPrimitive jsonPrimitive = jobject.getAsJsonPrimitive("timeStamp");
		Long currentTimeSTamp = jsonPrimitive.getAsLong();
		Date actualTime = new Date(currentTimeSTamp);
		return actualTime;
	}

	public String populateResponse(List<WeightDTO> weightDTOList) {
		JsonParser jparser = new JsonParser();
		Gson jgson = new Gson();
		JsonArray jsonArray = new JsonArray();
		for (WeightDTO weightDTO : weightDTOList) {
			jsonArray
					.add(jparser.parse(jgson.toJson(weightDTO.getTimeStamp())));
			jsonArray.add(jparser.parse(jgson.toJson(weightDTO
					.getActualWeight())));
		}
		return jsonArray.toString();
	}

}
