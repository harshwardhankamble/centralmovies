package com.central.movie.theatre.service;

import java.util.List;

import com.central.book.common.entity.Screen;

public interface ScreenService {

	public List<Screen> getAllScreenDetails();

	public void addNewScreen(Screen screen);

	public Screen getScreenById(Integer screenId);

	public int getTotalNumberOfSeatsByScreenId(Integer screenId);
}
