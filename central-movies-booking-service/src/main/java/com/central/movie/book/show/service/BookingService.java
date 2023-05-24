package com.central.movie.book.show.service;

import java.util.List;

import com.central.book.common.dto.BookingDto;
import com.central.book.common.entity.Booking;

public interface BookingService {

	public void bookShow(Booking booking, BookingDto bookingDto);
	
	public List<Booking> getAllBookings();
	
	public List<Booking> getBookingHistory(Integer userId);
	
	public List<Booking> getAllBookingsInTheatre(Integer theatreId);
	
}
