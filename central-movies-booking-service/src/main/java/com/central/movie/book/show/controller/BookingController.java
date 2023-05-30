package com.central.movie.book.show.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.BookingDto;
import com.central.book.common.entity.Booking;
import com.central.book.common.entity.Seat;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.book.common.util.CentralMovieUtil;
import com.central.movie.book.show.aop.AccessControl;
import com.central.movie.book.show.service.BookingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v3/bookings")
@Tag(name = "Booking")
@Import(GlobalExceptionResponseEntity.class)
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER, Constants.MANAGER})
	@PostMapping
	public void bookShow(@RequestParam Integer userId, @RequestBody BookingDto bookingDto) {
		
		Booking booking = convertDtoToEntity(bookingDto);
		
		bookingService.bookShow(booking, bookingDto);
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER})
	@GetMapping
	public ResponseEntity<List<BookingDto>> getAllBookings(@RequestParam Integer userId) {
		
		return ResponseEntity.ok(convertEntityToDtos(bookingService.getAllBookings()));
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER, Constants.CUSTOMER})
	@GetMapping("/history")
	public ResponseEntity<List<BookingDto>> getBookingHistory(@RequestParam Integer userId) {
		
		return ResponseEntity.ok(convertEntityToDtos(bookingService.getBookingHistory(userId)));
	}
	
	@AccessControl(roles = {Constants.MANAGER})
	@GetMapping("/theatre/{theatreId}")
	public List<BookingDto> getAllBookingsInTheatre(@RequestParam Integer userId, @PathVariable Integer theatreId) {
		
		return convertEntityToDtos(bookingService.getAllBookingsInTheatre(theatreId));
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
		bookingDto.setUserId(booking.getUser().getUserId());
		if (booking.getShowScreenTime() != null) {
			bookingDto.setScreenId(booking.getShowScreenTime().getShowScreenTimeId().getScreen().getScreenId());
			bookingDto.setShowDateTimeString(CentralMovieUtil.convertDateToString(booking.getShowScreenTime().getShowScreenTimeId().getShowDateTime(), "dd-mm-yyyy HH:MM"));
		}
		Set<Integer> seatIds = new HashSet<>();
		for(Seat seat: booking.getSeats()) {
			seatIds.add(seat.getSeatId());
		}
		bookingDto.setSeatIds(seatIds);
		return bookingDto;
	}
}
