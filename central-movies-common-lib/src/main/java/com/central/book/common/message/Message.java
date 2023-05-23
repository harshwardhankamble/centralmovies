package com.central.book.common.message;

import java.text.MessageFormat;

public class Message {

	public static final String MOVIE_NOT_FOUND = "Movie not found with id: {0}";
	
	public static final String SCREEN_NOT_FOUND = "Screen not found with id: {0}";

	public static final String SHOW_NOT_FOUND = "Show not found with id: {0}";
	
	public static final String SHOW_NOT_FOUND_FOR_TIME = "Show not found for date and time: {0}";

	public static final String USER_ALREADY_EXIST = "User with {0} {1} already exist. Please try with different {2}";

	public static final String USER_NOT_FOUND = "User with {0} {1} is not found";
	
	public static final String USER_NOT_FOUND_WITH_USERID_USERNAME = "User with User Id - {0} and Username - {1} is not found";

	public static final String PLEASE_ENTER_USERNAME_OR_EMAIL = "Please enter either email or username";

	public static final String SEAT_NOT_VACCANT = "Seat Number {0} is not vaccant";

	public static final String PLEASE_ENTER_VALID_SEAT_NUMBER = "Please enter valid seat numbers";

	public static final String BOOKING_NOT_FOUND = "Booking not found for Booking ID: {0}";

	public static final String WALLET_NOT_FOUND = "Wallet not found for User id: {0}";

	public static final String UNAUTHROIZED_ACCESS = "Unauthorized user access: Access Denied";
	
	public static String formatMessage(String message, Object ...args) {
		
		return MessageFormat.format(message, args);
	}
	
}
