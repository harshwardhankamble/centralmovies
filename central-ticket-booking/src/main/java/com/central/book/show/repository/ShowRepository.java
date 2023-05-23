package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {

}
