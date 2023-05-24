package com.central.movie.book.show.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.central.book.common.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	public List<Booking> findByUserUserId(Integer userId);
	
	@Query("from Booking where showScreenTime.showScreenTimeId.screen.theatre.theatreId = :theatreId")
	public List<Booking> findBookingsByTheatreId(@Param("theatreId") Integer theatreId);
}
