package com.meli.galaxy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteorologicalConditionsDto {

	private String weather;
	private String day;
	private String days;
	private String peak;
	
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getPeak() {
		return peak;
	}
	public void setPeak(String peak) {
		this.peak = peak;
	}
	
	public MeteorologicalConditionsDto(){}
	
	public MeteorologicalConditionsDto(String weather, long days){
		this.weather = weather;
		this.days = String.valueOf(days);
	}
	
}
