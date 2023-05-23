package com.central.book.show.entity;

import com.central.book.show.enums.SeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "screen_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

	@Id
	private Integer seatId;
	
	@Enumerated(EnumType.ORDINAL)
	private SeatStatus seatStatus;
	
	@ManyToOne
	@JoinColumn(name = "bookingId")
	private Booking booking;
	
	@ManyToOne
	@JoinColumn(name = "showScreenTimeId.screen.screenId")
	@JoinColumn(name = "showScreenTimeId.showDateTime")
	private ShowScreenTime showScreenTime;
	
}
