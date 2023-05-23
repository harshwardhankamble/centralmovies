package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
