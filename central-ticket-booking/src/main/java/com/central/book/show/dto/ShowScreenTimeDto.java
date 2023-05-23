package com.central.book.show.dto;

import com.central.book.show.enums.ShowStatus;

import lombok.Data;

@Data
public class ShowScreenTimeDto {

	private Integer screenId;
	
	private String showDateTime;
	
	private ShowStatus showStatus;
}
