package com.unibuc.ex1curs11.controller;

import com.unibuc.ex1curs11.dto.BudgetDTO;
import com.unibuc.ex1curs11.service.BudgetService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/{userId}")
    @ApiOperation("Create a new budget for a user")
    public ResponseEntity<BudgetDTO> createBudget(@PathVariable Long userId, @RequestBody BudgetDTO budgetDTO) {
        BudgetDTO created = budgetService.createBudget(userId, budgetDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{userId}")
    @ApiOperation("Get all budgets for a specific user")
    public ResponseEntity<List<BudgetDTO>> getUserBudgets(@PathVariable Long userId) {
        List<BudgetDTO> budgets = budgetService.getUserBudgets(userId);
        return ResponseEntity.ok(budgets);
    }
}