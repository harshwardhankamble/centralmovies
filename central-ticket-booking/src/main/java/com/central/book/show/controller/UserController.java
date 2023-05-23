package com.central.book.show.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.show.dto.UserDto;
import com.central.book.show.entity.Role;
import com.central.book.show.entity.User;
import com.central.book.show.exception.GlobalExceptionResponseEntity;
import com.central.book.show.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User")
@Import(GlobalExceptionResponseEntity.class)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/check/emailid/{emailId}")
	public boolean checkUserWithEmailId(@PathVariable String emailId) {
		
		return userService.checkUserWithEmail(emailId);
	}
	
	@GetMapping("/check/username/{username}")
	public boolean checkUserWithUsername(@PathVariable String username) {
		
		return userService.checkUserWithUsername(username);
	}
	
	@PostMapping
	public void registerNewUser(@RequestBody UserDto userDto) {
		User user = convertDtoToEntity(userDto);
		
		userService.registerNewUser(user);
	}
	
	private User convertDtoToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setRole(new Role(null, userDto.getRoleName(), null));
		user.setDateOfBirth(userDto.getDateOfBirth("dd-mm-yyyy"));
		return user;
	}

}
