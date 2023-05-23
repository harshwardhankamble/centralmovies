package com.central.book.show.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDto {
	
	private Integer reviewId;
	
	private String reviewComment;
	
	private Date reviewDateTime;

}
