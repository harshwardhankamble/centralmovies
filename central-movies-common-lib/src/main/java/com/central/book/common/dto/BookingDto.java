package com.central.book.common.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.central.book.common.enums.BookingStatus;
import com.central.book.common.util.CentralMovieUtil;

import lombok.Data;

@Data
public class BookingDto {
	
	private Integer bookingId;

	private Integer userId;
	
	private Integer totalSeats;
	
	private Integer screenId;
	
	private String showDateTimeString;
	
	private BookingStatus bookingStatus;
	
	private Double totalCost;
	
	private Set<Integer> seatIds = new HashSet<>();
	
	public Date getShowDateTime(String format) {
		
		return CentralMovieUtil.convertStringToDate(showDateTimeString, format);
	}
	
}
