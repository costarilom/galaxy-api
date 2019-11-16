package com.meli.galaxy.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	public Integer gerCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	public Integer getYearByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	public Date stringToDate(String dateStr){
		Date result = null;
		if(dateStr != null){
			try {
				result = formatter.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
