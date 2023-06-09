package com.central.movie.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Movie;
import com.central.book.common.entity.Review;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.message.Message;
import com.central.movie.repository.MovieRepository;
import com.central.movie.repository.ReviewRepository;
import com.central.movie.service.MovieService;

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
		List<Review> reviews = movie.getReviews();
		movie.setReviews(null);
		movie = movieRepository.save(movie);
		
		for(Review review: reviews) {
			review.setMovie(movie);
			review.setReviewDateTime(new Date());
		}
		movie.setReviews(reviews);
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
