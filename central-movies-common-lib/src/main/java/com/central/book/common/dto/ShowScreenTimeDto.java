package com.central.book.common.dto;

import com.central.book.common.enums.ShowStatus;

import lombok.Data;

@Data
public class ShowScreenTimeDto {

	private Integer screenId;
	
	private String showDateTime;
	
	private String screenName;
	
	private String showDate;
	
	private String showTime;
	
	private ShowStatus showStatus;
}
