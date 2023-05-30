package com.central.movie.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.MovieDto;
import com.central.book.common.dto.ReviewDto;
import com.central.book.common.entity.Movie;
import com.central.book.common.entity.Review;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.book.common.util.CentralMovieUtil;
import com.central.movie.aop.AccessControl;
import com.central.movie.service.MovieService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Movie")
@Import(GlobalExceptionResponseEntity.class)
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ModelMapper modelMapper;

	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER, Constants.CUSTOMER})
	@GetMapping
	public ResponseEntity<List<MovieDto>> getAllMovies(@RequestParam Integer userId) {
		
		List<Movie> movies = movieService.getAllMovies();
		
		return ResponseEntity.ok().body(convertEntityToDtos(movies));
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER, Constants.CUSTOMER})
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieDto> getMovieById(@RequestParam Integer userId, @PathVariable Integer movieId) {
		
		Movie movie = movieService.getMovieById(movieId);
		return ResponseEntity.ok().body(movie != null ? convertEntityToDto(movie) : null);
	}
	
	@AccessControl(roles = {Constants.ADMIN})
	@PostMapping
	public void addNewMovie(@RequestParam Integer userId, @RequestBody MovieDto movieDto) {
		
		Movie movie = convertDtoToEntity(movieDto);
		movieService.addNewMovie(movie);
	}
	
	@AccessControl(roles = {Constants.ADMIN})
	@PutMapping("/{movieId}")
	public void updateMovie(@RequestParam Integer userId, @RequestBody MovieDto movieDto, @PathVariable Integer movieId) {
		
		movieDto.setMovieId(movieId);
		Movie movie = convertDtoToEntity(movieDto);
		movieService.updateMovie(movie);
	}
	
	@AccessControl(roles = {Constants.ADMIN})
	@PostMapping("/{movieId}/review")
	public void addReviewToMovie(@RequestParam Integer userId, @RequestBody ReviewDto reviewDto, @PathVariable Integer movieId) {
		
		Review review = new Review();
		review.setReviewComment(reviewDto.getReviewComment());
		review.setReviewDateTime(new Date());
		
		movieService.addNewReviewCommentToMovie(review, movieId);
	}
	
	private MovieDto convertEntityToDto(Movie movie) {
		MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
		List<ReviewDto> reviewDtos = new ArrayList<>();
		for(Review review: movie.getReviews()) {
			ReviewDto reviewDto = new ReviewDto();
			reviewDto.setReviewId(review.getReviewId());
			reviewDto.setReviewComment(review.getReviewComment());
			reviewDto.setReviewDateTime(CentralMovieUtil.convertDateToString(review.getReviewDateTime(), "yyyy-MM-dd HH:mm"));
			reviewDtos.add(reviewDto);
		}
		movieDto.setReviews(reviewDtos);
		return movieDto;
	}
	
	private List<MovieDto> convertEntityToDtos(List<Movie> movies) {
		
		return movies.stream().map(this::convertEntityToDto).toList();
	}
	
	private Movie convertDtoToEntity(MovieDto movieDto) {
		
		return modelMapper.map(movieDto, Movie.class);
	}
}
