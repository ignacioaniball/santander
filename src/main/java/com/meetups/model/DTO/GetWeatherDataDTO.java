package com.meetups.model.DTO;

public class GetWeatherDataDTO {
	
	private String weatherUrlString;
	
	private String city;
	
	private String meetupDate;
	

	public String getMeetupDate() {
		return meetupDate;
	}

	public void setMeetupDate(String meetupDate) {
		this.meetupDate = meetupDate;
	}

	public String getWeatherUrlString() {
		return weatherUrlString;
	}

	public void setWeatherUrlString(String weatherUrlString) {
		this.weatherUrlString = weatherUrlString;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
