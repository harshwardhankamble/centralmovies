package com.central.book.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.central.book.common.enums.Genre;
import com.central.book.common.enums.MovieStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;

	private String movieName;

	private String posterImage;

	private String language;

	private Float totalHours;

	private String movieDescription;

	private String trailerUrl;

	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	@Enumerated(EnumType.ORDINAL)
	private MovieStatus movieStatus;

	@Enumerated(EnumType.ORDINAL)
	private Genre genre;

	@Min(value = 1)
	@Max(value = 5)
	private Integer movieRating;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movies_casts", joinColumns = { @JoinColumn(name = "castId") }, inverseJoinColumns = {
			@JoinColumn(name = "movieId") })
	private List<Cast> casts = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "movie")
	private List<Review> reviews = new ArrayList<>();

}
