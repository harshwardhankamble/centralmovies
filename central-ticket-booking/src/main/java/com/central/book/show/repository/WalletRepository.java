package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{
	
	public Wallet findByUserUserId(Integer userId);
}
