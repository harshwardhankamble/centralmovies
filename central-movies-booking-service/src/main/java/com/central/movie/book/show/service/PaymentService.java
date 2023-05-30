package com.central.movie.book.show.service;

import com.central.book.common.dto.PaymentDto;
import com.central.book.common.entity.Payment;
import com.central.book.common.entity.Wallet;

public interface PaymentService {

	public void makePaymentForBooking(Payment payment, PaymentDto paymentDto);

	public Wallet getWalletInfo(Integer userId);
	
}
