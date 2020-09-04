package com.meetups.service;

import com.meetups.model.DTO.GetWeatherDataDTO;
import com.meetups.model.response.WeatherDataResponse;
import com.meetups.model.response.WeatherInformation;

public interface WeatherService {
	
	public WeatherInformation getWeatherData(GetWeatherDataDTO weatherDataDTO) throws Exception;

}