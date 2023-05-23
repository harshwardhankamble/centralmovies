package com.central.movie.book.show.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.PaymentDto;
import com.central.book.common.entity.Payment;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.movie.book.show.aop.AccessControl;
import com.central.movie.book.show.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v3/payments")
@Tag(name = "Payment")
@Import(GlobalExceptionResponseEntity.class)
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER, Constants.MANAGER})
	@PostMapping
	public void makeBookingPayment(@RequestParam Integer userId, @RequestBody PaymentDto paymentDto) {
	
		Payment payment = convertEntityToDto(paymentDto);
		
		paymentService.makePaymentForBooking(payment, paymentDto);
	}
	
	private Payment convertEntityToDto(PaymentDto paymentDto) {
		
		return modelMapper.map(paymentDto, Payment.class);
	}
}
