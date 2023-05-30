package com.central.movie.theatre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Theatre;
import com.central.movie.theatre.repository.TheatreRepository;
import com.central.movie.theatre.service.TheatreService;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;
	
	@Override
	public List<Theatre> getAllTheatres() {
		
		return theatreRepository.findAll();
	}

}
