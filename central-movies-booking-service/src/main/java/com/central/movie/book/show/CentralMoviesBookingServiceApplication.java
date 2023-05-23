package com.central.movie.book.show;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EntityScan("com.central.book.common")
public class CentralMoviesBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralMoviesBookingServiceApplication.class, args);
	}
	
	@Bean
    ModelMapper modelMapper(){
	  return new ModelMapper();
	}

}
