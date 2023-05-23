package com.central.book.common.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.central.book.common.enums.Genre;
import com.central.book.common.enums.MovieStatus;

import lombok.Data;

@Data
public class MovieDto {

	private Integer movieId;

	private String movieName;

	private String posterImage;

	private String language;

	private Float totalHours;

	private String movieDescription;

	private String trailerUrl;

	private Date releaseDate;

	private MovieStatus movieStatus;

	private Genre genre;

	private Integer movieRating;

	private List<CastDto> casts = new ArrayList<>();
	
	private List<ReviewDto> reviews = new ArrayList<>();
}
