package com.central.movie.book.show.service;

import com.central.book.common.dto.PaymentDto;
import com.central.book.common.entity.Payment;

public interface PaymentService {

	public void makePaymentForBooking(Payment payment, PaymentDto paymentDto);
	
}
