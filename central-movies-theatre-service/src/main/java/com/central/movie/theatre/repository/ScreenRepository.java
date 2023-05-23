package com.central.movie.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
