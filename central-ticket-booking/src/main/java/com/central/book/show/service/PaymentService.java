package com.central.book.show.service;

import com.central.book.show.dto.PaymentDto;
import com.central.book.show.entity.Payment;

public interface PaymentService {

	public void makePaymentForBooking(Payment payment, PaymentDto paymentDto);
	
}
