package com.central.book.show.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.show.entity.Screen;
import com.central.book.show.exception.ContentNotFoundException;
import com.central.book.show.messages.Message;
import com.central.book.show.repository.ScreenRepository;
import com.central.book.show.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {
	
	@Autowired
	private ScreenRepository screenRepository;

	@Override
	public List<Screen> getAllScreenDetails() {
		
		return screenRepository.findAll();
	}

	@Override
	public void addNewScreen(Screen screen) {
		
		screenRepository.save(screen);
	}

	@Override
	public Screen getScreenById(Integer screenId) {
		
		Screen screen = screenRepository.findById(screenId).orElse(null);
		
		if (screen != null) 
			return screen;
		else 
			throw new ContentNotFoundException(Message.formatMessage(Message.SCREEN_NOT_FOUND, screenId));
	}
	
	

}
