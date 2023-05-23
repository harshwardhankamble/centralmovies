package com.central.book.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theatre_screens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer screenId;
	
	private String screenName;
	
	private Integer totalSeats;
	
	private Double screenTicketRate;
}
