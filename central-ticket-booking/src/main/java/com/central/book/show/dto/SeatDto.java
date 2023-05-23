package com.central.book.show.dto;

import com.central.book.show.entity.Booking;
import com.central.book.show.enums.SeatStatus;

import lombok.Data;

@Data
public class SeatDto {

	private Integer seatId;
	
	private SeatStatus seatStatus;
	
	private Booking booking;
}
