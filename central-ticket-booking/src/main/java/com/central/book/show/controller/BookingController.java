package com.central.book.show.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.show.aop.AccessControl;
import com.central.book.show.constant.Constants;
import com.central.book.show.dto.BookingDto;
import com.central.book.show.entity.Booking;
import com.central.book.show.entity.Seat;
import com.central.book.show.exception.GlobalExceptionResponseEntity;
import com.central.book.show.service.BookingService;
import com.central.book.show.util.CentralMovieUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Booking")
@Import(GlobalExceptionResponseEntity.class)
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER})
	@PostMapping
	public void bookShow(@RequestParam Integer userId, @RequestBody BookingDto bookingDto) {
		
		Booking booking = convertDtoToEntity(bookingDto);
		
		bookingService.bookShow(booking, bookingDto);
	}
	
	@GetMapping
	public ResponseEntity<List<BookingDto>> getAllBookings() {
		
		return ResponseEntity.ok(convertEntityToDtos(bookingService.getAllBookings()));
	}
	
	private Booking convertDtoToEntity(BookingDto bookingDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(bookingDto, Booking.class);
	}
	
	private List<BookingDto> convertEntityToDtos(List<Booking> bookings) {
		
		return bookings.stream().map(this::convertEntityToDto).toList();
	}
	
	private BookingDto convertEntityToDto(Booking booking) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);
		bookingDto.setEmailId(booking.getUser().getEmailId());
		if (booking.getShowScreenTime() != null) {
			bookingDto.setScreenId(booking.getShowScreenTime().getShowScreenTimeId().getScreen().getScreenId());
			bookingDto.setShowDateTimeString(CentralMovieUtil.convertDateToString(booking.getShowScreenTime().getShowScreenTimeId().getShowDateTime(), "dd-mm-yyyy HH:MM"));
		}
		Set<Integer> seatIds = new HashSet<>();
		for(Seat seat: booking.getSeats()) {
			seatIds.add(seat.getSeatId());
		}
		return bookingDto;
	}
}
