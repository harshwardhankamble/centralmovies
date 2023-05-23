package com.central.book.show.service;

import java.util.List;

import com.central.book.show.entity.Movie;
import com.central.book.show.entity.Review;

public interface MovieService {
	
	public List<Movie> getAllMovies();
	
	public Movie getMovieById(Integer movieId);
	
	public void addNewMovie(Movie movie);

	public void updateMovie(Movie movie);

	public void addNewReviewCommentToMovie(Review review, Integer movieId);
	
}
