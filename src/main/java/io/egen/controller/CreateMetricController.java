package io.egen.controller;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;
import io.egen.config.PropertiesConfig;
import io.egen.dto.WeightDTO;
import io.egen.metric.helper.MetricHelper;
import io.egen.rules.WeightRule;
import io.egen.service.MetricService;

import javax.ws.rs.Path;

import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Path("/resources/")
@Controller
public class CreateMetricController {

	@Autowired
	private MetricService metricService;
	
	@Autowired
	private WeightRule weightRule;
	
	@Autowired
	private MetricHelper metricHelper;
	
	@Autowired
	private PropertiesConfig propertiesConfig;

	/**
	 * Method to create metric. It also evaluates rules
	 * and inserts alerts
	 * @param json
	 */
	@RequestMapping(method = RequestMethod.POST, value = "createMetric")
	@ResponseBody
	public void createMetric(@RequestBody String json) {
		WeightDTO weightDTO = new WeightDTO();
		weightDTO.setActualWeight(metricHelper.getActualWeight(json));
		weightDTO.setTimeStamp(metricHelper.getActualTime(json));
		evaluateRule(json,weightDTO);		
		metricService.createMetric(weightDTO);
	}

	/**
	 * Method to evaluate rule and send alerts
	 * 
	 * @param base_weight
	 * @param currentWt
	 */
	private void evaluateRule(String currentWtJson, WeightDTO weightDTO) {
		// create a RuleEngine instance
		RulesEngine rulesEngine = aNewRulesEngine().build();
		
		// register the rules
		rulesEngine.registerRule(weightRule);
		String baseWeight = propertiesConfig.getProperty("BASE_WEIGHT");
		double base_weight = Double.parseDouble(baseWeight);
		weightRule.setBaseWeight(base_weight);
		weightRule.setCurrentWeightStr(currentWtJson);
		weightDTO.setAlert(false);
		weightRule.setWeightDTO(weightDTO);
		rulesEngine.fireRules();

	}

}
