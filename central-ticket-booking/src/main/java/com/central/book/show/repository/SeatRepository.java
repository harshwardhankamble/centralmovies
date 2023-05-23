package com.central.book.show.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.central.book.show.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	@Query("from Seat where seatId IN (:seatIds) AND showScreenTime.showScreenTimeId.screen.screenId = :screenId "
			+ "AND showScreenTime.showScreenTimeId.showDateTime = :showDateTime")
	public Set<Seat> getSeatsBySeatIdAndShowScreenTime(@Param("seatIds") Set<Integer> seatIds, @Param("screenId") Integer screenId, 
			@Param("showDateTime") Date showDateTime);
}
