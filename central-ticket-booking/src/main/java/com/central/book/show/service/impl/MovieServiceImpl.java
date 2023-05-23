package com.central.book.show.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.show.entity.Movie;
import com.central.book.show.entity.Review;
import com.central.book.show.exception.ContentNotFoundException;
import com.central.book.show.messages.Message;
import com.central.book.show.repository.MovieRepository;
import com.central.book.show.repository.ReviewRepository;
import com.central.book.show.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Movie> getAllMovies() {

		return movieRepository.findAll();
	}

	@Override
	public Movie getMovieById(Integer movieId) {
		Movie movie = movieRepository.findById(movieId).orElse(null);
		
		if (movie != null) 
			return movie;
		else 
			throw new ContentNotFoundException(Message.formatMessage(Message.MOVIE_NOT_FOUND, String.valueOf(movieId)));

	}

	@Override
	public void addNewMovie(Movie movie) {
		
		movieRepository.save(movie);
	}

	@Override
	public void updateMovie(Movie movie) {
		
		if (getMovieById(movie.getMovieId()) != null)
			movieRepository.save(movie);
		else 
			throw new ContentNotFoundException(Message.formatMessage(Message.MOVIE_NOT_FOUND, String.valueOf(movie.getMovieId())));
	}

	@Override
	public void addNewReviewCommentToMovie(Review review, Integer movieId) {
		
		Movie movie = getMovieById(movieId);
		
		review.setMovie(movie);
		
		reviewRepository.save(review);
		
	}
}
