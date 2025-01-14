package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.dto.TransactionDTO;
import com.unibuc.ex1curs11.model.Category;
import com.unibuc.ex1curs11.model.Transaction;
import com.unibuc.ex1curs11.model.User;
import com.unibuc.ex1curs11.repository.CategoryRepository;
import com.unibuc.ex1curs11.repository.TransactionRepository;
import com.unibuc.ex1curs11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        User user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = null;
        if (transactionDTO.getCategoryId() != null) {
            category = categoryRepository.findById(transactionDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setUser(user);
        transaction.setCategory(category);

        // Adjust user’s single balance
        if ("EXPENSE".equalsIgnoreCase(transactionDTO.getTransactionType())) {
            user.setBalance(user.getBalance().subtract(transactionDTO.getAmount()));
        } else if ("INCOME".equalsIgnoreCase(transactionDTO.getTransactionType())) {
            user.setBalance(user.getBalance().add(transactionDTO.getAmount()));
        }

        transactionRepository.save(transaction);
        userRepository.save(user); // update user’s balance

        // Fill back the ID to the DTO
        transactionDTO.setId(transaction.getId());
        return transactionDTO;
    }
}
