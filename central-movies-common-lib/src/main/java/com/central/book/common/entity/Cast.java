package com.central.book.common.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
