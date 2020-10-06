package com.hibernate.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	
	public static Date stringToDate(String stringDate) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(stringDate);
		return date;
	}
	
	public static String dateToString(Date date){
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = formatter.format(date);
		return formattedDate;
	}
	
}
