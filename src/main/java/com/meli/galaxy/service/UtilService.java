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
	
	public Date getDateFrom(Date date) {
		Date dateFrom = null;
		if(date != null){
			Calendar calFrom = Calendar.getInstance();
			calFrom.setTime(date);
			calFrom.set(Calendar.HOUR, 0);
			calFrom.set(Calendar.MINUTE, 0);
			calFrom.set(Calendar.SECOND, 0);
			calFrom.set(Calendar.MILLISECOND, 0);
			calFrom.set(Calendar.AM_PM, Calendar.AM);
			dateFrom = calFrom.getTime();
		}
		return dateFrom;
	}
	
	public Date getDateTo(Date date) {
		Date dateTo = null;
		if(date != null){
			Calendar calTo = Calendar.getInstance();
			calTo.setTime(date);
			calTo.set(Calendar.HOUR, 23);
			calTo.set(Calendar.MINUTE, 59);
			calTo.set(Calendar.SECOND, 59);
			calTo.set(Calendar.MILLISECOND, 999);
			calTo.set(Calendar.AM_PM, Calendar.AM);
			dateTo =  calTo.getTime();
		}
		
		return dateTo;
	}
	
	public Date addDay(Date date, String day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(day));  
		return  calendar.getTime();
	}
}
