package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
