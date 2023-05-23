package com.central.book.show.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ScreenDto {

	private Integer screenId;
	
	private String screenName;
	
	private Integer totalSeats;
	
	private Double screenTicketRate;

	private Set<SeatDto> seats = new HashSet<>();
}
