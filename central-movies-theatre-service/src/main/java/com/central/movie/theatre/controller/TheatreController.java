package com.central.movie.theatre.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.TheatreDto;
import com.central.book.common.entity.Theatre;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.movie.theatre.aop.AccessControl;
import com.central.movie.theatre.service.TheatreService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/theatres")
@Tag(name = "Theatre")
@Import(GlobalExceptionResponseEntity.class)
public class TheatreController {

	@Autowired
	private TheatreService theatreService;
	
	@Autowired
	private ModelMapper modelMapper;

	@AccessControl(roles = { Constants.MANAGER })
	@GetMapping
	public ResponseEntity<List<TheatreDto>> getAllScreenDetails(@RequestParam Integer userId) {
		List<Theatre> theatres = theatreService.getAllTheatres();

		return ResponseEntity.ok().body(convertEntityToDtos(theatres));

	}

	private TheatreDto convertEntityToDto(Theatre theatre) {

		return modelMapper.map(theatre, TheatreDto.class);
	}

	private List<TheatreDto> convertEntityToDtos(List<Theatre> theatres) {

		return theatres.stream().map(this::convertEntityToDto).toList();
	}
}
