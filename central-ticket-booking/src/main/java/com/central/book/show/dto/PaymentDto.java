package com.central.book.show.dto;

import com.central.book.show.enums.PaymentType;

import lombok.Data;

@Data
public class PaymentDto {
	
	private Integer paymentId;
	
	private Integer bookingId;
	
	private Integer userId;
	
	private Double totalCost;
	
	private PaymentType paymentType;
	
	private Integer cardNumber;
	
	private String expirationDate;
	
	private Integer cvv;
	
	private String upiId;

}
