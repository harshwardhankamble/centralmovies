package com.central.movie.theatre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Movie;
import com.central.book.common.entity.Screen;
import com.central.book.common.entity.Show;
import com.central.book.common.entity.ShowScreenTime;
import com.central.book.common.enums.ShowStatus;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.message.Message;
import com.central.movie.theatre.repository.ShowRepository;
import com.central.movie.theatre.repository.ShowScreenTimeRepository;
import com.central.movie.theatre.service.MovieService;
import com.central.movie.theatre.service.ScreenService;
import com.central.movie.theatre.service.ShowService;

import jakarta.transaction.Transactional;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private ShowScreenTimeRepository showScreenTimeRepository;

	@Override
	public List<Show> getAllShows() {
		
		return showRepository.findAll();
	}

	@Override
	public void addNewShow(Show show) {
		show = showRepository.save(show);	
		
		Movie movie = movieService.getMovieById(show.getMovieId());
		
		if (!show.getShowTime().isEmpty()) {
			for(ShowScreenTime showScreenTime: show.getShowTime()) {
				Screen screen = screenService.getScreenById(showScreenTime.getShowScreenTimeId().getScreen().getScreenId());
				showScreenTime.getShowScreenTimeId().setScreen(screen);
				showScreenTime.setShow(show);
			}
		}
		
		show.setMovie(movie);
		
		showRepository.save(show);		
	}

	@Override
	public void removeShowByShowId(Integer showId) {
		
		if (showRepository.existsById(showId))
			showRepository.deleteById(showId);
		else 
			throw new ContentNotFoundException(Message.formatMessage(Message.SHOW_NOT_FOUND, showId));		
	}

	@Override
	public void updateShow(Show show) {
		
		if (!showRepository.existsById(show.getShowId())) 
			throw new ContentNotFoundException(Message.formatMessage(Message.SHOW_NOT_FOUND, show.getShowId()));
		
		
		Movie movie = movieService.getMovieById(show.getMovie().getMovieId());
		
		if (!show.getShowTime().isEmpty()) {
			for(ShowScreenTime showScreenTime: show.getShowTime()) {
				Screen screen = screenService.getScreenById(showScreenTime.getShowScreenTimeId().getScreen().getScreenId());
				showScreenTime.getShowScreenTimeId().setScreen(screen);
				showScreenTime.setShow(show);
			}
		}
		
		show.setMovie(movie);
		
		showRepository.save(show);		
		
	}

	@Override
	public Show getShowById(Integer showId) {
		Show show = showRepository.findById(showId).orElse(null);
		
		if (show == null) 
			throw new ContentNotFoundException(Message.formatMessage(Message.SHOW_NOT_FOUND, showId));		
		
		return show;
	}

	@Override
	@Transactional
	public void changeShowStatus(Integer showId, ShowStatus status) {
		
		if (showRepository.existsById(showId))
			showScreenTimeRepository.changeShowStatus(showId, status.ordinal());
		else 
			throw new ContentNotFoundException(Message.formatMessage(Message.SHOW_NOT_FOUND, showId));	
		
	}

	@Override
	public List<Show> getShowsByMovieId(Integer movieId) {
		
		return showRepository.findByMovieMovieId(movieId);
	}
}
