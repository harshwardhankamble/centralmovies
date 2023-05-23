package com.central.book.show.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class CentralMovieConfiguration {

	@Value("${jwt.secret.key}")
	private String secretKey;
}
