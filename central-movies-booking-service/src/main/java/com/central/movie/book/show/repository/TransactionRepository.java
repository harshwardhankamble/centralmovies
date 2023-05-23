package com.central.movie.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
