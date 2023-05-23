package com.central.book.show.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.show.dto.ScreenDto;
import com.central.book.show.entity.Screen;
import com.central.book.show.exception.GlobalExceptionResponseEntity;
import com.central.book.show.service.ScreenService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/screens")
@Tag(name = "Screen")
@Import(GlobalExceptionResponseEntity.class)
public class ScreenController {
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping
	public ResponseEntity<List<ScreenDto>> getAllScreenDetails() {
		List<Screen> screens = screenService.getAllScreenDetails();
		
		return ResponseEntity.ok().body(convertEntityToDtos(screens));
		
	}
	
	@PostMapping
	public void addNewScreen(@RequestBody ScreenDto screenDto) {
		screenService.addNewScreen(convertDtoToEntity(screenDto));
	}
	
	private ScreenDto convertEntityToDto(Screen screen) {
		
		return modelMapper.map(screen, ScreenDto.class);
	}
	
	private List<ScreenDto> convertEntityToDtos(List<Screen> screens) {
		
		return screens.stream().map(this::convertEntityToDto).toList();
	}
	
	private Screen convertDtoToEntity(ScreenDto screenDto) {
		
		return modelMapper.map(screenDto, Screen.class);
	}

}
