package com.meetups.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class WeatherDataRequest {

	@ApiModelProperty
	@NotBlank
	private String city;

	public void setCity(String city) {
		this.city = city;
	}

	public String getcity() {
		return null;
	}

}
