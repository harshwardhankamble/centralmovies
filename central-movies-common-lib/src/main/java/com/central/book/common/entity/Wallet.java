package com.central.book.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment_wallet")
@Data
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer walletId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private Double balance;
	
	private Date createDate;
	
	private Date lastTransactionDate;
	
	private boolean isActive;
	
	@OneToMany(mappedBy = "wallet")
	private List<Transaction> transactions = new ArrayList<>();
}
