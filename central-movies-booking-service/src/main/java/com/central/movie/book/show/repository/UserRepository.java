package com.central.movie.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
