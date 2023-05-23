package com.central.book.common.dto;

import com.central.book.common.entity.Booking;
import com.central.book.common.enums.SeatStatus;

import lombok.Data;

@Data
public class SeatDto {

	private Integer seatId;
	
	private SeatStatus seatStatus;
	
	private Booking booking;
}
