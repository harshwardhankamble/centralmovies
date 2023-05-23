package com.central.book.common.dto;

import java.util.Date;

import com.central.book.common.util.CentralMovieUtil;

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
