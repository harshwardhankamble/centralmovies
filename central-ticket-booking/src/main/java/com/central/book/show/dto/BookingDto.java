package com.central.book.show.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.central.book.show.util.CentralMovieUtil;

import lombok.Data;

@Data
public class BookingDto {
	
	private Integer bookingId;

	private String emailId;
	
	private Integer totalSeats;
	
	private Integer screenId;
	
	private String showDateTimeString;
	
	private Set<Integer> seatIds = new HashSet<>();
	
	public Date getShowDateTime(String format) {
		
		return CentralMovieUtil.convertStringToDate(showDateTimeString, format);
	}
	
}
