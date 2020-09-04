package com.meetups.model.response;

import java.util.List;

import org.json.JSONObject;

public class WeatherInformation {
	
	public String dt;
	public JSONObject main;
	public List<WeatherDescription> weather;
	public JSONObject clouds;
	public JSONObject wind;
	public JSONObject sys;
	public String dt_txt;
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public JSONObject getMain() {
		return main;
	}
	public void setMain(JSONObject main) {
		this.main = main;
	}
	public List<WeatherDescription> getWeather() {
		return weather;
	}
	public void setWeather(List<WeatherDescription> weather) {
		this.weather = weather;
	}
	public JSONObject getClouds() {
		return clouds;
	}
	public void setClouds(JSONObject clouds) {
		this.clouds = clouds;
	}
	public JSONObject getWind() {
		return wind;
	}
	public void setWind(JSONObject wind) {
		this.wind = wind;
	}
	public JSONObject getSys() {
		return sys;
	}
	public void setSys(JSONObject sys) {
		this.sys = sys;
	}
	public String getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	
	

}
