package com.central.book.common.dto;

import com.central.book.common.enums.PaymentType;
import com.central.book.common.enums.TransactionStatus;

import lombok.Data;

@Data
public class TransactionDto {

	private Integer transactionId;

	private TransactionStatus transactionStatus;

	private String transactionDate;

	private Double amount;

	private PaymentType paymentType;
}
