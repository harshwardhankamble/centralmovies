package com.central.movie.book.show.service.impl;

import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.dto.PaymentDto;
import com.central.book.common.entity.Booking;
import com.central.book.common.entity.Payment;
import com.central.book.common.entity.Seat;
import com.central.book.common.entity.Transaction;
import com.central.book.common.entity.Wallet;
import com.central.book.common.enums.BookingStatus;
import com.central.book.common.enums.PaymentStatus;
import com.central.book.common.enums.SeatStatus;
import com.central.book.common.enums.TransactionStatus;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.exception.NotEfficientBalance;
import com.central.book.common.message.Message;
import com.central.movie.book.show.repository.BookingRepository;
import com.central.movie.book.show.repository.PaymentRepository;
import com.central.movie.book.show.repository.TransactionRepository;
import com.central.movie.book.show.repository.WalletRepository;
import com.central.movie.book.show.service.PaymentService;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	@Transactional
	public void makePaymentForBooking(Payment payment, PaymentDto paymentDto) {
		Booking booking = bookingRepository.findById(paymentDto.getBookingId()).orElse(null);
		
		if (booking == null) {
			throw new ContentNotFoundException(Message.formatMessage(Message.BOOKING_NOT_FOUND, paymentDto.getBookingId()));
		}
		payment.setBooking(booking);
		
		try {
			Wallet wallet = walletRepository.findByUserUserId(paymentDto.getUserId());
			if (wallet == null) {
				throw new ContentNotFoundException(Message.formatMessage(Message.WALLET_NOT_FOUND, paymentDto.getUserId()));
			}
			
			if (wallet.getBalance() < paymentDto.getTotalCost()) {
				throw new NotEfficientBalance(Message.formatMessage("No sufficient balance in User {0} wallet", paymentDto.getUserId()));
			}
			
			payment.setBooking(booking);
			booking.setBookingStatus(BookingStatus.BOOKED);
			
			for(Seat seat: booking.getSeats()) {
				seat.setSeatStatus(SeatStatus.BOOKED);
			}
			payment.setPaymentDateTime(new Date());
			
			payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
			
			Transaction transaction = new Transaction();
			transaction.setAmount(payment.getTotalCost());
			transaction.setPayment(payment);
			transaction.setPaymentType(payment.getPaymentType());
			transaction.setTransactionDate(new Date());
			transaction.setTransactionStatus(TransactionStatus.SUCCESS);
			transaction.setWallet(wallet);
			wallet.setBalance(wallet.getBalance() - payment.getTotalCost());
			bookingRepository.save(booking);
			walletRepository.save(wallet);
			paymentRepository.save(payment);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			booking.setSeats(new HashSet<>());
			booking.setBookingStatus(BookingStatus.UNPAID);
			bookingRepository.save(booking);
			
			payment.setBooking(booking);
			payment.setPaymentDateTime(new Date());
			
			payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
			paymentRepository.save(payment);
			
			throw e;
		}
		
	}

}
