package com.central.book.show.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_casts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer castId;
	
	private String castName;
	
	private String castDescription;
	
	@Min(value = 1)
	@Max(value = 10)
	private Integer castRating;

}
