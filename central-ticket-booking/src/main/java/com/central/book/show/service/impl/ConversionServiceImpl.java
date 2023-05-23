package com.central.book.show.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.show.dto.MovieDto;
import com.central.book.show.entity.Movie;
import com.central.book.show.service.ConversionService;

@Service
public class ConversionServiceImpl implements ConversionService {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Object convertEntityToDto(Object entity, Object dto) {
		Object object = null;
		if (entity instanceof Movie && dto instanceof MovieDto) {
			object = modelMapper.map(entity, MovieDto.class);
		}
		
		return object;
	}

}
