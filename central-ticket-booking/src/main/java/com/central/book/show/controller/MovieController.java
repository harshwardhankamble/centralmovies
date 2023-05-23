package com.central.book.show.controller;

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

import com.central.book.show.aop.AccessControl;
import com.central.book.show.constant.Constants;
import com.central.book.show.dto.MovieDto;
import com.central.book.show.dto.ReviewDto;
import com.central.book.show.entity.Movie;
import com.central.book.show.entity.Review;
import com.central.book.show.exception.GlobalExceptionResponseEntity;
import com.central.book.show.service.MovieService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movie")
@Import(GlobalExceptionResponseEntity.class)
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ModelMapper modelMapper;

	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER})
	@GetMapping
	public ResponseEntity<List<MovieDto>> getAllMovies(@RequestParam Integer userId) {
		
		List<Movie> movies = movieService.getAllMovies();
		
		return ResponseEntity.ok().body(convertEntityToDtos(movies));
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer movieId) {
		
		Movie movie = movieService.getMovieById(movieId);
		return ResponseEntity.ok().body(movie != null ? convertEntityToDto(movie) : null);
	}
	
	@PostMapping
	public void addNewMovie(@RequestBody MovieDto movieDto) {
		
		Movie movie = convertDtoToEntity(movieDto);
		movieService.addNewMovie(movie);
	}
	
	@PutMapping("/{movieId}")
	public void updateMovie(@RequestBody MovieDto movieDto, @PathVariable Integer movieId) {
		
		movieDto.setMovieId(movieId);
		Movie movie = convertDtoToEntity(movieDto);
		movieService.updateMovie(movie);
	}
	
	@PostMapping("/{movieId}/review")
	public void addReviewToMovie(@RequestBody ReviewDto reviewDto, @PathVariable Integer movieId) {
		
		Review review = new Review();
		review.setReviewComment(reviewDto.getReviewComment());
		review.setReviewDateTime(new Date());
		
		movieService.addNewReviewCommentToMovie(review, movieId);
	}
	
	private MovieDto convertEntityToDto(Movie movie) {
		
		return modelMapper.map(movie, MovieDto.class);
	}
	
	private List<MovieDto> convertEntityToDtos(List<Movie> movies) {
		
		return movies.stream().map(this::convertEntityToDto).toList();
	}
	
	private Movie convertDtoToEntity(MovieDto movieDto) {
		
		return modelMapper.map(movieDto, Movie.class);
	}
}
