package com.central.book.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CentralMovieUtil {
	
	public static Date convertStringToDate(String dateString, String format) {
		
		try {
			if (dateString != null) {
				return new SimpleDateFormat(format).parse(dateString);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static String convertDateToString(Date date, String format) {
		if (date != null) {
			return new SimpleDateFormat(format).format(date);
		}
		return null;
	}

}
