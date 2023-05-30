package com.central.movie.theatre.service;

import java.util.Date;
import java.util.List;

import com.central.book.common.entity.Seat;

public interface SeatService {

	public List<Seat> getBookedSeats(Integer screenId, Date showTime);
}
