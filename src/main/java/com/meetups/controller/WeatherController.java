package com.meetups.controller;

import java.rmi.ServerError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetups.model.DTO.GetWeatherDataDTO;
import com.meetups.model.request.WeatherDataRequest;
import com.meetups.model.response.WeatherDataResponse;
import com.meetups.model.response.WeatherInformation;
import com.meetups.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "weather" })
@RestController
@RequestMapping("/v1")
public class WeatherController {

//	private WeatherConfiguration configuration;

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WeatherController.class);

	private UrlValidator urlValidator;
	
	@Autowired
	private WeatherService service;

//	@Autowired
//	public WeatherController(WeatherConfiguration configuration) {
//		this.configuration = configuration;
//		urlValidator = new UrlValidator();
//	}

	@GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieve weather", notes = "Weather information for a given city.", response = WeatherDataResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	@Secured({ "hasRole('ROLE_USER')" })
	public ResponseEntity<WeatherInformation> getWeather(HttpServletRequest request,
			@ApiParam(name = "city", value = "The name of the city", required = true) @RequestParam(required = true) String city,
			@ApiParam(name = "meetupDate", value = "The date of the meetup, required = true") @RequestParam(required = true) String meetupDate) throws Exception {
		if (StringUtils.isEmpty(city)) {
			throw new ServerError("city parameter can't be empty", null);
		}

		if(StringUtils.isEmpty(meetupDate)) {
			throw new ServerError("meetup parameter can't be empty", null);
		}
		GetWeatherDataDTO weatherDataDTO = new GetWeatherDataDTO();
		weatherDataDTO.setCity(city);
		weatherDataDTO.setMeetupDate(meetupDate);

		WeatherInformation weatherInformation = service.getWeatherData(weatherDataDTO);

		return new ResponseEntity<>(weatherInformation, HttpStatus.OK);

	}

}
