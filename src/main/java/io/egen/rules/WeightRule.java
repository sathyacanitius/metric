package io.egen.rules;

import io.egen.dto.WeightDTO;
import io.egen.metric.helper.MetricHelper;
import io.egen.service.AlertService;

import org.easyrules.core.BasicRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeightRule extends BasicRule {

	@Autowired
	private AlertService alertService;
	
	@Autowired
	private MetricHelper metricHelper;

	private double baseWeight;
	private String actualWeightStr;
	private WeightDTO weightDTO;
	
	public void setWeightDTO(WeightDTO weightDTO) {
		this.weightDTO = weightDTO;
	}

	public void setCurrentWeightStr(String actualWeightStr) {
		this.actualWeightStr = actualWeightStr;
	}

	public void setBaseWeight(double baseWeight) {
		this.baseWeight = baseWeight;
	}

	@Override
	public boolean evaluate() {
		boolean flag = false;
		double actualWeight = metricHelper.getActualWeight(actualWeightStr);
		double tenPercentWt = baseWeight + ((baseWeight * 10) / 100);
		flag = ((actualWeight > tenPercentWt) || (actualWeight < tenPercentWt)) ? true
				: false;
		return flag;
	}

	@Override
	public void execute() {
		weightDTO.setAlert(true);
	}





}
