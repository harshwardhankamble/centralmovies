package com.central.movie.theatre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {

	public List<Authentication> findByUserIdAndUserName(Integer userId, String userName);
	
	public Authentication findFirstByUserIdOrderByUserIdDesc(Integer userId);
}
