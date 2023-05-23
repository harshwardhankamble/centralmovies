package com.central.book.show.dto;

import java.util.Date;

import com.central.book.show.util.CentralMovieUtil;

import lombok.Data;

@Data
public class UserDto {

	private Integer userId;
	
	private String userName;
	
	private String emailId;
	
	private String password;
	
	private String mobileNumber;
	
	private String roleName;
	
	private String dob;
	
	public Date getDateOfBirth(String format) {
		return CentralMovieUtil.convertStringToDate(dob, format);
	}
}
