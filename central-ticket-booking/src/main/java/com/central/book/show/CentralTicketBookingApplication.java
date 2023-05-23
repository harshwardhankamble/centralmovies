package com.central.book.show;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class CentralTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralTicketBookingApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper(){
	  return new ModelMapper();
	}
}
