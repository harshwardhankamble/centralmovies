package com.central.movie.theatre.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.ScreenDto;
import com.central.book.common.entity.Screen;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.movie.theatre.aop.AccessControl;
import com.central.movie.theatre.service.ScreenService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/screens")
@Tag(name = "Screen")
@Import(GlobalExceptionResponseEntity.class)
public class ScreenController {
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@AccessControl(roles = {Constants.MANAGER})
	@GetMapping
	public ResponseEntity<List<ScreenDto>> getAllScreenDetails(@RequestParam Integer userId) {
		List<Screen> screens = screenService.getAllScreenDetails();
		
		return ResponseEntity.ok().body(convertEntityToDtos(screens));
		
	}
	
	@AccessControl(roles = {Constants.MANAGER})
	@PostMapping
	public void addNewScreen(@RequestParam Integer userId, @RequestBody ScreenDto screenDto) {
		screenService.addNewScreen(convertDtoToEntity(screenDto));
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER, Constants.MANAGER})
	@GetMapping("/{screenId}/seats")
	public int getTotalNumberOfSeatsByScreenId(@RequestParam Integer userId, @PathVariable Integer screenId) {
		return screenService.getTotalNumberOfSeatsByScreenId(screenId);
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
