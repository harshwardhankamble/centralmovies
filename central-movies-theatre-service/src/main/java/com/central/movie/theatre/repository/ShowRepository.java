package com.central.movie.theatre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {

	public List<Show> findByMovieMovieId(Integer movieId);
}
