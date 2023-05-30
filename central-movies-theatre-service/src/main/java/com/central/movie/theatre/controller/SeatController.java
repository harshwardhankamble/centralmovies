package com.central.movie.theatre.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.SeatGetDto;
import com.central.book.common.entity.Seat;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.book.common.util.CentralMovieUtil;
import com.central.movie.theatre.aop.AccessControl;
import com.central.movie.theatre.service.SeatService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/seats")
@Tag(name = "Seat")
@Import(GlobalExceptionResponseEntity.class)
public class SeatController {

	@Autowired
	private SeatService seatService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER, Constants.CUSTOMER})
	@GetMapping
	public ResponseEntity<List<SeatGetDto>> getBookedSeats(@RequestParam Integer userId, @RequestParam Integer screenId,
			@RequestParam String showTime) {
		List<Seat> seats = seatService.getBookedSeats(screenId, CentralMovieUtil.convertStringToDate(showTime, "dd-MM-yyyy HH:mm"));
		
		return ResponseEntity.ok(convertEntityToDtos(seats));
	}
	
	private SeatGetDto convertEntityToDto(Seat seat) {
		
		return modelMapper.map(seat, SeatGetDto.class);
	}
	
	private List<SeatGetDto> convertEntityToDtos(List<Seat> seats) {
		
		return seats.stream().map(this::convertEntityToDto).toList();
	}
}
