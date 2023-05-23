package com.central.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
