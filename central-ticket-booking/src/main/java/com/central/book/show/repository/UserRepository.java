package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByEmailId(String emailId);
	
	public boolean existsByUserName(String username);
	
	public User findOneByEmailId(String emailId);
	
	public User findOneByUserName(String username);
	
	public User findByUserIdAndUserName(Integer userId, String userName);
}
