package com.central.book.show.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ShowDto {

	private Integer showId;

	private String showName;
	
	private Integer movieId;
	
	private String movieName;

	private Date startingDate;
	
	private List<ShowScreenTimeDto> showTime = new ArrayList<>();
	
}
