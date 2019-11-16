package com.meli.galaxy.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.galaxy.dto.WeatherDto;
import com.meli.galaxy.service.WeatherService;

@RestController
@RequestMapping("/galaxy")
public class GalaxyController {

	@Resource
	WeatherService weatherService;

	@RequestMapping(value = "/periods", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<WeatherDto> getAllPeriods(HttpServletRequest request) throws IOException {
		return weatherService.getWeather();
	}
	

	@RequestMapping(value = "/weather/day/{day}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public WeatherDto getWeatherByDay(@PathVariable String day, HttpServletRequest request) throws IOException {
		return weatherService.getWeatherByDay(day);
	}
}
