package com.central.movie.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
