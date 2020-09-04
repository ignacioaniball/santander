package com.meetups.adapterImpl;

import java.rmi.ServerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.meetups.adapter.WeatherAdapter;
import com.meetups.model.DTO.GetWeatherDataDTO;
import com.meetups.model.response.WeatherDataResponse;
import com.meetups.model.response.WeatherInformation;

@Component
@Qualifier("adapterWeatherbit")
public class WeatherbitAdapter implements WeatherAdapter {

	private static final String WEATHER_API = "https://community-open-weather-map.p.rapidapi.com/forecast";
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAdapter.class);

	private static final String LOG_STRIGN_REQUEST_HEADERS = "";
	// Headers constants
	private static final String HEADER_AUTHORIZATION_STRING = "Authorization";
	private static final String HEADER_APPLICATION_JSON = "application/json";
	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String HEADER_RAPIAPI_HOST = "x-rapidapi-host";
	private static final String HEADER_RAPIAPI_KEY = "x-rapidapi-key";

	private String hostValue = "community-open-weather-map.p.rapidapi.com";
	private String KeyValue = "c92785a9e8msh2e0bfd2ae5b51a7p1bc37ajsn65e08ae037a0";
	
	
	public WeatherbitAdapter() {
	}

	@Override
	public WeatherInformation getWeather(GetWeatherDataDTO weatherDataDTO) {

		String weatherApiUrl = WEATHER_API;
		// Headers
		MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
		headersMap.add(HEADER_CONTENT_TYPE, HEADER_APPLICATION_JSON);
		headersMap.add(HEADER_RAPIAPI_HOST, hostValue);
		headersMap.add(HEADER_RAPIAPI_KEY, KeyValue);
				
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
				.queryParam("q", weatherDataDTO.getCity());

		LOGGER.debug(LOG_STRIGN_REQUEST_HEADERS);
		LOGGER.debug(headersMap.toString());

		HttpEntity<?> weatherRequestEntity = new HttpEntity<>(headersMap);

		RestTemplate restTemplate = createRestTemplate();
		ResponseEntity<WeatherDataResponse> weatherResponseEntity;

		try {
			weatherResponseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, weatherRequestEntity,
					WeatherDataResponse.class);
			if (weatherResponseEntity == null || weatherResponseEntity.getBody() == null) {
				LOGGER.error("Weather information is missing in the response.");
				throw new ServerException("Weather information is missing in the response.");
			}

			WeatherDataResponse weatherDataResponseBody = weatherResponseEntity.getBody();
			WeatherInformation weatherData = new WeatherInformation();
			System.out.println(weatherDataResponseBody.getList().getJSONObject(4));
//			for (WeatherInformation weather : weatherDataResponseBody.getList().getJSONObject(4))  {
//				if(weather.getDt_txt().contains(weatherDataDTO.getMeetupDate())) {
//					
//					
//					weatherData = parseGetWeatherResponse(weather);
//					
//				}
//			}

			LOGGER.debug("Weather response object: ");


			return weatherData;
		} catch (IllegalArgumentException | RestClientException | ServerException e) {
			LOGGER.error("Error retrieving weather: ", e.getMessage());
			throw new RestClientException(weatherApiUrl);
		}
	}

	private static WeatherInformation parseGetWeatherResponse(WeatherInformation weather) {
		WeatherInformation weatherData = new WeatherInformation();
		weatherData.setMain(weather.getMain());

		return weatherData;
	}

	public RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}

}
