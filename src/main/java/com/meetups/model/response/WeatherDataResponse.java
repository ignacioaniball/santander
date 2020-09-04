package com.meetups.model.response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.jsonwebtoken.lang.Objects;
import io.swagger.annotations.ApiModelProperty;

public class WeatherDataResponse {
	
	@ApiModelProperty
	public String cod;
	public String message;
	public String cnt;
	public List<WeatherInformation> list;
	public JSONObject city;
	
	
	public List<WeatherInformation> getList() {
		return list;
	}
	public void setList(List<WeatherInformation> list) {
		this.list = list;
	}
	public JSONObject getCity() {
		return city;
	}
	public void setCity(JSONObject city) {
		this.city = city;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	public Object getNotificationCount() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCurrentWeatherString(List<WeatherInformation> list2) {
		// TODO Auto-generated method stub
		
	}

}
