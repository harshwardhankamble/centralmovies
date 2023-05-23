package com.central.movie.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
