package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
