package com.central.movie.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.dto.BookingDto;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.movie.theatre.proxy.BookingServiceClient;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/bookings")
@Tag(name = "Bookinh")
@Import(GlobalExceptionResponseEntity.class)
public class BookingController {

	@Autowired
	private BookingServiceClient bookingServiceClient;

	@GetMapping("/feign/theatre/{theatreId}")
	public ResponseEntity<List<BookingDto>> getAllBookingsInTheatre(@RequestParam Integer userId,
			@PathVariable Integer theatreId) {

		return ResponseEntity.ok(bookingServiceClient.getAllBookingsInTheatre(userId, theatreId));
	}

}
