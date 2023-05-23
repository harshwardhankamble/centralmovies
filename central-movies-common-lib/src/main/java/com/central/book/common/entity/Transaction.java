package com.central.book.common.entity;

import java.util.Date;

import com.central.book.common.enums.PaymentType;
import com.central.book.common.enums.TransactionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "wallet_transactions")
@Data
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	
	@ManyToOne
	@JoinColumn(name = "walletId")
	private Wallet wallet;

	@Enumerated(EnumType.ORDINAL)
	private TransactionStatus transactionStatus;
	
	private Date transactionDate;
	
	@OneToOne
	@JoinColumn(name = "paymentId")
	private Payment payment;
	
	private Double amount;
	
	@Enumerated(EnumType.ORDINAL)
	private PaymentType paymentType;
}
