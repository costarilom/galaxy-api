package com.meli.galaxy.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.galaxy.dto.MeteorologicalConditionsDto;
import com.meli.galaxy.service.MeteorologicalConditionsService;

@RestController
@RequestMapping("/galaxy")
public class GalaxyController {

	@Resource
	MeteorologicalConditionsService meteorologicalConditionsService;


	@RequestMapping(value = "/periods", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<MeteorologicalConditionsDto> getAllPeriods(HttpServletRequest request) throws IOException {
		return meteorologicalConditionsService.getPeriods();
	}
	
	@RequestMapping(value = "/weather/day/{day}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public MeteorologicalConditionsDto getWeatherByDay(@PathVariable String day, HttpServletRequest request) throws IOException {
		return meteorologicalConditionsService.getWeatherByDay(day);
	}
	
	@RequestMapping(value = "/periods/year/{year}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<MeteorologicalConditionsDto> getPeriodsByYear(@PathVariable String year, HttpServletRequest request) throws IOException {
		return meteorologicalConditionsService.getPeriodsByYear(year);
	}
	
	@RequestMapping(value = "/periods/code/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public MeteorologicalConditionsDto getPeriodsByCode(@PathVariable String code, HttpServletRequest request) throws IOException {
		return meteorologicalConditionsService.getPeriodsByCode(code);
	}
	
	@RequestMapping(value = "/clean", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Map<String, String> clean(HttpServletRequest request) throws IOException {
		return meteorologicalConditionsService.clean();
	}
	
	@RequestMapping(value = "/initialize", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Map<String, String> initialize(HttpServletRequest request) throws IOException {
		return meteorologicalConditionsService.initialize();
	}
}
