package com.central.book.show.service;

import java.util.List;

import com.central.book.show.entity.Show;
import com.central.book.show.enums.ShowStatus;

public interface ShowService {

	public List<Show> getAllShows();

	public void addNewShow(Show show);

	public void removeShowByShowId(Integer showId);

	public void updateShow(Show show);

	public Show getShowById(Integer showId);

	public void changeShowStatus(Integer showId, ShowStatus status);

}
