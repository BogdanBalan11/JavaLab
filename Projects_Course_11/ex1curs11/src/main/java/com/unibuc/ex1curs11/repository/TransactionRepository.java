package com.unibuc.ex1curs11.repository;

import com.unibuc.ex1curs11.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
