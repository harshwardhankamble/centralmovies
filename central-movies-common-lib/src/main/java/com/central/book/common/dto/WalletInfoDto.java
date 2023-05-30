package com.central.book.common.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class WalletInfoDto {

	private Integer walletId;

	private String userName;

	private Double balance;

	private String createDate;

	private List<TransactionDto> transactions = new ArrayList<>();

	public void addTransaction(TransactionDto transactionDto) {
		transactions.add(transactionDto);
	}
}
