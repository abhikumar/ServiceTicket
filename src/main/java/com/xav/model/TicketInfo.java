package com.xav.model;

public class TicketInfo {
	public String getShiftTimingFrom() {
		return shiftTimingFrom;
	}
	public void setShiftTimingFrom(String shiftTimingFrom) {
		this.shiftTimingFrom = shiftTimingFrom;
	}
	public String getShiftTimingTo() {
		return shiftTimingTo;
	}
	public void setShiftTimingTo(String shiftTimingTo) {
		this.shiftTimingTo = shiftTimingTo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getTicketOpen() {
		return TicketOpen;
	}
	public void setTicketOpen(String ticketOpen) {
		TicketOpen = ticketOpen;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getSeatLocation() {
		return seatLocation;
	}
	public void setSeatLocation(String seatLocation) {
		this.seatLocation = seatLocation;
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
	public String getRequiredDuration() {
		return requiredDuration;
	}
	public void setRequiredDuration(String requiredDuration) {
		this.requiredDuration = requiredDuration;
	}
	public String getBusinessJustification() {
		return businessJustification;
	}
	public void setBusinessJustification(String businessJustification) {
		this.businessJustification = businessJustification;
	}
	private String category;
	private String subCategory;
	private String contactNumber;
	private String seatLocation;
	private String shiftTimingFrom;
	private String shiftTimingTo;
	private String projectName;
	private String departmentName;
	private String title;
	private String details;
	private String requiredDuration;
	private String businessJustification;
	private String userName;
	private String FullName;
	private String TicketOpen;
	

}
