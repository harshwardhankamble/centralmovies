package com.central.book.common.dto;

import lombok.Data;

@Data
public class ScreenDto {

	private Integer screenId;
	
	private String screenName;
	
	private Integer totalSeats;
	
	private Double screenTicketRate;
}
