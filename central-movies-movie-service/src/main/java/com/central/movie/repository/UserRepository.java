package com.central.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.central.book.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByEmailId(String emailId);
	
	public boolean existsByUserName(String username);
	
	public User findOneByEmailId(String emailId);
	
	public User findOneByUserName(String username);
	
	public User findByUserIdAndUserName(Integer userId, String userName);
	
	@Modifying
	@Query(value = "update users set role_id = :roleId where user_id = :userId", nativeQuery = true)
	public void changeUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	
	public User findByUserNameAndPassword(String userName, String password);
	
	public List<User> findByRoleRoleIdNot(Integer roleId);
}
