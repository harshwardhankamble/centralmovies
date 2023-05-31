package com.central.movie.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.UserDto;
import com.central.book.common.entity.Role;
import com.central.book.common.entity.User;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.movie.aop.AccessControl;
import com.central.movie.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/users")
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
	
	@AccessControl(roles = {Constants.ADMIN})
	@PutMapping("/{clientId}/role/{roleId}")
	public void changeRoleOfUser(@RequestParam Integer userId, @PathVariable Integer clientId, @PathVariable Integer roleId) {
		
		userService.changeRoleOfUser(clientId, roleId);
	}
	
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER, Constants.MANAGER})
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable Integer userId) {
		
		return ResponseEntity.ok().body(convertEntityToDto(userService.getUserDetailsById(userId)));
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER, Constants.MANAGER})
	@PostMapping("/login")
	public ResponseEntity<UserDto> validateCredentialsAndGenerateAccessToken(@RequestBody UserDto userDto){
		
		User user = userService.validateCredentialsAndGenerateAccessToken(userDto);
		userDto = convertEntityToDto(user);
		userDto.setPassword(null);
		return ResponseEntity.ok(userDto);
	}
	
	@AccessControl(roles = {Constants.ADMIN})
	@GetMapping("/customers")
	public ResponseEntity<List<UserDto>> getAllCustomers(@RequestParam Integer userId) {
		
		return ResponseEntity.ok(convertEntityToDtos(userService.getAllCustomers()));
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.CUSTOMER, Constants.MANAGER})
	@GetMapping("/{userId}/role")
	public String getUserRole(@PathVariable Integer userId) {
		
		return userService.getUserRoleByUserId(userId);
	}
	
	private User convertDtoToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setRole(new Role(null, userDto.getRoleName(), null));
		user.setDateOfBirth(userDto.getDateOfBirth("dd-mm-yyyy"));
		return user;
	}

	private UserDto convertEntityToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setRoleName(user.getRole() != null ? user.getRole().getRoleName() : null);
		return userDto;
	}
	
	private List<UserDto> convertEntityToDtos(List<User> users) {
		
		return users.stream().map(this::convertEntityToDto).toList();
	}

}
