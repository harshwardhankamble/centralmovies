package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
