package com.meetups.adapter;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdapterFactory {

	
	@Autowired
	@Qualifier("adapterWeatherbit")
	public  WeatherAdapter adapterWheatherbit;
	
	public WeatherAdapter getAdapter(String adapterName) throws Exception {
		WeatherAdapterEnum adapterFromNameEnum = WeatherAdapterEnum.valueOf(adapterName.toUpperCase(Locale.getDefault()));
		switch (adapterFromNameEnum) {
		case ADAPTERWEATHERBIT:
			
			return adapterWheatherbit;

		default:
			throw new Exception("Error obtaining adapter, adapter name should not be null");
		} 
	}
}
