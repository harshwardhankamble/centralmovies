package com.central.movie.theatre.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.central.book.common.dto.BookingDto;

@FeignClient(name = "CENTRAL-MOVIES-BOOKING-SERVICE", url = "localhost:8083/api/v3/bookings")
public interface BookingServiceClient {

	@GetMapping("/theatre/{theatreId}")
	List<BookingDto> getAllBookingsInTheatre(@RequestParam Integer userId, @PathVariable Integer theatreId);
}
