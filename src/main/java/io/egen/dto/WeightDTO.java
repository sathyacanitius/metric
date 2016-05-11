package io.egen.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class WeightDTO {
	

    @Id private ObjectId id;
	private Date timeStamp;
	private double actualWeight;
	private boolean alert;
	
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public double getActualWeight() {
		return actualWeight;
	}
	public void setActualWeight(double actualWeight) {
		this.actualWeight = actualWeight;
	}

}
