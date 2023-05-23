package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
