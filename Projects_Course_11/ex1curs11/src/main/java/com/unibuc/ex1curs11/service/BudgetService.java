package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.dto.BudgetDTO;
import com.unibuc.ex1curs11.model.Budget;
import com.unibuc.ex1curs11.model.User;
import com.unibuc.ex1curs11.repository.BudgetRepository;
import com.unibuc.ex1curs11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    public BudgetDTO createBudget(Long userId, BudgetDTO budgetDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = new Budget();
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        budget.setUser(user);

        budgetRepository.save(budget);
        return convertToDTO(budget);
    }

    public List<BudgetDTO> getUserBudgets(Long userId) {
        return budgetRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BudgetDTO convertToDTO(Budget budget) {
        BudgetDTO dto = new BudgetDTO();
        dto.setId(budget.getId());
        dto.setAmount(budget.getAmount());
        dto.setStartDate(budget.getStartDate());
        dto.setEndDate(budget.getEndDate());
        dto.setUserId(budget.getUser().getId());
        return dto;
    }
}