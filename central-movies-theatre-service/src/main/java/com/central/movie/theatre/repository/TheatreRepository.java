package com.central.movie.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

}
