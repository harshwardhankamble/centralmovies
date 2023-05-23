package com.central.book.show.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.show.dto.PaymentDto;
import com.central.book.show.entity.Payment;
import com.central.book.show.exception.GlobalExceptionResponseEntity;
import com.central.book.show.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment")
@Import(GlobalExceptionResponseEntity.class)
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public void makeBookingPayment(@RequestBody PaymentDto paymentDto) {
	
		Payment payment = convertEntityToDto(paymentDto);
		
		paymentService.makePaymentForBooking(payment, paymentDto);
	}
	
	private Payment convertEntityToDto(PaymentDto paymentDto) {
		
		return modelMapper.map(paymentDto, Payment.class);
	}
}
