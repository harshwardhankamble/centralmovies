package com.central.book.common.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.central.book.common.enums.BookingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "show_booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user; 
	
	private Integer totalSeats;
	
	private Date bookingDateTime;
	
	private Double totalCost;
	
	@Enumerated(EnumType.ORDINAL)
	private BookingStatus bookingStatus;
	
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Seat> seats = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "showId")
	private Show show;
	
	@OneToOne
	@JoinColumn(name = "showScreenTimeId.screen.screenId", referencedColumnName = "screenId")
	@JoinColumn(name = "showScreenTimeId.showDateTime", referencedColumnName = "showDateTime")
	private ShowScreenTime showScreenTime;
}
