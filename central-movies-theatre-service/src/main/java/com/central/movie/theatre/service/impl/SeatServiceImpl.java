package com.central.movie.theatre.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Seat;
import com.central.book.common.enums.SeatStatus;
import com.central.movie.theatre.repository.SeatRepository;
import com.central.movie.theatre.service.ScreenService;
import com.central.movie.theatre.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private ScreenService screenService;

	@Override
	public List<Seat> getBookedSeats(Integer screenId, Date showTime) {
		int totalSeats = screenService.getTotalNumberOfSeatsByScreenId(screenId);
		List<Seat> seats = seatRepository.findByBookedSeats(screenId, showTime);
		List<Integer> seatIntegers = new ArrayList<>();
		for (Seat seat : seats) {
			seatIntegers.add(seat.getSeatId());
		}

		for (int i = 1; i <= totalSeats; i++) {
			if (!seatIntegers.contains(i)) {
				Seat seat = new Seat(i, SeatStatus.VACCANT, null, null);
				seats.add(seat);
			}
		}
		return seats;
	}

}
