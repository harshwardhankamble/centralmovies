package com.central.book.show.service;

import java.util.List;

import com.central.book.show.dto.BookingDto;
import com.central.book.show.entity.Booking;

public interface BookingService {

	public void bookShow(Booking booking, BookingDto bookingDto);
	
	public List<Booking> getAllBookings();
	
}
