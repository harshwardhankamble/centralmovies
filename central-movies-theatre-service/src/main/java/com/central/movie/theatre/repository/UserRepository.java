package com.central.movie.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.central.book.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByEmailId(String emailId);
	
	public boolean existsByUserName(String username);
	
	public User findOneByEmailId(String emailId);
	
	public User findOneByUserName(String username);
	
	public User findByUserIdAndUserName(Integer userId, String userName);
}
