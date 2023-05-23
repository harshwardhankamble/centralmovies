package com.central.movie.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Movie;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.message.Message;
import com.central.movie.theatre.repository.MovieRepository;
import com.central.movie.theatre.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Movie getMovieById(Integer movieId) {
		Movie movie = movieRepository.findById(movieId).orElse(null);

		if (movie != null)
			return movie;
		else
			throw new ContentNotFoundException(Message.formatMessage(Message.MOVIE_NOT_FOUND, String.valueOf(movieId)));

	}

}
