package com.central.book.show.entity;

import java.util.Date;

import com.central.book.show.enums.PaymentStatus;
import com.central.book.show.enums.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;
	
	@OneToOne
	@JoinColumn(name = "bookingId")
	private Booking booking;
	
	private Double totalCost;
	
	private Date paymentDateTime;
	
	@Enumerated(EnumType.ORDINAL)
	private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.ORDINAL)
	private PaymentType paymentType;

}
