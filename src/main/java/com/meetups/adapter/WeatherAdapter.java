package com.meetups.adapter;

import com.meetups.model.DTO.GetWeatherDataDTO;
import com.meetups.model.response.WeatherDataResponse;
import com.meetups.model.response.WeatherInformation;

public interface WeatherAdapter {
	
	/**
	 * Retrieves the weather for a given day.
	 */
	 WeatherInformation getWeather(GetWeatherDataDTO weatherDataDTO);
}
