package com.central.book.show.service;

import java.util.List;

import com.central.book.show.entity.Screen;

public interface ScreenService {

	public List<Screen> getAllScreenDetails();

	public void addNewScreen(Screen screen);

	public Screen getScreenById(Integer screenId);
}
