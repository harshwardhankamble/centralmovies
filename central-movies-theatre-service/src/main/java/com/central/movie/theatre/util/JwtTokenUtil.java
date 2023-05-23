package com.central.movie.theatre.util;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.central.book.common.constant.Constants;
import com.central.movie.theatre.configuration.CentralMovieConfiguration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CentralMovieConfiguration centralMovieConfiguration;
	
	private Key key;
	
	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(centralMovieConfiguration.getSecretKey().getBytes());
	}
	
	public String generateAuthToken(Integer userId, String username, Date dateOfBirth) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userId);
		claims.put("userName", username);
		return doGenerateToken(claims, userId);
	}
	
	private String doGenerateToken(Map<String, Object> claims, Integer subject) {
		
		return Jwts.builder().addClaims(claims).setSubject(String.valueOf(subject)).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_TOKEN_VALIDITY * 1000))
				.signWith(key, SignatureAlgorithm.HS512).compact();
	}

}
