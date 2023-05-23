package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
