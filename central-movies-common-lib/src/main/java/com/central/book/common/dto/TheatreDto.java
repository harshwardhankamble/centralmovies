package com.central.book.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TheatreDto {

	private Integer theatreId;
	
	private String theatreName;
	
	private String location;
	
	private List<ScreenDto> screens = new ArrayList<>();
}
