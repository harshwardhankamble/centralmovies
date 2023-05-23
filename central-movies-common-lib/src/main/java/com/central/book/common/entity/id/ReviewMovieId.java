package com.central.book.common.entity.id;

import java.io.Serializable;

import com.central.book.common.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class ReviewMovieId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reviewId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movieId")
	private Movie movie;

	@JsonIgnore
	public Movie getMovie() {
		return movie;
	}
	
}
