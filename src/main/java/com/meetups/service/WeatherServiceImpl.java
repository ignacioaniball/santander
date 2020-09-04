package com.meetups.service;

import java.rmi.ServerException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Value;
import com.meetups.adapter.AdapterFactory;
import com.meetups.adapter.WeatherAdapter;
import com.meetups.model.DTO.GetWeatherDataDTO;
import com.meetups.model.response.WeatherDataResponse;
import com.meetups.model.response.WeatherInformation;

@Service
public class WeatherServiceImpl implements WeatherService {

	
	@Autowired
	private AdapterFactory adapterFactory;
	
	private WeatherAdapter adapter;
	
	@Value("${weather.default.adapter}")
	private String defaultAdapter = "adapterWeatherbit";
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	public WeatherInformation getWeatherData(GetWeatherDataDTO weatherDataDTO) throws Exception {
		
		LOGGER.info("Get Weather service. Default adapter: {+defaultAdapter}", defaultAdapter);
		
		try {
			initAdapter();
						
			WeatherInformation weatherDataResponse = adapter.getWeather(weatherDataDTO);
			
			return weatherDataResponse;
			
		} catch ( Exception  e) {

			LOGGER.error("Error retrieving weather from adapter.");
			throw e;
		}
	}
	
	protected void initAdapter() throws Exception {
		if(adapter == null) {
			adapter = adapterFactory.getAdapter(defaultAdapter);
		}
	}
	
	protected WeatherDataResponse setWeather(WeatherDataResponse weatherDataResponse) {
		
		return weatherDataResponse;
	}
}
