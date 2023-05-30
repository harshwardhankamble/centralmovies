package com.central.book.common.dto;

import com.central.book.common.enums.SeatStatus;

import lombok.Data;

@Data
public class SeatGetDto {

	private Integer seatId;

	private SeatStatus seatStatus;
}
