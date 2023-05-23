package com.central.movie.book.show.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Screen;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.message.Message;
import com.central.movie.book.show.repository.ScreenRepository;
import com.central.movie.book.show.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository screenRepository;
	
	@Override
	public Screen getScreenById(Integer screenId) {
		Screen screen = screenRepository.findById(screenId).orElse(null);
		
		if (screen != null) 
			return screen;
		else 
			throw new ContentNotFoundException(Message.formatMessage(Message.SCREEN_NOT_FOUND, screenId));
	}

}
