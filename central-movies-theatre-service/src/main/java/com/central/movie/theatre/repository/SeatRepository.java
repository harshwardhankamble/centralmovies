package com.central.movie.theatre.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.central.book.common.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	@Query("from Seat where showScreenTime.showScreenTimeId.screen.screenId = :screenId AND showScreenTime.showScreenTimeId.showDateTime = :showTime")
	public List<Seat> findByBookedSeats(@Param("screenId") Integer screenId, @Param("showTime") Date showTime);
}
