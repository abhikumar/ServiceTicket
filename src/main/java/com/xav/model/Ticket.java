package com.xav.model;

import java.util.List;
import java.util.Map;

public class Ticket {
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Integer> getValidOption() {
		return validOption;
	}
	public void setValidOption(List<Integer> validOption) {
		this.validOption = validOption;
	}
	public Map<Integer, String> getCategoryDetails() {
		return categoryDetails;
	}
	public void setCategoryDetails(Map<Integer, String> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}
	private String message;
	List<Integer> validOption;
	Map<Integer,String> categoryDetails;
	/*String contactNumber;
	String seatLocation;
	String title;
	String details;
	String bDetails;
	String reqDetails;*/
	String showOn;
	private String contentValue;
	
	public String getContentValue() {
		return contentValue;
	}
	public void setContentValue(String contentValue) {
		this.contentValue = contentValue;
	}
	public String getShowOn() {
		return showOn;
	}
	public void setShowOn(String showOn) {
		this.showOn = showOn;
	}
	/*public String getReqDetails() {
		return reqDetails;
	}
	public void setReqDetails(String reqDetails) {
		this.reqDetails = reqDetails;
	}
	public String getbDetails() {
		return bDetails;
	}
	public void setbDetails(String bDetails) {
		this.bDetails = bDetails;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getSeatLocation() {
		return seatLocation;
	}
	public void setSeatLocation(String seatLocation) {
		this.seatLocation = seatLocation;
	}*/
	String nextStep;
	public String getNextStep() {
		return nextStep;
	}
	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}
	/*public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}*/

}
