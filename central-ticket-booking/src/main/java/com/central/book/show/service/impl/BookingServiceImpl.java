package com.central.book.show.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.show.dto.BookingDto;
import com.central.book.show.entity.Booking;
import com.central.book.show.entity.Screen;
import com.central.book.show.entity.Seat;
import com.central.book.show.entity.ShowScreenTime;
import com.central.book.show.entity.User;
import com.central.book.show.enums.BookingStatus;
import com.central.book.show.enums.SeatStatus;
import com.central.book.show.exception.ContentNotFoundException;
import com.central.book.show.exception.SeatIsNotVaccant;
import com.central.book.show.messages.Message;
import com.central.book.show.repository.BookingRepository;
import com.central.book.show.repository.SeatRepository;
import com.central.book.show.repository.ShowScreenTimeRepository;
import com.central.book.show.repository.UserRepository;
import com.central.book.show.service.BookingService;
import com.central.book.show.service.ScreenService;
import com.central.book.show.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private ShowScreenTimeRepository showScreenTimeRepository;

	@Override
	@Transactional
	public void bookShow(Booking booking, BookingDto bookingDto) {

		User user = null;
		
		// check user name or email if found attached to booking
		if (bookingDto.getEmailId() != null && !bookingDto.getEmailId().equals("")
				&& !userService.checkUserWithEmail(bookingDto.getEmailId())) {
			throw new ContentNotFoundException(Message.formatMessage(Message.USER_NOT_FOUND, "Email ID", bookingDto.getEmailId()));
		} else {
			user = userRepository.findOneByEmailId(bookingDto.getEmailId());
		}
		
		booking.setUser(user);
		booking.setBookingDateTime(new Date());
		booking.setBookingStatus(BookingStatus.PROCESSING);
		
		booking = bookingRepository.save(booking);
		
		// check if screen is valid and calculate fare
		Screen screen = screenService.getScreenById(bookingDto.getScreenId());
		booking.setTotalCost(booking.getTotalSeats() * screen.getScreenTicketRate());
		
		for(Integer seatId: bookingDto.getSeatIds()) {
			if (seatId < 1 || seatId > screen.getTotalSeats()) {
				throw new ContentNotFoundException(Message.PLEASE_ENTER_VALID_SEAT_NUMBER);
			}
		}
		
		Set<Seat> seats = seatRepository.getSeatsBySeatIdAndShowScreenTime(bookingDto.getSeatIds(), screen.getScreenId(), bookingDto.getShowDateTime("dd-mm-yyyy HH:MM"));				
		if (!seats.isEmpty()) {
			throw new SeatIsNotVaccant(Message.SEAT_NOT_VACCANT, getSeatIds(seats));
		}
		
		// check if show date time is valid
		
		ShowScreenTime showScreenTime = showScreenTimeRepository.findByShowScreenTimeIdScreenScreenIdAndShowScreenTimeIdShowDateTime(screen.getScreenId(), bookingDto.getShowDateTime("dd-mm-yyyy HH:MM"));
		
		seats = new HashSet<>();
		for(Integer seatId: bookingDto.getSeatIds()) {
			Seat seat = new Seat();
			seat.setSeatId(seatId);
			seat.setBooking(booking);
			seat.setSeatStatus(SeatStatus.INPROCESSING);
			seat.setShowScreenTime(showScreenTime);
			seats.add(seat);
		}
		
		showScreenTime.setSeats(seats);		
		showScreenTimeRepository.save(showScreenTime);
		
		booking.setShowScreenTime(showScreenTime);
		booking.setShow(showScreenTime.getShow());
		booking.setBookingStatus(BookingStatus.UNPAID);
		bookingRepository.save(booking);
	}
	
	private String getSeatIds(Set<Seat> seats) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Seat seat: seats) {
			stringBuilder.append(seat.getSeatId());
		}
		
		return String.valueOf(stringBuilder);
	}

	@Override
	public List<Booking> getAllBookings() {
		
		return bookingRepository.findAll();
	}

}
