package com.central.movie.book.show.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	public List<Booking> findByUserUserId(Integer userId);
}
